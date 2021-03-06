package com.leaguechampions.injection

import com.leaguechampions.features.champions.presentation.championdetails.ChampionDetailsFragment
import com.leaguechampions.features.champions.presentation.champions.ChampionsActivity
import com.leaguechampions.features.champions.presentation.champions.ChampionsFragment
import com.leaguechampions.features.settings.presentation.settings.SettingsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    internal abstract fun contributeChampionsActivity(): ChampionsActivity

    @ContributesAndroidInjector
    internal abstract fun contributeChampionsFragment(): ChampionsFragment

    @ContributesAndroidInjector
    internal abstract fun contributeChampionDetailsFragment(): ChampionDetailsFragment

    @ContributesAndroidInjector
    internal abstract fun contributeSettingsActivity(): SettingsActivity
}
