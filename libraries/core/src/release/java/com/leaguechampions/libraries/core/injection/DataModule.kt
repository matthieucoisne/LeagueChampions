package com.leaguechampions.libraries.core.injection

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.picasso.OkHttp3Downloader
import com.leaguechampions.libraries.core.data.local.Const
import com.leaguechampions.libraries.core.data.remote.Api
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Const.URL_BASE)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    internal fun providePicasso(context: Context, okHttpClient: OkHttpClient): Picasso {
        return Picasso.Builder(context)
                .downloader(OkHttp3Downloader(okHttpClient))
                .build()
    }
}
