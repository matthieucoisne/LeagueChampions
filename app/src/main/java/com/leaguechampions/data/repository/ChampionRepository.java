package com.leaguechampions.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.leaguechampions.R;
import com.leaguechampions.data.model.Resource;
import com.leaguechampions.data.model.RiotResponse;
import com.leaguechampions.data.remote.Api;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChampionRepository {

    private Api api;

    @Inject
    public ChampionRepository(Api api) {
        this.api = api;
    }

    public LiveData<Resource<RiotResponse>> getChampions() {
        final MutableLiveData<Resource<RiotResponse>> data = new MutableLiveData<>();

        api.getChampions().enqueue(new Callback<RiotResponse>() {
            @Override
            public void onResponse(@NonNull Call<RiotResponse> call, @NonNull Response<RiotResponse> response) {
                if (response.isSuccessful()) {
                    RiotResponse riotResponse = response.body();
                    data.setValue(Resource.success(riotResponse));
                } else {
                    // TODO find a way to add `response.code()`
                    data.setValue(Resource.error(R.string.error_code, null));
                }
            }

            @Override
            public void onFailure(@NonNull Call<RiotResponse> call, @NonNull Throwable t) {
                if (t instanceof IOException) {
                    data.setValue(Resource.error(R.string.error_io, null));
                } else {
                    data.setValue(Resource.error(R.string.error_something_went_wrong, null));
                }
            }
        });

        return data;
    }

    public LiveData<Resource<RiotResponse>> getChampionDetails(final String championId) {
        final MutableLiveData<Resource<RiotResponse>> data = new MutableLiveData<>();

        api.getChampion(championId).enqueue(new Callback<RiotResponse>() {
            @Override
            public void onResponse(@NonNull Call<RiotResponse> call, @NonNull Response<RiotResponse> response) {
                if (response.isSuccessful()) {
                    RiotResponse riotResponse = response.body();
                    data.setValue(Resource.success(riotResponse));
                } else {
                    // TODO find a way to add `response.code()`
                    data.setValue(Resource.error(R.string.error_code, null));
                }
            }

            @Override
            public void onFailure(@NonNull Call<RiotResponse> call, @NonNull Throwable t) {
                if (t instanceof IOException) {
                    data.setValue(Resource.error(R.string.error_io, null));
                } else {
                    data.setValue(Resource.error(R.string.error_something_went_wrong, null));
                }
            }
        });

        return data;
    }
}
