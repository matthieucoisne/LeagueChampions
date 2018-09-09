package com.leaguechampions.ui.championdetails

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.repository.ChampionRepository
import com.leaguechampions.utils.Event
import com.leaguechampions.utils.Status
import javax.inject.Inject

class ChampionDetailsViewModel @Inject constructor(private val championRepository: ChampionRepository) : ViewModel() {

    sealed class ViewAction {
        object Finish : ViewAction()
    }

    data class ViewState(
            val status: Status,
            val champion: Champion? = null,
            val error: String = ""
    )

    private val _viewAction = MutableLiveData<Event<ViewAction>>()
    val viewAction: LiveData<Event<ViewAction>>
        get() = _viewAction

    private val _viewState: MutableLiveData<ViewState>
    val viewState: LiveData<ViewState>
        get() = _viewState

    private val championId = MutableLiveData<String>()

    init {
        _viewState = Transformations.switchMap(championId) { championId ->
            Transformations.map(championRepository.getChampionDetails(championId)) { resource ->
                when (resource.status) {
                    Status.LOADING -> ViewState(status = Status.LOADING)
                    Status.SUCCESS -> ViewState(status = Status.SUCCESS, champion = resource.data)
                    Status.ERROR -> ViewState(status = Status.ERROR, error = resource.message!!)
                }
            }
        } as MutableLiveData<ViewState>
    }

    fun setChampionId(championId: String?) {
        if (championId == this.championId.value) {
            return
        }
        this.championId.value = championId
    }
}
