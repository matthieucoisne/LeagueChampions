package com.leaguechampions.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.MenuItem;

import com.leaguechampions.R;
import com.leaguechampions.model.RiotResponse;
import com.leaguechampions.datasource.remote.Api;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChampionsPresenter {

    private final Api api;
    private ChampionsViewable viewable;

    public interface ChampionsViewable {
        void showSettings();
        void setAdapter(RiotResponse riotResponse);
        void showError(@StringRes int stringId);
        void showError(@StringRes int stringId, int errorCode);
    }

    @Inject
    ChampionsPresenter(Api api) {
        this.api = api;
    }

    public void setViewable(ChampionsViewable viewable) {
        this.viewable = viewable;
    }

    public void onActivityCreated(Bundle savedInstanceState, Bundle arguments) {
        getChampions();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                viewable.showSettings();
                return true;
            default:
                return false;
        }
    }

    private void getChampions() {
        api.getChampions().enqueue(new Callback<RiotResponse>() {
            @Override
            public void onResponse(@NonNull Call<RiotResponse> call, @NonNull Response<RiotResponse> response) {
                if (response.isSuccessful()) {
                    RiotResponse riotResponse = response.body();
                    viewable.setAdapter(riotResponse);
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
