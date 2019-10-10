package com.leaguechampions.features.champions.presentation.champions

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.leaguechampions.features.champions.R
import com.leaguechampions.features.champions.domain.usecase.GetChampionsUseCase
import com.leaguechampions.libraries.core.utils.Event
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class ChampionsViewModel @Inject constructor(
    private val getChampionsUseCase: GetChampionsUseCase
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
            emit(ViewState.ShowChampions(getChampionsUseCase.execute().toChampionsUiModel()))
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
