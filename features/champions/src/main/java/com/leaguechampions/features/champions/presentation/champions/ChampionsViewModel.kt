package com.leaguechampions.features.champions.presentation.champions

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.leaguechampions.features.champions.R
import com.leaguechampions.features.champions.domain.usecase.GetChampionsUseCase
import com.leaguechampions.libraries.core.utils.Event
import com.leaguechampions.libraries.core.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ChampionsViewModel @Inject constructor(
    private val getChampionsUseCase: GetChampionsUseCase
) : ViewModel() {

    sealed class ViewAction {
        data class NavigateToDetails(val championId: String) : ViewAction()
    }

    sealed class ViewState {
        data class ShowLoading(val champions: ChampionsUiModel?) : ViewState()
        data class ShowChampions(val champions: ChampionsUiModel) : ViewState()
        data class ShowError(@StringRes val errorStringId: Int) : ViewState()
    }

    private val _viewAction = MutableLiveData<Event<ViewAction>>()
    val viewAction: LiveData<Event<ViewAction>>
        get() = _viewAction

    private val _viewState: MutableLiveData<ViewState>
    val viewState: LiveData<ViewState>
        get() = _viewState

    init {
        _viewState = liveData(context = viewModelScope.coroutineContext + Dispatchers.Main) {
            emitSource(getChampionsUseCase.execute())
        }.map { resource ->
            when (resource) {
                is Resource.Loading -> ViewState.ShowLoading(resource.data?.toChampionsUiModel())
                is Resource.Error -> ViewState.ShowError(R.string.error_something_went_wrong)
                is Resource.Success -> ViewState.ShowChampions(resource.data.toChampionsUiModel())
            }
        } as MutableLiveData<ViewState>
    }

    fun onChampionClicked(championId: String) {
        _viewAction.value = Event(ViewAction.NavigateToDetails(championId))
    }
}
