package com.leaguechampions.ui.champions

import dagger.Module
import dagger.Provides

@Module
class ChampionsPresenterModule {
    @Provides
    internal fun provideView(activity: ChampionsActivity): ChampionsPresenter.ChampionsView {
        return activity
    }
}
