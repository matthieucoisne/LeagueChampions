package com.leaguechampions.injection;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.leaguechampions.data.local.Const;
import com.leaguechampions.data.remote.Api;
import com.leaguechampions.data.remote.MockApi;
import com.leaguechampions.utils.picasso.PicassoMockRequestHandler;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

@Module
public class DataModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Const.URL_BASE)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    MockRetrofit provideMockRetrofit(Retrofit retrofit, NetworkBehavior networkBehavior) {
        return new MockRetrofit.Builder(retrofit)
                .networkBehavior(networkBehavior)
                .build();
    }

    @Provides
    @Singleton
    NetworkBehavior provideNetworkBehavior() {
        NetworkBehavior networkBehavior = NetworkBehavior.create();
        networkBehavior.setDelay(Const.BEHAVIOR_DELAY_MILLIS, TimeUnit.MILLISECONDS);
        networkBehavior.setErrorPercent(0);
        networkBehavior.setFailurePercent(0);
        networkBehavior.setVariancePercent(0);
        return networkBehavior;
    }

    @Provides
    @Singleton
    Api provideApi(Context context, Retrofit retrofit, MockRetrofit mockRetrofit, @Named("mockMode") boolean mockMode) {
        if (mockMode) {
            return new MockApi(context, mockRetrofit.create(Api.class));
        } else {
            return retrofit.create(Api.class);
        }
    }

    @Provides
    @Singleton
    Picasso providePicasso(Context context, OkHttpClient okHttpClient, @Named("mockMode") boolean mockMode) {
        Picasso.Builder builder = new Picasso.Builder(context);
        if (mockMode) {
            NetworkBehavior networkBehavior = NetworkBehavior.create();
            networkBehavior.setDelay(0, TimeUnit.MILLISECONDS);
            networkBehavior.setErrorPercent(0);
            networkBehavior.setFailurePercent(0);
            networkBehavior.setVariancePercent(0);
            builder.addRequestHandler(new PicassoMockRequestHandler(networkBehavior, context.getAssets()));
        }
        builder.downloader(new OkHttp3Downloader(okHttpClient));
        return builder.build();
    }

    @Provides
    @Named("mockMode")
    boolean provideMockMode(SharedPreferences preferences) {
        return preferences.getBoolean(Const.PREF_KEY_MOCK_MODE, false);
    }
}
