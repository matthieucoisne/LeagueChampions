package com.leaguechampions.ui.settings;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingsPresenterModule {

    @Provides
    public SettingsPresenter.SettingsView provideView(SettingsActivity activity) {
        return activity;
    }
}
