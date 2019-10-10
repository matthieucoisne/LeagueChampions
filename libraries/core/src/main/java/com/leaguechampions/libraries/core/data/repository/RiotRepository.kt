package com.leaguechampions.libraries.core.data.repository

import com.leaguechampions.libraries.core.data.remote.RiotApi
import javax.inject.Inject

class RiotRepository @Inject constructor(
    private val riotApi: RiotApi
) {
    suspend fun getVersion(): String {
        return riotApi.getRealm().getVersion()
    }
}
