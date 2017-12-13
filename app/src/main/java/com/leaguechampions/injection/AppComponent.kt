package com.leaguechampions.injection

import android.content.SharedPreferences
import com.leaguechampions.data.remote.Api
import com.squareup.picasso.Picasso
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DataModule::class])
interface AppComponent {
    fun provideApi(): Api
    fun providePicasso(): Picasso
    fun provideSharedPreferences(): SharedPreferences
}
