package com.leaguechampions.libraries.core.injection

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.jakewharton.picasso.OkHttp3Downloader
import com.leaguechampions.libraries.core.data.local.Const
import com.leaguechampions.libraries.core.data.remote.MockApi
import com.leaguechampions.libraries.core.data.remote.RiotApi
import com.leaguechampions.libraries.core.utils.picasso.PicassoMockRequestHandler
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import java.util.concurrent.TimeUnit
import javax.inject.Named
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
    internal fun provideMockRetrofit(retrofit: Retrofit, networkBehavior: NetworkBehavior): MockRetrofit {
        return MockRetrofit.Builder(retrofit)
                .networkBehavior(networkBehavior)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideNetworkBehavior(): NetworkBehavior {
        val networkBehavior = NetworkBehavior.create()
        networkBehavior.setDelay(Const.BEHAVIOR_DELAY_MILLIS, TimeUnit.MILLISECONDS)
        networkBehavior.setErrorPercent(0)
        networkBehavior.setFailurePercent(0)
        networkBehavior.setVariancePercent(0)
        return networkBehavior
    }

    @Provides
    @Singleton
    internal fun provideApi(context: Context, retrofit: Retrofit, mockRetrofit: MockRetrofit, @Named("mockMode") mockMode: Boolean): RiotApi {
        return if (mockMode) {
            MockApi(context, mockRetrofit.create(RiotApi::class.java))
        } else {
            retrofit.create(RiotApi::class.java)
        }
    }

    @Provides
    @Singleton
    internal fun providePicasso(context: Context, okHttpClient: OkHttpClient, @Named("mockMode") mockMode: Boolean): Picasso {
        val builder = Picasso.Builder(context)
        if (mockMode) {
            val networkBehavior = NetworkBehavior.create()
            networkBehavior.setDelay(0, TimeUnit.MILLISECONDS)
            networkBehavior.setErrorPercent(0)
            networkBehavior.setFailurePercent(0)
            networkBehavior.setVariancePercent(0)
            builder.addRequestHandler(PicassoMockRequestHandler(networkBehavior, context.assets))
        }
        builder.downloader(OkHttp3Downloader(okHttpClient))
        return builder.build()
    }

    @Provides
    @Named("mockMode")
    internal fun provideMockMode(preferences: SharedPreferences): Boolean {
        return preferences.getBoolean(Const.PREF_KEY_MOCK_MODE, false)
    }
}
