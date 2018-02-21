package com.leaguechampions;

import com.leaguechampions.injection.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class LeagueChampions extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();

//        Picasso.setSingletonInstance(appComponent.getPicasso());
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
