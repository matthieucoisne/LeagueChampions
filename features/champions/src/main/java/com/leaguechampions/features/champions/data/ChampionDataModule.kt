package com.leaguechampions.features.champions.data

import com.leaguechampions.features.champions.data.local.ChampionDao
import com.leaguechampions.features.champions.data.remote.ChampionApi
import com.leaguechampions.features.champions.data.repository.ChampionDataRepository
import com.leaguechampions.features.champions.domain.repository.ChampionRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ChampionDataModule {

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
