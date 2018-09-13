package com.leaguechampions.ui.championdetails

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.repository.ChampionRepository
import com.leaguechampions.utils.Status
import javax.inject.Inject

class ChampionDetailsViewModel @Inject constructor(private val championRepository: ChampionRepository) : ViewModel() {

    data class ViewState(
            val status: Status,
            val champion: Champion? = null,
            val error: String = ""
    )

    private val _viewState: MutableLiveData<ViewState>
    val viewState: LiveData<ViewState>
        get() = _viewState

    private val _championId = MutableLiveData<String>()
    val championId: LiveData<String>
        get() = _championId

    init {
        _viewState = Transformations.switchMap(championId) { championId ->
            Transformations.map(championRepository.getChampionDetails(championId)) { resource ->
                when (resource.status) {
                    Status.LOADING -> ViewState(status = Status.LOADING)
                    Status.SUCCESS -> ViewState(status = Status.SUCCESS, champion = resource.data!!)
                    Status.ERROR -> ViewState(status = Status.ERROR, error = resource.message!!)
                }
            }
        } as MutableLiveData<ViewState>
    }

    fun setChampionId(championId: String?) {
        if (_championId.value == championId) {
            return
        }
        _championId.value = championId
    }
}
