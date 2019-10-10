package com.leaguechampions.features.champions.data

import android.content.Context
import com.leaguechampions.features.champions.data.local.ChampionDao
import com.leaguechampions.features.champions.data.remote.ChampionApi
import com.leaguechampions.features.champions.data.repository.ChampionDataRepository
import com.leaguechampions.features.champions.domain.repository.ChampionRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.mock.MockRetrofit
import javax.inject.Named
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
    internal fun provideChampionApi(context: Context, retrofit: Retrofit, mockRetrofit: MockRetrofit, @Named("mockMode") mockMode: Boolean): ChampionApi {
//        return if (mockMode) {
//            MockApi(context, mockRetrofit.create(ChampionApi::class.java))
//        } else {
        return retrofit.create(ChampionApi::class.java)
//        }
    }
}
