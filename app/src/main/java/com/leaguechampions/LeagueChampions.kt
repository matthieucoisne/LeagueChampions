package com.leaguechampions

import android.app.Application
import com.leaguechampions.features.champions.injection.ChampionComponent
import com.leaguechampions.features.champions.injection.ChampionComponentProvider
import com.leaguechampions.injection.component.AppComponent
import com.leaguechampions.injection.component.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

class LeagueChampions : Application(), ChampionComponentProvider {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(DebugTree())
//        Picasso.setSingletonInstance(appComponent.getPicasso());
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun provideChampionComponent(): ChampionComponent {
        return appComponent.championComponent().create()
    }
}
