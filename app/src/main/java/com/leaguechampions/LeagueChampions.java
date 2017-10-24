package com.leaguechampions;

import android.app.Application;

import com.leaguechampions.data.local.Const;
import com.leaguechampions.injection.AppComponent;
import com.leaguechampions.injection.AppModule;
import com.leaguechampions.injection.DaggerAppComponent;
import com.leaguechampions.injection.DataModule;
import com.leaguechampions.injection.NetworkModule;

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
