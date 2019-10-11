package com.leaguechampions.features.champions.domain

import com.leaguechampions.features.champions.domain.repository.ChampionRepository
import com.leaguechampions.features.champions.domain.usecase.GetChampionDetailsUseCase
import com.leaguechampions.features.champions.domain.usecase.GetChampionsUseCase
import com.leaguechampions.libraries.core.domain.repository.RiotRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ChampionDomainModule {

    @Provides
    @Singleton
    fun provideChampionDetailsUseCase(riotRepository: RiotRepository, championRepository: ChampionRepository): GetChampionDetailsUseCase {
        return GetChampionDetailsUseCase(riotRepository, championRepository)
    }

    @Provides
    @Singleton
    fun provideChampionsUseCase(riotRepository: RiotRepository, championRepository: ChampionRepository): GetChampionsUseCase {
        return GetChampionsUseCase(riotRepository, championRepository)
    }
}
