package com.leaguechampions.core;

import android.app.Application;

import com.leaguechampions.dagger.AppComponent;
import com.leaguechampions.dagger.DaggerAppComponent;
import com.leaguechampions.dagger.AppModule;
import com.leaguechampions.dagger.NetworkModule;
import com.leaguechampions.module.DataModule;

public class LeagueChampions extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this.getApplicationContext()))
                .networkModule(new NetworkModule())
                .dataModule(new DataModule(Const.URL))
                .build();

//        Picasso.setSingletonInstance(appComponent.getPicasso());
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
