package com.leaguechampions.ui.championdetails

import dagger.Module
import dagger.Provides

@Module
class ChampionDetailsPresenterModule {
    @Provides
    internal fun provideView(activity: ChampionDetailsActivity): ChampionDetailsPresenter.ChampionDetailsView {
        return activity
    }
}
