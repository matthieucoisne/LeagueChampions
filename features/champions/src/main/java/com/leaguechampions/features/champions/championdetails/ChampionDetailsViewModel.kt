package com.leaguechampions.features.champions.championdetails

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.leaguechampions.core.data.repository.ChampionRepository
import com.leaguechampions.core.utils.Resource
import com.leaguechampions.features.champions.R
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ChampionDetailsViewModel @Inject constructor(
        private val championRepository: ChampionRepository
) : ViewModel() {

    sealed class ViewState {
        object ShowLoading : ViewState()
        data class ShowChampionDetails(val championDetails: ChampionDetailsUiModel) : ViewState()
        data class ShowError(@StringRes val errorStringId: Int) : ViewState()
    }

    private val _viewState: MutableLiveData<ViewState>
    val viewState: LiveData<ViewState>
        get() = _viewState

    private val championId = MutableLiveData<String>()

    init {
        _viewState = championId.switchMap { championId ->
            liveData(context = viewModelScope.coroutineContext + Dispatchers.Main) {
                emitSource(championRepository.getChampionWithCacheFromDb(championId))
            }
        }.map { resource ->
            when (resource) {
                is Resource.Loading -> ViewState.ShowLoading
                is Resource.Error -> ViewState.ShowError(R.string.error_something_went_wrong)
                is Resource.Success -> ViewState.ShowChampionDetails(resource.data.toChampionDetailsUiModel())
            }
        } as MutableLiveData<ViewState>
    }

    fun setChampionId(championId: String?) {
        if (this.championId.value != championId) {
            this.championId.value = championId
        }
    }
}
