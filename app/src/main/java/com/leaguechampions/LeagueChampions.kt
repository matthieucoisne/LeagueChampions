package com.leaguechampions

import com.leaguechampions.injection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class LeagueChampions : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

//        Picasso.setSingletonInstance(appComponent.getPicasso());
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}
