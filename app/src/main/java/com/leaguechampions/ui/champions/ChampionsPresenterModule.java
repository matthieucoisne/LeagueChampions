package com.leaguechampions.ui.champions;

import dagger.Module;
import dagger.Provides;

@Module
public class ChampionsPresenterModule {

    @Provides
    public ChampionsPresenter.ChampionsView provideView(ChampionsActivity activity) {
        return activity;
    }
}
