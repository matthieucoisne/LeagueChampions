package com.leaguechampions.dagger.component;

import com.leaguechampions.activity.ChampionsActivity;
import com.leaguechampions.dagger.AppComponent;
import com.leaguechampions.dagger.module.ChampionsPresenterModule;
import com.leaguechampions.dagger.scope.ActivityScoped;

import dagger.Component;

@ActivityScoped
@Component(dependencies = {AppComponent.class}, modules = {ChampionsPresenterModule.class})
public interface ChampionsComponent {
    void inject(ChampionsActivity activity);
}
