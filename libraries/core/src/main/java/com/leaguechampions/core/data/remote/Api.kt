package com.leaguechampions.core.data.remote

import com.leaguechampions.core.data.model.Champion
import com.leaguechampions.core.data.model.RiotRealm
import com.leaguechampions.core.data.model.RiotResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * http://ddragon.leagueoflegends.com/realms/na.json
 * http://ddragon.leagueoflegends.com/cdn/7.15.1/data/en_US/champion.json
 * http://ddragon.leagueoflegends.com/cdn/7.15.1/data/en_US/champion/Riven.json
 */
interface Api {

    @GET("realms/na.json")
    suspend fun getRealm(): RiotRealm

    @GET("cdn/{version}/data/en_US/champion.json")
    suspend fun getChampions(@Path("version") version: String): RiotResponse<Champion>

    @GET("cdn/{version}/data/en_US/champion/{championId}.json")
    suspend fun getChampionDetails(@Path("version") version: String, @Path("championId") championId: String): RiotResponse<Champion>
}
