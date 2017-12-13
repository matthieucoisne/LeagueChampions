package com.leaguechampions.ui.champions

import com.leaguechampions.injection.scope.ActivityScoped
import dagger.Module
import dagger.Provides

@Module
class ChampionsPresenterModule(private val view: ChampionsPresenter.ChampionsView) {
    @ActivityScoped
    @Provides
    internal fun provideView(): ChampionsPresenter.ChampionsView {
        return view
    }
}
