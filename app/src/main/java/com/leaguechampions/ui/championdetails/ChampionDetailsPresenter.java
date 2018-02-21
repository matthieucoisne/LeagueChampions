package com.leaguechampions.ui.championdetails;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.MenuItem;

import com.leaguechampions.data.model.Champion;
import com.leaguechampions.data.remote.Api;

import javax.inject.Inject;

public class ChampionDetailsPresenter {

//    private final Api api;
    private final ChampionDetailsView view;
//    private String championId;
//    private String version;

    public interface ChampionDetailsView {
        void showDetails(String version, Champion champion);
        void showError(@StringRes int stringId);
        void showError(@StringRes int stringId, int errorCode);
        void doFinish();
    }

    @Inject
    public ChampionDetailsPresenter(ChampionDetailsView view, Api api) {
        this.view = view;
//        this.api = api;
    }

    public void onActivityCreated(Bundle savedInstanceState, Bundle arguments) {
//        if (savedInstanceState != null) {
//            championId = savedInstanceState.getString(Const.KEY_CHAMPION_ID);
//            version = savedInstanceState.getString(Const.KEY_VERSION);
//        } else {
//            championId = arguments.getString(Const.KEY_CHAMPION_ID);
//            version = arguments.getString(Const.KEY_VERSION);
//        }
//
//        getChampionDetails();
    }

    public void onSaveInstanceState(Bundle outState) {
//        outState.putString(Const.KEY_CHAMPION_ID, championId);
//        outState.putString(Const.KEY_VERSION, version);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                view.doFinish();
                return true;
            default:
                return false;
        }
    }

//    private void getChampionDetails() {
//        api.getChampion(championId).enqueue(new Callback<RiotResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<RiotResponse> call, @NonNull Response<RiotResponse> response) {
//                if (response.isSuccessful()) {
//                    RiotResponse riotResponse = response.body();
//                    view.showDetails(version, riotResponse.getData().get(championId));
//                } else {
//                    view.showError(R.string.error_code, response.code());
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<RiotResponse> call, @NonNull Throwable t) {
//                if (t instanceof IOException) {
//                    view.showError(R.string.error_io);
//                } else {
//                    view.showError(R.string.error_something_went_wrong);
//                }
//            }
//        });
//    }
}
