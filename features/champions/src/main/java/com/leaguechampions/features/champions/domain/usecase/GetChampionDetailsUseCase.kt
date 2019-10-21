package com.leaguechampions.features.champions.domain.usecase

import androidx.lifecycle.LiveData
import com.leaguechampions.features.champions.domain.model.Champion
import com.leaguechampions.features.champions.domain.repository.ChampionRepository
import com.leaguechampions.libraries.core.domain.repository.RiotRepository
import com.leaguechampions.libraries.core.utils.Resource

class GetChampionDetailsUseCase(
    private val riotRepository: RiotRepository,
    private val championRepository: ChampionRepository
) {

    suspend fun execute(championId: String): LiveData<Resource<Champion>> {
        val version = riotRepository.getVersion()
        return championRepository.getChampionDetails(championId, version)
    }
}
