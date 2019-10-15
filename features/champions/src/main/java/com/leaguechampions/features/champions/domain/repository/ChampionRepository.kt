package com.leaguechampions.features.champions.domain.repository

import com.leaguechampions.features.champions.domain.model.Champion

interface ChampionRepository {
    suspend fun getChampions(version: String): List<Champion>
    suspend fun getChampionDetails(championId: String, version: String): Champion
//    suspend fun getChampionWithCacheFromDb(championId: String): LiveData<Resource<Champion>>
}
