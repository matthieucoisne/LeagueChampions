package com.leaguechampions.libraries.core.data.repository

import com.leaguechampions.libraries.core.data.remote.RiotApi
import com.leaguechampions.libraries.core.domain.repository.RiotRepository
import javax.inject.Inject

class RiotDataRepository @Inject constructor(
    private val riotApi: RiotApi
) : RiotRepository {

    override suspend fun getVersion(): String {
        return riotApi.getRealm().getVersion()
    }
}
