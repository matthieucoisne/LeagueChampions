package com.leaguechampions.ui.champions

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.leaguechampions.R
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.repository.ChampionRepository
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

    private val _viewState: MutableLiveData<ViewState> //= MutableLiveData()
    val viewState: MutableLiveData<ViewState>
        get() = _viewState

//    init {
//        viewState.value = ViewState()
//    }

    private fun currentViewState(): ViewState = viewState.value!!

//    private val _champions: MutableLiveData<Map<String, Champion>>
//    val champions: LiveData<Map<String, Champion>>
//        get() = _champions
//
//    private val _error: MutableLiveData<Event<Int>>
//    val error: LiveData<Event<Int>>
//        get() = _error


    init {
        val riotResponse = championRepository.getChampions()

//        viewState.value = Transformations.map(riotResponse) {
//            if (it != null) {
//                currentViewState().copy(champions = it.data)
//            } else {
//                currentViewState().copy(error = R.string.error_something_went_wrong)
//            }
//        }.value

//        _viewState = Transformations.switchMap(riotResponse) {
//            val lol = MutableLiveData<ViewState>()
//            lol.value = currentViewState().copy(champions = it.data)
//            lol
//        } as MutableLiveData<ViewState>
//        _viewState.value = ViewState()


        _viewState = Transformations.switchMap(riotResponse) {
            MutableLiveData<ViewState>().apply {
                this.setValue(currentViewState().copy(champions = it.data))
            }
        } as MutableLiveData<ViewState>




//        _champions = Transformations.switchMap(riotResponse) {
//            val lol = MutableLiveData<Map<String, Champion>>()
//            lol.value = it.data
//            lol
//        } as MutableLiveData<Map<String, Champion>>

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




    fun loadingFinished() {
//        viewState.value = currentViewState().copy(isLoading = false)
    }

    fun onChampionClicked(championId: String) {
        viewAction.value = ViewAction.ShowDetails(championId)
    }

    fun onSettingsClicked() {
        viewAction.value = ViewAction.ShowSettings
    }
}