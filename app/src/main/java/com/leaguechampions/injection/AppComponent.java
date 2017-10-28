package com.leaguechampions.injection;

import android.content.SharedPreferences;

import com.leaguechampions.data.remote.Api;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, DataModule.class})
public interface AppComponent {
    Api provideApi();
    Picasso providePicasso();
    SharedPreferences provideSharedPreferences();
}
