package com.leaguechampions.data.repository

import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.remote.Api
import javax.inject.Inject

class ChampionRepository @Inject constructor(private val api: Api) {

    suspend fun getChampions(): List<Champion> {
        val realm = api.getRealmAsync().await()
        val result = api.getChampionsAsync(realm.getVersion()).await()
        return result.data.values.toMutableList().also { it.sort() }
    }

    suspend fun getChampionDetails(championId: String): Champion {
        val realm = api.getRealmAsync().await()
        val result = api.getChampionDetailsAsync(realm.getVersion(), championId).await()
        return result.data.getValue(championId).also { it.version = result.version }
    }
}
