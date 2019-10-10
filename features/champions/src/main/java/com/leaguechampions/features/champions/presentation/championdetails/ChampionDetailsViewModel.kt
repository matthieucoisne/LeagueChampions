package com.leaguechampions.features.champions.presentation.championdetails

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.leaguechampions.features.champions.R
import com.leaguechampions.features.champions.domain.usecase.GetChampionDetailsUseCase
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class ChampionDetailsViewModel @Inject constructor(
    private val getChampionDetailsUseCase: GetChampionDetailsUseCase
) : ViewModel() {

    sealed class ViewState {
        object ShowLoading : ViewState()
        data class ShowChampionDetails(val championDetails: ChampionDetailsUiModel) : ViewState()
        data class ShowError(@StringRes val errorStringId: Int) : ViewState()
    }

    private val _viewState: MutableLiveData<ViewState>
    val viewState: LiveData<ViewState>
        get() = _viewState

    private val championId = MutableLiveData<String>()

//    init {
//        _viewState = championId.switchMap { championId ->
//            liveData(context = viewModelScope.coroutineContext + Dispatchers.Main) {
//                emitSource(getChampionDetailsUseCase.execute(championId))
//            }
//        }.map { resource ->
//            when (resource) {
//                is Resource.Loading -> ViewState.ShowLoading
//                is Resource.Error -> ViewState.ShowError(R.string.error_something_went_wrong)
//                is Resource.Success -> ViewState.ShowChampionDetails(resource.data.toChampionDetailsUiModel())
//            }
//        } as MutableLiveData<ViewState>
//    }

    init {
        _viewState = championId.switchMap { championId ->
            liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
                emit(ViewState.ShowLoading)
                try {
                    emit(ViewState.ShowChampionDetails(getChampionDetailsUseCase.execute(championId).toChampionDetailsUiModel()))
                } catch (e: Exception) {
                    Timber.e(e)
                    val errorStringId = when (e) {
                        is IOException -> R.string.error_io
                        else -> R.string.error_something_went_wrong
                    }
                    emit(ViewState.ShowError(errorStringId))
                }
            }
        } as MutableLiveData<ViewState>
    }

    fun setChampionId(championId: String?) {
        if (this.championId.value != championId) {
            this.championId.value = championId
        }
    }
}
