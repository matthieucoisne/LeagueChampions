package com.leaguechampions.ui.champions

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.leaguechampions.R
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.repository.ChampionRepository
import com.leaguechampions.data.repository.Resource
import javax.inject.Inject


class ChampionsViewModel @Inject constructor(
        championRepository: ChampionRepository
) : ViewModel() {

    sealed class ViewAction {
        class ShowDetails(val championId: String) : ViewAction()
        object ShowSettings : ViewAction()
    }

    data class ViewState(
            val champions: Map<String, Champion> = emptyMap(),
            val error: Int = R.string.error_something_went_wrong
    )

    val viewAction: MutableLiveData<ViewAction> = MutableLiveData() // TODO change MutableLiveData for SingleLiveData as this should be 1 time only

    private val _viewState: MutableLiveData<ViewState> //= MutableLiveData() -- initialized in the init block
    val viewState: LiveData<ViewState>
        get() = _viewState

    private fun currentViewState(): ViewState = viewState.value ?: ViewState()

//    private val _champions: MutableLiveData<Map<String, Champion>>
//    val champions: LiveData<Map<String, Champion>>
//        get() = _champions
//
//    private val _error: MutableLiveData<Event<Int>>
//    val error: LiveData<Event<Int>>
//        get() = _error

    private var riotResponse: LiveData<Resource<Map<String, Champion>>>

    init {
        riotResponse = championRepository.getChampions()

        _viewState = Transformations.map(riotResponse) {
            ViewState(champions = it.data) // we dont need to call currentViewState() as this is the initialization
        } as MutableLiveData<ViewState>

//        _viewState = Transformations.switchMap(riotResponse) {
//            getLiveDataViewState(it)
//        } as MutableLiveData<ViewState>




//        if (riotResponse.value != null) {
//            _champions.value = riotResponse.value?.data
//        } else {
//            _error.value = Event(R.string.error_something_went_wrong)
//        }

//        val state = if (riotResponse.value != null) {
//            currentViewState().copy(champions = riotResponse.value?.data!!)
//        } else {
//            currentViewState().copy(error = R.string.error_something_went_wrong)
//        }
//        viewState.value = state
    }

    fun getLiveDataViewState(champions: Resource<Map<String, Champion>>): MutableLiveData<ViewState> {
        val viewState = MutableLiveData<ViewState>()

        val updatedViewState = currentViewState().copy(champions = champions.data)
        val newViewState = ViewState(champions = champions.data)

        viewState.value = newViewState
        return viewState
    }


    fun loadingFinished() {
//        viewState.value = currentViewState().copy(isLoading = false)
    }

    fun onChampionClicked(championId: String) {
        viewAction.value = ViewAction.ShowDetails(championId)
    }

    fun onSettingsClicked() {
//        viewAction.value = ViewAction.ShowSettings
        _viewState.value = currentViewState().copy(champions = emptyMap(), error = R.string.error_io)
    }
}