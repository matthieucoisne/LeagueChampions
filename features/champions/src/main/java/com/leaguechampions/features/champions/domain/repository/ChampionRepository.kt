package com.leaguechampions.features.champions.domain.repository

import androidx.lifecycle.LiveData
import com.leaguechampions.features.champions.domain.model.Champion
import com.leaguechampions.libraries.core.utils.Resource

interface ChampionRepository {
    suspend fun getChampions(version: String): LiveData<Resource<List<Champion>>>
    suspend fun getChampionDetails(championId: String, version: String): LiveData<Resource<Champion>>
}
