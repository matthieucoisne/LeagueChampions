package com.leaguechampions.dagger.component;

import com.leaguechampions.activity.ChampionDetailsActivity;
import com.leaguechampions.activity.ChampionsActivity;
import com.leaguechampions.activity.SettingsActivity;
import com.leaguechampions.dagger.module.AppModule;
import com.leaguechampions.dagger.module.NetworkModule;
import com.leaguechampions.module.DataModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, DataModule.class})
public interface AppComponent {
    void inject(ChampionsActivity activity);
    void inject(ChampionDetailsActivity activity);
    void inject(SettingsActivity activity);
}
