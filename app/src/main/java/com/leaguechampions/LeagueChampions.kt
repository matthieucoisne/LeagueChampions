package com.leaguechampions

import android.app.Application
import com.leaguechampions.data.local.Const
import com.leaguechampions.injection.*

class LeagueChampions : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(applicationContext))
                .networkModule(NetworkModule())
                .dataModule(DataModule(Const.URL))
                .build()

        //        Picasso.setSingletonInstance(appComponent.getPicasso());
    }
}
