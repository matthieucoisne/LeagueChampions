package com.leaguechampions.ui.settings

import com.leaguechampions.injection.AppComponent
import com.leaguechampions.injection.scope.ActivityScoped

import dagger.Component

@ActivityScoped
@Component(dependencies = [(AppComponent::class)], modules = [(SettingsPresenterModule::class)])
interface SettingsComponent {
    fun inject(activity: SettingsActivity)
}
