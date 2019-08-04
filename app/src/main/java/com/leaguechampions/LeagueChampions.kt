package com.leaguechampions

import com.leaguechampions.injection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import timber.log.Timber.DebugTree

class LeagueChampions : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(DebugTree())
//        Picasso.setSingletonInstance(appComponent.getPicasso());
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}




// repeat() a la place de for
// channels pour communiquer entre coroutines
// runBlocking utiliser pour les test ou rares cas
// coroutineScope pour demarrer une coroutine, puis launch fireAndForget ou async pour garder le defered result et possiblement attendre la fin

/*
fun List<User>.aggregate(): List<User> {
    return groupBy { it.login }
        .map { (key, value) -> User(key, value.sumBy { it.contributions }) }
        .sortedByDescending { it.contributions }
}
 */


// TODO
// MODEL MAP UI MODEL
// SAVED STATE HANDLE
// IMPROVE ROOM
// MOTION LAYOUT
// MVI
// BETTER UNDERSTAND COROUTINES
// KOTLIN FLOW ?
// KOIN / TOOTHPICK