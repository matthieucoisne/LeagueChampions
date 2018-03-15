package com.leaguechampions.ui.champions

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.repository.ChampionRepository
import com.leaguechampions.data.repository.Resource
import javax.inject.Inject


class ChampionsViewModel @Inject constructor(private val championRepository: ChampionRepository) : ViewModel() {

    val riotResponse: LiveData<Resource<Map<String, Champion>>> = championRepository.getChampions()
}