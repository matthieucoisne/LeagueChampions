package com.leaguechampions.injection

import com.leaguechampions.LeagueChampions
import com.leaguechampions.injection.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilder::class,
    AppModule::class,
    NetworkModule::class,
    DataModule::class,
    ViewModelModule::class
])
interface AppComponent : AndroidInjector<LeagueChampions> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<LeagueChampions>()
}