package com.leaguechampions.datasource.remote;

import android.content.Context;

import com.google.gson.Gson;
import com.leaguechampions.model.Champion;
import com.leaguechampions.model.Champions;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okio.BufferedSource;
import okio.Okio;
import retrofit2.Call;
import retrofit2.http.Path;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.Calls;

public class MockApi implements Api {

    private final Context context;
    private final BehaviorDelegate<Api> delegate;
    private final Gson gson;

    public MockApi(Context context, BehaviorDelegate<Api> delegate) {
        this.context = context;
        this.delegate = delegate;
        this.gson = new Gson();
    }

    private String getStringFromFile(String filePath) throws IOException {
        InputStream in = context.getAssets().open(filePath);
        BufferedSource source = Okio.buffer(Okio.source(in));
        return source.readString(Charset.defaultCharset());
    }

    private <T> T getDataFromFile(String filePath, Type type) throws IOException {
        return gson.fromJson(getStringFromFile(filePath), type);
    }

    @Override
    public Call<Champions> getChampions() {
        String filePath = "json/v3/getChampions.json";

        try {
            Champions champions = getDataFromFile(filePath, Champions.class);
            return delegate.returning(Calls.response(champions)).getChampions();
        } catch (IOException e) {
            return Calls.failure(e);
        }
    }

    @Override
    public Call<Champion> getChampion(@Path("championId") int championId) {
        String filePath = "json/v3/getChampion.json";

        try {
            Champion champion = getDataFromFile(filePath, Champion.class);
            return delegate.returning(Calls.response(champion)).getChampion(championId);
        } catch (IOException e) {
            return Calls.failure(e);
        }
    }
}
