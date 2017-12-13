package com.leaguechampions.ui.champions

import com.leaguechampions.injection.AppComponent
import com.leaguechampions.injection.scope.ActivityScoped

import dagger.Component

@ActivityScoped
@Component(dependencies = [AppComponent::class], modules = [ChampionsPresenterModule::class])
interface ChampionsComponent {
    fun inject(activity: ChampionsActivity)
}
