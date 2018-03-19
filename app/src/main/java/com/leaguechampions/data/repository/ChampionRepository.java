package com.leaguechampions.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.leaguechampions.R;
import com.leaguechampions.data.model.Champion;
import com.leaguechampions.data.model.RiotResponse;
import com.leaguechampions.data.remote.Api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class ChampionRepository {

    private Api api;

    @Inject
    public ChampionRepository(Api api) {
        this.api = api;
    }

    public LiveData<Resource<Map<String, Champion>>> getChampions() {
        final MutableLiveData<Resource<Map<String, Champion>>> data = new MutableLiveData<>();

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
                        data.setValue(Resource.success(response.getData()));
                    }

                    @Override
                    public void onError(Throwable t) {
                        Timber.e(t);
                        if (t instanceof IOException) {
                            data.setValue(Resource.error(R.string.error_io, new HashMap<>(0)));
                        } else {
                            data.setValue(Resource.error(R.string.error_something_went_wrong, new HashMap<>(0)));
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return data;
    }

    public LiveData<Resource<Champion>> getChampionDetails(final String championId) {
        final MutableLiveData<Resource<Champion>> data = new MutableLiveData<>();

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
                        data.setValue(Resource.success(champion));
                    }

                    @Override
                    public void onError(Throwable t) {
                        Timber.e(t);
                        if (t instanceof IOException) {
                            data.setValue(Resource.error(R.string.error_io, null));
                        } else {
                            data.setValue(Resource.error(R.string.error_something_went_wrong, null));
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return data;
    }
}
