package com.leaguechampions.dagger.module;

import com.leaguechampions.presenter.ChampionDetailsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ChampionDetailsPresenterModule {

    private final ChampionDetailsPresenter.ChampionDetailsView view;

    public ChampionDetailsPresenterModule(ChampionDetailsPresenter.ChampionDetailsView view) {
        this.view = view;
    }

    @Provides
    public ChampionDetailsPresenter.ChampionDetailsView provideView() {
        return view;
    }
}
