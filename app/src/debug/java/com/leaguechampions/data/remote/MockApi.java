package com.leaguechampions.data.remote;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.leaguechampions.data.model.RiotRealm;
import com.leaguechampions.data.model.RiotResponse;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import io.reactivex.Observable;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.mock.BehaviorDelegate;

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
    public Observable<RiotRealm> getVersion() {
        String filePath = "json/getVersion.json";

        try {
            RiotRealm riotRealm = getDataFromFile(filePath, RiotRealm.class);
            return delegate.returningResponse(riotRealm).getVersion();
        } catch (IOException e) {
            return Observable.error(e);
        }
    }

    @Override
    public Observable<RiotResponse> getChampions(String version) {
        String filePath = "json/getChampions.json";

        try {
            RiotResponse riotResponse = getDataFromFile(filePath, RiotResponse.class);
            return delegate.returningResponse(riotResponse).getChampions(version);
        } catch (IOException e) {
            return Observable.error(e);
        }
    }

    @Override
    public Observable<RiotResponse> getChampion(String version, String championId) {
        String filePath = "json/getChampion.json";

        try {
            // The file "getChampion.json" is a map (championId -> data) containing data for "Riven" only.
            // We need to replace the key "Riven" with the provided championId.
            String json = getStringFromFile(filePath);
            JsonObject jsonObjectResponse = gson.fromJson(json, JsonElement.class).getAsJsonObject();
            if (!"Riven".equals(championId)) {
                JsonObject jsonObjectData = jsonObjectResponse.getAsJsonObject("data");
                jsonObjectData.add(championId, jsonObjectData.get("Riven"));
                jsonObjectData.remove("Riven");
            }

            RiotResponse riotResponse = gson.fromJson(jsonObjectResponse, RiotResponse.class);
            return delegate.returningResponse(riotResponse).getChampion(version, championId);
        } catch (IOException e) {
            return Observable.error(e);
        }
    }
}
