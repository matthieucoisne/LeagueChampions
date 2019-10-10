package com.leaguechampions.libraries.core.data.remote

import com.leaguechampions.libraries.core.data.model.RiotRealm
import retrofit2.http.GET

/**
 * http://ddragon.leagueoflegends.com/realms/na.json
 */
interface RiotApi {

    @GET("realms/na.json")
    suspend fun getRealm(): RiotRealm
}
