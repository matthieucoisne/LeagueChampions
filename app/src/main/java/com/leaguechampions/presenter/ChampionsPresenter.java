package com.leaguechampions.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.leaguechampions.R;
import com.leaguechampions.model.Champions;
import com.leaguechampions.datasource.remote.Api;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChampionsPresenter {

    private ChampionsViewable viewable;

    private final Api api;

    public interface ChampionsViewable {
        void showSettings();
        void setAdapter(Champions champions);
        void showError(String message);
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
        }
        return false;
    }

    private void getChampions() {
        api.getChampions().enqueue(new Callback<Champions>() {
            @Override
            public void onResponse(@NonNull Call<Champions> call, @NonNull Response<Champions> response) {
                if (response.isSuccessful()) {
                    Champions champions = response.body();
                    viewable.setAdapter(champions);
                } else {
                    viewable.showError("error " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Champions> call, @NonNull Throwable t) {
                if (t instanceof IOException) {
                    viewable.showError("io failure");
                } else {
                    viewable.showError("failure");
                }
            }
        });
    }
}
