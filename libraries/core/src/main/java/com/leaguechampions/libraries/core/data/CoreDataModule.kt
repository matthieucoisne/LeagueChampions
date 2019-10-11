package com.leaguechampions.libraries.core.data

import com.leaguechampions.libraries.core.data.remote.RiotApi
import com.leaguechampions.libraries.core.data.repository.RiotDataRepository
import com.leaguechampions.libraries.core.domain.repository.RiotRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreDataModule {

    @Provides
    @Singleton
    fun provideRiotRepository(riotApi: RiotApi): RiotRepository {
        return RiotDataRepository(riotApi)
    }
}
