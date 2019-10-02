package com.leaguechampions.injection.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leaguechampions.core.injection.ViewModelFactory
import com.leaguechampions.features.champions.champions.ChampionsViewModel
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
    @ViewModelKey(com.leaguechampions.features.champions.championdetails.ChampionDetailsViewModel::class)
    internal abstract fun bindChampionDetailsViewModel(championDetailsViewModel: com.leaguechampions.features.champions.championdetails.ChampionDetailsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
