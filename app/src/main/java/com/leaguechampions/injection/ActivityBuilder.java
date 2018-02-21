package com.leaguechampions.injection;

import com.leaguechampions.ui.championdetails.ChampionDetailsActivity;
import com.leaguechampions.ui.championdetails.ChampionDetailsPresenterModule;
import com.leaguechampions.ui.champions.ChampionsActivity;
import com.leaguechampions.ui.champions.ChampionsPresenterModule;
import com.leaguechampions.ui.settings.SettingsActivity;
import com.leaguechampions.ui.settings.SettingsPresenterModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {ChampionsPresenterModule.class})
    abstract ChampionsActivity contributeChampionsActivity();

    @ContributesAndroidInjector(modules = {ChampionDetailsPresenterModule.class})
    abstract ChampionDetailsActivity contributeChampionDetailsActivity();

    @ContributesAndroidInjector(modules = {SettingsPresenterModule.class})
    abstract SettingsActivity contributeSettingsActivity();
}
