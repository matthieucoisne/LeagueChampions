package com.leaguechampions.ui.champions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.repository.ChampionRepository
import com.leaguechampions.utils.Event
import com.leaguechampions.utils.Status
import javax.inject.Inject

class ChampionsViewModel @Inject constructor(championRepository: ChampionRepository) : ViewModel() {

    sealed class ViewAction {
        class ShowDetails(val championId: String) : ViewAction()
        object ShowSettings : ViewAction()
    }

    data class ViewState(
            val status: Status,
            val champions: List<Champion> = emptyList(),
            val error: String = ""
    )

    private val _viewAction = MutableLiveData<Event<ViewAction>>()
    val viewAction: LiveData<Event<ViewAction>>
        get() = _viewAction

    private val _viewState: MutableLiveData<ViewState>
    val viewState: LiveData<ViewState>
        get() = _viewState

    init {
        _viewState = Transformations.map(championRepository.getChampions()) { resource ->
            when (resource.status) {
                Status.LOADING -> ViewState(status = Status.LOADING)
                Status.SUCCESS -> {
                    val champions = resource.data!!.toMutableList()
                    champions.sort()
                    ViewState(status = Status.SUCCESS, champions = champions)
                }
                Status.ERROR -> ViewState(status = Status.ERROR, error = resource.message!!)
            }
        } as MutableLiveData<ViewState>
    }

    fun onChampionClicked(championId: String) {
        _viewAction.value = Event(ViewAction.ShowDetails(championId))
    }
}
