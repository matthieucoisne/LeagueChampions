package com.leaguechampions.injection.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.leaguechampions.ui.championdetails.ChampionDetailsViewModel;
import com.leaguechampions.ui.champions.ChampionsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ChampionsViewModel.class)
    abstract ViewModel bindChampionsViewModel(ChampionsViewModel championsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChampionDetailsViewModel.class)
    abstract ViewModel bindChampionDetailsViewModel(ChampionDetailsViewModel championDetailsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
