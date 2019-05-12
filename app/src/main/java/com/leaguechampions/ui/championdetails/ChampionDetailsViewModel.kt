package com.leaguechampions.ui.championdetails

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.leaguechampions.R
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.repository.ChampionRepository
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class ChampionDetailsViewModel @Inject constructor(private val championRepository: ChampionRepository) : ViewModel() {

    sealed class ViewState {
        object ShowLoading : ViewState()
        data class ShowChampion(val champion: Champion) : ViewState()
        data class ShowError(@StringRes val errorStringId: Int) : ViewState()
    }

    private val _championId = MutableLiveData<String>()

    val viewState = _championId.switchMap { championId ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(ViewState.ShowLoading)
            try {
                emit(ViewState.ShowChampion(championRepository.getChampionDetails(championId)))
            } catch (e: Exception) {
                Timber.e(e)
                val errorStringId = when (e) {
                    is IOException -> R.string.error_io
                    else -> R.string.error_something_went_wrong
                }
                emit(ViewState.ShowError(errorStringId))
            }
        }
    }

    fun setChampionId(championId: String?) {
        if (_championId.value == championId) {
            return
        }
        _championId.value = championId
    }
}
