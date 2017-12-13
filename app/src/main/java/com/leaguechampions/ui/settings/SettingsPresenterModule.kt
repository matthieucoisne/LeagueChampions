package com.leaguechampions.ui.settings

import com.leaguechampions.injection.scope.ActivityScoped
import dagger.Module
import dagger.Provides

@Module
class SettingsPresenterModule(private val view: SettingsPresenter.SettingsView) {
    @ActivityScoped
    @Provides
    internal fun provideView(): SettingsPresenter.SettingsView {
        return view
    }
}
