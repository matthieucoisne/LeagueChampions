package com.leaguechampions.data.remote

import com.leaguechampions.data.model.RiotRealm
import com.leaguechampions.data.model.RiotResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * http://ddragon.leagueoflegends.com/realms/na.json
 * http://ddragon.leagueoflegends.com/cdn/7.15.1/data/en_US/champion.json
 * http://ddragon.leagueoflegends.com/cdn/7.15.1/data/en_US/champion/Riven.json
 */
interface Api {

    @GET("realms/na.json")
    fun getVersion(): Observable<RiotRealm>

    @GET("cdn/{version}/data/en_US/champion.json")
    fun getChampions(@Path("version") version: String): Observable<RiotResponse>

    @GET("cdn/{version}/data/en_US/champion/{championId}.json")
    fun getChampionDetails(@Path("version") version: String, @Path("championId") championId: String): Observable<RiotResponse>
}
