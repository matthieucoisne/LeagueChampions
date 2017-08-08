package com.leaguechampions.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.leaguechampions.core.Const;
import com.leaguechampions.model.Champion;
import com.leaguechampions.datasource.remote.Api;
import com.leaguechampions.model.RiotResponse;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChampionDetailsPresenter {

    private ChampionDetailsViewable viewable;

    private final Api api;

    public interface ChampionDetailsViewable {
        void showDetails(String version, Champion champion);
        void showError(String message);
        void doFinish();
    }

    @Inject
    ChampionDetailsPresenter(Api api) {
        this.api = api;
    }

    public void setViewable(ChampionDetailsViewable viewable) {
        this.viewable = viewable;
    }

    public void onActivityCreated(Bundle savedInstanceState, Bundle arguments) {
        String championId = arguments.getString(Const.KEY_CHAMPION_ID);
        String version = arguments.getString(Const.KEY_VERSION);
        getChampionDetails(version, championId);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                viewable.doFinish();
                return true;
        }
        return false;
    }

    private void getChampionDetails(final String version, final String championId) {
        api.getChampion(championId).enqueue(new Callback<RiotResponse>() {
            @Override
            public void onResponse(@NonNull Call<RiotResponse> call, @NonNull Response<RiotResponse> response) {
                if (response.isSuccessful()) {
                    RiotResponse riotResponse = response.body();
                    viewable.showDetails(version, riotResponse.getData().get(championId));
                } else {
                    viewable.showError("error " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RiotResponse> call, @NonNull Throwable t) {
                if (t instanceof IOException) {
                    viewable.showError("io failure");
                } else {
                    viewable.showError("failure");
                }
            }
        });
    }
}
