package com.leaguechampions.ui.champions

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.leaguechampions.R
import com.leaguechampions.data.repository.ChampionRepository
import com.leaguechampions.utils.Event
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class ChampionsViewModel @Inject constructor(
        private val championRepository: ChampionRepository
) : ViewModel() {

    sealed class ViewAction {
        data class NavigateToDetails(val championId: String) : ViewAction()
    }

    sealed class ViewState {
        object ShowLoading : ViewState()
        data class ShowChampions(val champions: ChampionsUiModel) : ViewState()
        data class ShowError(@StringRes val errorStringId: Int) : ViewState()
    }

    private val _viewAction = MutableLiveData<Event<ViewAction>>()
    val viewAction: LiveData<Event<ViewAction>>
        get() = _viewAction

    val viewState = liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(ViewState.ShowLoading)
        try {
            emit(ViewState.ShowChampions(championRepository.getChampions().toChampionsUiModel()))
        } catch (e: Exception) {
            Timber.e(e)
            val errorStringId = when (e) {
                is IOException -> R.string.error_io
                else -> R.string.error_something_went_wrong
            }
            emit(ViewState.ShowError(errorStringId))
        }
    }

    fun onChampionClicked(championId: String) {
        _viewAction.value = Event(ViewAction.NavigateToDetails(championId))
    }
}
