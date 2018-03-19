package com.leaguechampions.injection;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leaguechampions.LeagueChampions;
import com.leaguechampions.data.remote.Api;
import com.leaguechampions.data.repository.ChampionRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module //(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    Context providesContext(LeagueChampions application) {
        return application;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    // TODO put this somewhere else ? in DataModule ?
    @Provides
    @Singleton
    ChampionRepository provideChampionRepository(Api api) {
        return new ChampionRepository(api);
    }
}