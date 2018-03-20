package com.leaguechampions.ui.champions;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.MenuItem;

import com.leaguechampions.R;
import com.leaguechampions.data.model.Champion;
import com.leaguechampions.data.model.RiotResponse;
import com.leaguechampions.data.remote.Api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class ChampionsPresenter implements ChampionsAdapter.onItemClickListener {

    private final Api api;
    private final ChampionsView view;

    public interface ChampionsView {
        void setAdapter(List<Champion> champions);
        void showError(@StringRes int stringId);
        void showDetails(String championId);
        void showSettings();
    }

    @Inject
    public ChampionsPresenter(ChampionsView view, Api api) {
        this.view = view;
        this.api = api;
    }

    public void onActivityCreated(Bundle savedInstanceState, Bundle arguments) {
        getChampions();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                view.showSettings();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onItemClick(Champion champion) {
        view.showDetails(champion.getId());
    }

    private void getChampions() {
        api.getVersion()
                .flatMap(response ->
                        api.getChampions(response.getVersion())
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RiotResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RiotResponse response) {
                        List<Champion> champions = new ArrayList<>(response.getData().values());
                        Collections.sort(champions);
                        view.setAdapter(champions);
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
