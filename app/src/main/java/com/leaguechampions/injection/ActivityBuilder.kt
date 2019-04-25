package com.leaguechampions.injection

import com.leaguechampions.ui.championdetails.ChampionDetailsActivity
import com.leaguechampions.ui.champions.ChampionsActivity
import com.leaguechampions.ui.champions.ChampionsFragment
import com.leaguechampions.ui.settings.SettingsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    internal abstract fun contributeChampionsActivity(): ChampionsActivity

    @ContributesAndroidInjector
    internal abstract fun contributeChampionsFragment(): ChampionsFragment

    @ContributesAndroidInjector
    internal abstract fun contributeChampionDetailsActivity(): ChampionDetailsActivity

    @ContributesAndroidInjector
    internal abstract fun contributeSettingsActivity(): SettingsActivity
}