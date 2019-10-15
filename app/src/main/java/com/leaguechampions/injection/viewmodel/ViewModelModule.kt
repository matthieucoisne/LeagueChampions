package com.leaguechampions.injection.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leaguechampions.features.champions.presentation.championdetails.ChampionDetailsViewModel
import com.leaguechampions.features.champions.presentation.champions.ChampionsViewModel
import com.leaguechampions.libraries.core.injection.ViewModelFactory
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
