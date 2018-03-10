package com.leaguechampions.injection.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.leaguechampions.ui.championdetails.ChampionDetailsViewModel
import com.leaguechampions.ui.champions.ChampionsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ChampionsViewModel::class)
    internal abstract fun bindChampionsViewModel(championsViewModel: ChampionsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChampionDetailsViewModel::class)
    internal abstract fun bindChampionDetailsViewModel(championDetailsViewModel: ChampionDetailsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}