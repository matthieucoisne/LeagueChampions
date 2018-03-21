package com.leaguechampions.ui.championdetails;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.MenuItem;

import com.leaguechampions.R;
import com.leaguechampions.data.local.Const;
import com.leaguechampions.data.model.Champion;
import com.leaguechampions.data.model.RiotResponse;
import com.leaguechampions.data.remote.Api;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class ChampionDetailsPresenter {

    private final Api api;
    private final ChampionDetailsView view;
    private String championId;

    public interface ChampionDetailsView {
        void showDetails(Champion champion);
        void showError(@StringRes int stringId);
        void doFinish();
    }

    @Inject
    public ChampionDetailsPresenter(ChampionDetailsView view, Api api) {
        this.view = view;
        this.api = api;
    }

    public void onActivityCreated(Bundle savedInstanceState, Bundle arguments) {
        if (savedInstanceState != null) {
            championId = savedInstanceState.getString(Const.KEY_CHAMPION_ID);
        } else {
            championId = arguments.getString(Const.KEY_CHAMPION_ID);
        }

        getChampionDetails(championId);
    }

    public void onSaveInstanceState(Bundle outState) {
        // This could be removed, championId is already provided in the starting intent.
        outState.putString(Const.KEY_CHAMPION_ID, championId);
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

    private void getChampionDetails(final String championId) {
        api.getVersion()
                .flatMap(response ->
                        api.getChampion(response.getVersion(), championId)
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RiotResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RiotResponse response) {
                        Champion champion = response.getData().get(championId);
                        champion.setVersion(response.getVersion());
                        view.showDetails(champion);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Timber.e(t);
                        if (t instanceof IOException) {
                            view.showError(R.string.error_io);
                        } else {
                            view.showError(R.string.error_something_went_wrong);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
