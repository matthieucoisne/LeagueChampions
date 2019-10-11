package com.leaguechampions.libraries.core.domain.repository

interface RiotRepository {
    suspend fun getVersion(): String
}
