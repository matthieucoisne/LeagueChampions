package com.leaguechampions.features.champions.domain.usecase

import com.leaguechampions.features.champions.domain.model.Champion
import com.leaguechampions.features.champions.domain.repository.ChampionRepository
import com.leaguechampions.libraries.core.domain.repository.RiotRepository

class GetChampionDetailsUseCase(
    private val riotRepository: RiotRepository,
    private val championRepository: ChampionRepository
) {

    suspend fun execute(championId: String): Champion {
        val version = riotRepository.getVersion()
        return championRepository.getChampionDetails(championId, version)
    }
}
