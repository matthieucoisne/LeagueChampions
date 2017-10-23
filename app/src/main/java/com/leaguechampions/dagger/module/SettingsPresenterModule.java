package com.leaguechampions.dagger.module;

import com.leaguechampions.presenter.SettingsPresenter;

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
