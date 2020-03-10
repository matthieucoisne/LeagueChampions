package com.leaguechampions.injection.component

import android.content.Context
import com.leaguechampions.features.champions.injection.ChampionComponent
import com.leaguechampions.features.champions.injection.ChampionModule
import com.leaguechampions.injection.module.NetworkModule
import com.leaguechampions.injection.module.StorageModule
import com.leaguechampions.injection.viewmodel.ViewModelModule
import com.leaguechampions.libraries.core.injection.CoreDataModule
import com.leaguechampions.libraries.core.injection.DataModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    StorageModule::class,
    NetworkModule::class,
    DataModule::class,
    ViewModelModule::class,

    ChampionModule::class,

    CoreDataModule::class
])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun championComponent(): ChampionComponent.Factory
}
