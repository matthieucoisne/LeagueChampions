package com.leaguechampions

import com.leaguechampions.injection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import timber.log.Timber.DebugTree

class LeagueChampions : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        // TODO Timber
        Timber.plant(DebugTree())
//        Picasso.setSingletonInstance(appComponent.getPicasso());
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}
