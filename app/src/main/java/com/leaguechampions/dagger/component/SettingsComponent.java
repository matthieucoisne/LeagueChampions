package com.leaguechampions.dagger.component;

import com.leaguechampions.activity.SettingsActivity;
import com.leaguechampions.dagger.AppComponent;
import com.leaguechampions.dagger.module.SettingsPresenterModule;
import com.leaguechampions.dagger.scope.ActivityScoped;

import dagger.Component;

@ActivityScoped
@Component(dependencies = {AppComponent.class}, modules = {SettingsPresenterModule.class})
public interface SettingsComponent {
    void inject(SettingsActivity activity);
}
