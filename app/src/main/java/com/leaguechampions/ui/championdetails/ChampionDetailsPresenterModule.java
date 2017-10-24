package com.leaguechampions.ui.championdetails;

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
