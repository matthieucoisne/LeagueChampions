package com.leaguechampions.injection

import com.leaguechampions.LeagueChampions
import com.leaguechampions.features.champions.data.ChampionDataModule
import com.leaguechampions.features.champions.domain.ChampionDomainModule
import com.leaguechampions.injection.viewmodel.ViewModelModule
import com.leaguechampions.libraries.core.injection.DataModule
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
    ViewModelModule::class,
    ChampionDomainModule::class,
    ChampionDataModule::class
])
interface AppComponent : AndroidInjector<LeagueChampions> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<LeagueChampions>()
}
