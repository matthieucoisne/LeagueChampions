package com.leaguechampions.features.champions.presentation.champions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.leaguechampions.features.champions.domain.usecase.GetChampionsUseCase
import com.leaguechampions.libraries.core.presentation.BaseAction
import com.leaguechampions.libraries.core.presentation.BaseViewModel
import com.leaguechampions.libraries.core.presentation.BaseViewState
import com.leaguechampions.libraries.core.utils.Event
import com.leaguechampions.libraries.core.utils.Resource
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject

class ChampionsViewModel @Inject constructor(
    private val getChampionsUseCase: GetChampionsUseCase
) : BaseViewModel<ChampionsViewModel.ViewState, ChampionsViewModel.Action>(ViewState()) {

    sealed class Action : BaseAction {
        data class ShowSuccess(val champions: ChampionsUiModel) : Action()
        object ShowFailure : Action()
    }

    sealed class ViewAction {
        data class NavigateToDetails(val championId: String) : ViewAction()
    }


    data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val champions: ChampionsUiModel? = null
//        data class ShowLoading(val champions: ChampionsUiModel?) : ViewState()
//        data class ShowChampions(val champions: ChampionsUiModel) : ViewState()
//        data class ShowError(@StringRes val errorStringId: Int) : ViewState()
    ) : BaseViewState

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.ShowSuccess -> state.copy(
            isLoading = false,
            isError = false,
            champions = viewAction.champions
        )
        is Action.ShowFailure -> state.copy(
            isLoading = false,
            isError = true,
            champions = null
        )
    }

    private val _viewAction = MutableLiveData<Event<ViewAction>>()
    val viewAction: LiveData<Event<ViewAction>>
        get() = _viewAction

    private val _viewState2: MutableLiveData<Unit>
    val viewState2: LiveData<Unit>
        get() = _viewState2

    init {
//        viewModelScope.launch {
//            getChampionsUseCase.execute().map { resource ->
//                when (resource) {
//                    is Resource.Loading -> {
//                        Timber.d("ROFLCOPTER Loading")
//                    }
//                    is Resource.Error -> {
//                        Timber.d("ROFLCOPTER Error")
//                        sendAction(Action.ShowFailure)
//                    }
//                    is Resource.Success -> {
//                        Timber.d("ROFLCOPTER Success")
//                        sendAction(Action.ShowSuccess(resource.data.toChampionsUiModel()))
//                    }
//                    else -> {
//                        Timber.d("ROFLCOPTER else")
//                    }
//                }
//            }
//        }



//            liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
//                try {
//                    emit(ViewState.ShowChampionDetails(getChampionsUseCase.execute()))
//                } catch (e: Exception) {
//                    Timber.e(e)
//                    val errorStringId = when (e) {
//                        is IOException -> R.string.error_io
//                        else -> R.string.error_something_went_wrong
//                    }
//                    emit(ViewState.ShowError(errorStringId))
//                }
//            } as MutableLiveData<ViewState>







        _viewState2 = liveData(context = viewModelScope.coroutineContext + Dispatchers.Main) {
            emitSource(getChampionsUseCase.execute())
        }.map { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Timber.d("ROFLCOPTER Loading")
                }
                is Resource.Error -> {
                    Timber.d("ROFLCOPTER Error")
                    sendAction(Action.ShowFailure)
                }
                is Resource.Success -> {
                    Timber.d("ROFLCOPTER Success")
                    sendAction(Action.ShowSuccess(resource.data.toChampionsUiModel()))
                }
            }
        } as MutableLiveData<Unit>
    }

    fun onChampionClicked(championId: String) {
        _viewAction.value = Event(ChampionsViewModel.ViewAction.NavigateToDetails(championId))
    }
}
