package com.leaguechampions.injection

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.leaguechampions.LeagueChampions
import com.leaguechampions.data.remote.Api
import com.leaguechampions.data.repository.ChampionRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton



@Module //(includes = ViewModelModule.class)
class AppModule {

    @Provides
    @Singleton
    internal fun providesContext(application: LeagueChampions): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun providesSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    // TODO put this somewhere else ? in DataModule ?
    @Provides
    @Singleton
    fun provideChampionRepository(api: Api, context: Context): ChampionRepository {
        return ChampionRepository(api, context)
    }
}
