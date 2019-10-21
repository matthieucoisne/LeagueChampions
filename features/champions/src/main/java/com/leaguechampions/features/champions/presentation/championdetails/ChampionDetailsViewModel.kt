package com.leaguechampions.features.champions.presentation.championdetails

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.leaguechampions.features.champions.R
import com.leaguechampions.features.champions.domain.usecase.GetChampionDetailsUseCase
import com.leaguechampions.libraries.core.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ChampionDetailsViewModel @Inject constructor(
    private val getChampionDetailsUseCase: GetChampionDetailsUseCase
) : ViewModel() {

    sealed class ViewState {
        data class ShowLoading(val championDetails: ChampionDetailsUiModel?) : ViewState()
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
                emitSource(getChampionDetailsUseCase.execute(championId))
            }
        }.map { resource ->
            when (resource) {
                is Resource.Loading -> ViewState.ShowLoading(resource.data?.toChampionDetailsUiModel())
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
