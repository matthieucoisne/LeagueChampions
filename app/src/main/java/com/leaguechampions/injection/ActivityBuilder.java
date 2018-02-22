package com.leaguechampions.injection;

import com.leaguechampions.ui.championdetails.ChampionDetailsActivity;
import com.leaguechampions.ui.champions.ChampionsActivity;
import com.leaguechampions.ui.settings.SettingsActivity;
import com.leaguechampions.ui.settings.SettingsPresenterModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract ChampionsActivity contributeChampionsActivity();

    @ContributesAndroidInjector
    abstract ChampionDetailsActivity contributeChampionDetailsActivity();

    @ContributesAndroidInjector(modules = {SettingsPresenterModule.class})
    abstract SettingsActivity contributeSettingsActivity();
}
