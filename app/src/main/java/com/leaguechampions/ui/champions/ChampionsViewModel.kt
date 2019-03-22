package com.leaguechampions.ui.champions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.repository.ChampionRepository
import com.leaguechampions.utils.Event
import com.leaguechampions.utils.Resource
import com.leaguechampions.utils.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChampionsViewModel @Inject constructor(val championRepository: ChampionRepository) : ViewModel(), CoroutineScope {

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

    private val job = Job()
    override val coroutineContext = job + Dispatchers.Main

    init {
        _viewState = Transformations.map(getData()) { resource ->
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

    fun getData(): LiveData<Resource<List<Champion>>> {
        launch {
            championRepository.getChampions()
        }
    }
}
