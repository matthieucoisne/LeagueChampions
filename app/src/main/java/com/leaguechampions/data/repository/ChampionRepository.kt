package com.leaguechampions.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.leaguechampions.R
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.remote.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import javax.inject.Inject

class ChampionRepository @Inject constructor(private val api: Api) {

    fun getChampions(): LiveData<Resource<Map<String, Champion>>> {
        val data = MutableLiveData<Resource<Map<String, Champion>>>()

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
                        { response ->
                            data.setValue(Resource.success(response.data))
                        },
                        // onError
                        { t ->
                            if (t is IOException) {
                                data.value = Resource.error(R.string.error_io, null as Map<String, Champion>)
                            } else {
                                data.value = Resource.error(R.string.error_something_went_wrong, null as Map<String, Champion>)
                            }
                        },
                        // onComplete
                        {}
                )

        return data
    }

    fun getChampionDetails(championId: String): LiveData<Resource<Champion>> {
        val data = MutableLiveData<Resource<Champion>>()

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
                            if (t is IOException) {
                                data.value = Resource.error(R.string.error_io, null as Champion)
                            } else {
                                data.value = Resource.error(R.string.error_something_went_wrong, null as Champion)
                            }
                        },
                        // onComplete
                        {}
                )

        return data
    }
}
