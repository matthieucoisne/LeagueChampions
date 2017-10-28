package com.leaguechampions.ui.settings;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingsPresenterModule {

    private final SettingsPresenter.SettingsView view;

    public SettingsPresenterModule(SettingsPresenter.SettingsView view) {
        this.view = view;
    }

    @Provides
    public SettingsPresenter.SettingsView provideView() {
        return view;
    }
}
