package com.leaguechampions.ui.championdetails;

import dagger.Module;
import dagger.Provides;

@Module
public class ChampionDetailsPresenterModule {

    @Provides
    public ChampionDetailsPresenter.ChampionDetailsView provideView(ChampionDetailsActivity activity) {
        return activity;
    }
}
