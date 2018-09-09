package com.leaguechampions.ui.champions

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.view.MenuItem
import com.leaguechampions.R
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.repository.ChampionRepository
import com.leaguechampions.data.repository.Status
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

    val viewAction: MutableLiveData<ViewAction> = MutableLiveData() // TODO change MutableLiveData for SingleLiveData as this should be 1 time only

    private val _viewState: MutableLiveData<ViewState>
    val viewState: LiveData<ViewState>
        get() = _viewState

//    private fun currentViewState(): ViewState = viewState.value ?: ViewState()

    init {
        val riotResponse = championRepository.getChampions()

        _viewState = Transformations.map(riotResponse) { resource ->
            when(resource.status) {
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

    fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                viewAction.value = ViewAction.ShowSettings
                true
            }
            else -> false
        }
    }

    fun onChampionClicked(championId: String) {
        viewAction.value = ViewAction.ShowDetails(championId)
    }
}