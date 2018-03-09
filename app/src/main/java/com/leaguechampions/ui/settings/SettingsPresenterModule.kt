package com.leaguechampions.ui.settings

import dagger.Module
import dagger.Provides

@Module
class SettingsPresenterModule {
    @Provides
    internal fun provideView(activity: SettingsActivity): SettingsPresenter.SettingsView {
        return activity
    }
}
