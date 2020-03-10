package com.leaguechampions.features.champions.injection

import com.leaguechampions.features.champions.data.local.ChampionDao
import com.leaguechampions.features.champions.data.remote.ChampionApi
import com.leaguechampions.features.champions.data.repository.ChampionDataRepository
import com.leaguechampions.features.champions.domain.repository.ChampionRepository
import com.leaguechampions.features.champions.domain.usecase.GetChampionDetailsUseCase
import com.leaguechampions.features.champions.domain.usecase.GetChampionsUseCase
import com.leaguechampions.libraries.core.domain.repository.RiotRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ChampionModule {

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

    @Provides
    @Singleton
    fun provideChampionRepository(championApi: ChampionApi, championDao: ChampionDao): ChampionRepository {
        return ChampionDataRepository(championApi, championDao)
    }

    @Provides
    @Singleton
    internal fun provideChampionApi(retrofit: Retrofit): ChampionApi {
        return retrofit.create(ChampionApi::class.java)
    }
}
