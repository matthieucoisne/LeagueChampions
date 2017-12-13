package com.leaguechampions.ui.championdetails

import com.leaguechampions.injection.scope.ActivityScoped
import dagger.Module
import dagger.Provides

@Module
class ChampionDetailsPresenterModule(private val view: ChampionDetailsPresenter.ChampionDetailsView) {
    @ActivityScoped
    @Provides
    internal fun provideView(): ChampionDetailsPresenter.ChampionDetailsView {
        return view
    }
}
