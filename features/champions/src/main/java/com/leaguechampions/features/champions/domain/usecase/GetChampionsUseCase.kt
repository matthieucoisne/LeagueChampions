package com.leaguechampions.features.champions.domain.usecase

import com.leaguechampions.features.champions.domain.model.Champion
import com.leaguechampions.features.champions.domain.repository.ChampionRepository
import com.leaguechampions.libraries.core.data.repository.RiotRepository

class GetChampionsUseCase(
    private val riotRepository: RiotRepository,
    private val championRepository: ChampionRepository
) {

    suspend fun execute(): List<Champion> {
        val version = riotRepository.getVersion()
        return championRepository.getChampions(version)
    }
}
