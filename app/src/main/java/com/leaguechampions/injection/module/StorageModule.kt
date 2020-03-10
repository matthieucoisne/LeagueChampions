package com.leaguechampions.injection.module

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.leaguechampions.data.local.AppDatabase
import com.leaguechampions.features.champions.data.local.ChampionDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    internal fun providesSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson { // TODO use Moshi
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideChampionDao(context: Context): ChampionDao {
        return AppDatabase.getDatabase(context).championDao()
    }
}
