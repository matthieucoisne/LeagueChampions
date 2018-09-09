package com.leaguechampions.data.repository

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.leaguechampions.R
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.remote.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class ChampionRepository @Inject constructor(private val api: Api, private val context: Context) {

    @SuppressLint("CheckResult")
    fun getChampions(): LiveData<Resource<List<Champion>>> {
        val data = MutableLiveData<Resource<List<Champion>>>()

        data.value = Resource.loading()
        api.getVersion()
                .flatMap { response ->
//                    if (response.raw().cacheResponse() != null) {
//                        Log.d("OkHttp", "Response from cache.")
//                    }
//                    api.getChampions(response.body()?.getVersion()!!)
                    api.getChampions(response.getVersion())
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        // onNext
                        {
                            data.setValue(Resource.success(it.data.values.toList()))
                        },
                        // onError
                        { t ->
                            Timber.e(t)
                            if (t is IOException) {
                                data.value = Resource.error(context.getString(R.string.error_io))
                            } else {
                                data.value = Resource.error(context.getString(R.string.error_something_went_wrong))
                            }
                        },
                        // onComplete
                        {}
                )

        return data
    }

    @SuppressLint("CheckResult")
    fun getChampionDetails(championId: String): LiveData<Resource<Champion>> {
        val data = MutableLiveData<Resource<Champion>>()

        data.value = Resource.loading()
        api.getVersion()
                .flatMap { response ->
//                    if (response.raw().cacheResponse() != null) {
//                        Log.d("OkHttp", "Response from cache.")
//                    }
//                    api.getChampionDetails(response.body()?.getVersion()!!, championId)
                    api.getChampionDetails(response.getVersion(), championId)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        // onNext
                        { response ->
                            val champion = response.data[championId]!!
                            champion.version = response.version
                            data.setValue(Resource.success(champion))
                        },
                        // onError
                        { t ->
                            Timber.e(t)
                            if (t is IOException) {
                                data.value = Resource.error(context.getString(R.string.error_io))
                            } else {
                                data.value = Resource.error(context.getString(R.string.error_something_went_wrong))
                            }
                        },
                        // onComplete
                        {}
                )

        return data
    }
}
