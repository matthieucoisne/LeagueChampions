package com.leaguechampions.dagger.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.leaguechampions.core.Const;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache, HttpLoggingInterceptor loggingInterceptor, Interceptor requestInterceptor) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(requestInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Context context) {
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @Provides
    @Singleton
    Interceptor provideRequestInterceptor() {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder builder = request.newBuilder();
                builder.addHeader("X-Riot-Token", Const.API_KEY);
                return chain.proceed(builder.build());
            }
        };
    }
}
