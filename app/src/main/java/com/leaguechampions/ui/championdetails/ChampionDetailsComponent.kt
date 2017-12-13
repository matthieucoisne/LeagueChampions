package com.leaguechampions.ui.championdetails

import com.leaguechampions.injection.AppComponent
import com.leaguechampions.injection.scope.ActivityScoped

import dagger.Component

@ActivityScoped
@Component(dependencies = [AppComponent::class], modules = [ChampionDetailsPresenterModule::class])
interface ChampionDetailsComponent {
    fun inject(activity: ChampionDetailsActivity)
}
