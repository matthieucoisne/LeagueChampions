package com.leaguechampions.dagger.component;

import com.leaguechampions.activity.ChampionDetailsActivity;
import com.leaguechampions.dagger.AppComponent;
import com.leaguechampions.dagger.module.ChampionDetailsPresenterModule;
import com.leaguechampions.dagger.scope.ActivityScoped;

import dagger.Component;

@ActivityScoped
@Component(dependencies = {AppComponent.class}, modules = {ChampionDetailsPresenterModule.class})
public interface ChampionDetailsComponent {
    void inject(ChampionDetailsActivity activity);
}
