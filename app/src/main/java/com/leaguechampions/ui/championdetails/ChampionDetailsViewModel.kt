package com.leaguechampions.ui.championdetails

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.repository.ChampionRepository
import com.leaguechampions.data.repository.Resource
import javax.inject.Inject

class ChampionDetailsViewModel @Inject constructor(private val championRepository: ChampionRepository) : ViewModel() {

    private val championId = MutableLiveData<String>()

    val riotResponse: LiveData<Resource<Champion>> = Transformations.switchMap(championId) { championId ->
        if (championId == null) {
            // TODO return AbsentLiveData.create();
            null
        } else {
            championRepository.getChampionDetails(championId)
        }
    }

    fun setChampionId(championId: String?) {
        if (championId == this.championId.value) {
            return
        }
        this.championId.value = championId
    }
}
