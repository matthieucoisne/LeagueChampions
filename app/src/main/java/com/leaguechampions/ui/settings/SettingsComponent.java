package com.leaguechampions.ui.settings;

import com.leaguechampions.injection.AppComponent;
import com.leaguechampions.injection.scope.ActivityScoped;

import dagger.Component;

@ActivityScoped
@Component(dependencies = {AppComponent.class}, modules = {SettingsPresenterModule.class})
public interface SettingsComponent {
    void inject(SettingsActivity activity);
}
