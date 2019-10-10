package com.leaguechampions.features.champions.data.remote

import com.leaguechampions.features.champions.data.model.ChampionEntity
import com.leaguechampions.libraries.core.data.model.RiotResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * http://ddragon.leagueoflegends.com/cdn/7.15.1/data/en_US/champion.json
 * http://ddragon.leagueoflegends.com/cdn/7.15.1/data/en_US/champion/Riven.json
 */
interface ChampionApi {

    @GET("cdn/{version}/data/en_US/champion.json")
    suspend fun getChampions(@Path("version") version: String): RiotResponse<ChampionEntity>

    @GET("cdn/{version}/data/en_US/champion/{championId}.json")
    suspend fun getChampionDetails(@Path("championId") championId: String, @Path("version") version: String): RiotResponse<ChampionEntity>
}
