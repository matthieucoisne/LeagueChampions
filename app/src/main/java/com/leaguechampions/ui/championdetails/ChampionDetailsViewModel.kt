package com.leaguechampions.ui.championdetails

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.leaguechampions.R
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.repository.ChampionRepository
import com.leaguechampions.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ChampionDetailsViewModel @Inject constructor(private val championRepository: ChampionRepository) : ViewModel() {

    sealed class ViewState {
        object ShowLoading : ViewState()
        data class ShowChampion(val champion: Champion) : ViewState()
        data class ShowError(@StringRes val errorStringId: Int) : ViewState()
    }

    private val _championId = MutableLiveData<String>()

    val viewState = _championId.switchMap { championId ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.Main) {
            emitSource(championRepository.getChampionWithCacheFromDb(championId))
        }
    }.map { resource ->
        when (resource) {
            is Resource.Loading -> ViewState.ShowLoading
            is Resource.Error -> ViewState.ShowError(R.string.error_something_went_wrong)
            is Resource.Success -> ViewState.ShowChampion(resource.data)
        }
    }

    fun setChampionId(championId: String?) {
        if (_championId.value != championId) {
            _championId.value = championId
        }
    }
}
