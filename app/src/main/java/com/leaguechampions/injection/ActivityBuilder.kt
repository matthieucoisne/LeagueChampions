package com.leaguechampions.injection

import com.leaguechampions.ui.championdetails.ChampionDetailsActivity
import com.leaguechampions.ui.champions.ChampionsActivity
import com.leaguechampions.ui.settings.SettingsActivity
import com.leaguechampions.ui.settings.SettingsPresenterModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    internal abstract fun contributeChampionsActivity(): ChampionsActivity

    @ContributesAndroidInjector
    internal abstract fun contributeChampionDetailsActivity(): ChampionDetailsActivity

    @ContributesAndroidInjector(modules = [(SettingsPresenterModule::class)])
    internal abstract fun contributeSettingsActivity(): SettingsActivity
}