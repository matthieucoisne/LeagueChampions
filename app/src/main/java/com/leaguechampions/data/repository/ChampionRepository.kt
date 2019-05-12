package com.leaguechampions.data.repository

import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.remote.Api
import javax.inject.Inject

class ChampionRepository @Inject constructor(private val api: Api) {

    suspend fun getChampions(): List<Champion> {
        val realm = api.getRealm().await()
        val result = api.getChampions(realm.getVersion()).await()
        val champions = result.data.values.toMutableList()
        champions.sort()
        return champions
    }

    suspend fun getChampionDetails(championId: String): Champion {
        val realm = api.getRealm().await()
        val result = api.getChampionDetails(realm.getVersion(), championId).await()
        val champion = result.data.getValue(championId)
        champion.version = result.version
        return champion
    }
}
