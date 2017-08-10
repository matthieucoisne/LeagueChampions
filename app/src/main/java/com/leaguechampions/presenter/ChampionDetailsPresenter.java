package com.leaguechampions.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.MenuItem;

import com.leaguechampions.R;
import com.leaguechampions.core.Const;
import com.leaguechampions.datasource.remote.Api;
import com.leaguechampions.model.Champion;
import com.leaguechampions.model.RiotResponse;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChampionDetailsPresenter {

    private final Api api;
    private String championId;
    private String version;
    private ChampionDetailsViewable viewable;

    public interface ChampionDetailsViewable {
        void showDetails(String version, Champion champion);
        void showError(@StringRes int stringId);
        void showError(@StringRes int stringId, int errorCode);
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
        if (savedInstanceState != null) {
            championId = savedInstanceState.getString(Const.KEY_CHAMPION_ID);
            version = savedInstanceState.getString(Const.KEY_VERSION);
        } else {
            championId = arguments.getString(Const.KEY_CHAMPION_ID);
            version = arguments.getString(Const.KEY_VERSION);
        }

        getChampionDetails();
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putString(Const.KEY_CHAMPION_ID, championId);
        outState.putString(Const.KEY_VERSION, version);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                viewable.doFinish();
                return true;
            default:
                return false;
        }
    }

    private void getChampionDetails() {
        api.getChampion(championId).enqueue(new Callback<RiotResponse>() {
            @Override
            public void onResponse(@NonNull Call<RiotResponse> call, @NonNull Response<RiotResponse> response) {
                if (response.isSuccessful()) {
                    RiotResponse riotResponse = response.body();
                    viewable.showDetails(version, riotResponse.getData().get(championId));
                } else {
                    viewable.showError(R.string.error_code, response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RiotResponse> call, @NonNull Throwable t) {
                if (t instanceof IOException) {
                    viewable.showError(R.string.error_io);
                } else {
                    viewable.showError(R.string.error_something_went_wrong);
                }
            }
        });
    }
}
