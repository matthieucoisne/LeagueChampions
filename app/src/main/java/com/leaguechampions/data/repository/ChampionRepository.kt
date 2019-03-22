package com.leaguechampions.data.repository

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.remote.Api
import com.leaguechampions.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChampionRepository @Inject constructor(private val api: Api, private val context: Context) {

    suspend fun getChampions(): LiveData<Resource<List<Champion>>> {
        val data = MutableLiveData<Resource<List<Champion>>>()

        data.value = Resource.loading()


            val realm = api.getRealm().await()
            delay(2_000)
            val result = api.getChampions(realm.getVersion()).await()
            data.postValue(Resource.success(result.data.values.toList()))


//        api.getVersion()
//                .flatMap { response ->
////                    if (response.raw().cacheResponse() != null) {
////                        Log.d("OkHttp", "Response from cache.")
////                    }
////                    api.getChampions(response.body()?.getVersion()!!)
//
//
//                    // TOD0 remove this, only for testing
//                    Thread.sleep(2500)
//
//
//                    api.getChampions(response.getVersion())
//                }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        // onNext
//                        {
//                            data.setValue(Resource.success(it.data.values.toList()))
//                        },
//                        // onError
//                        { t ->
//                            Timber.e(t)
//                            if (t is IOException) {
//                                data.value = Resource.error(context.getString(R.string.error_io))
//                            } else {
//                                data.value = Resource.error(context.getString(R.string.error_something_went_wrong))
//                            }
//                        },
//                        // onComplete
//                        {}
//                )

        return data
    }

    @SuppressLint("CheckResult")
    fun getChampionDetails(championId: String): LiveData<Resource<Champion>> {
        val data = MutableLiveData<Resource<Champion>>()

        data.value = Resource.loading()

        CoroutineScope(Dispatchers.IO).launch {
            val realm = withContext(Dispatchers.IO) { api.getRealm() }.await()
            delay(2_000)
            val result = withContext(Dispatchers.IO) { api.getChampionDetails(realm.getVersion(), championId) }.await()
            val champion = result.data.getValue(championId)
            champion.version = result.version
            data.postValue(Resource.success(champion))
        }

//        data.value = Resource.loading()
//        api.getVersion()
//                .flatMap { response ->
////                    if (response.raw().cacheResponse() != null) {
////                        Log.d("OkHttp", "Response from cache.")
////                    }
////                    api.getChampionDetails(response.body()?.getVersion()!!, championId)
//
//
//                    // TOD) remove this, only for testing
//                    Thread.sleep(2500)
//
//
//                    api.getChampionDetails(response.getVersion(), championId)
//                }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        // onNext
//                        { response ->
//                            val champion = response.data[championId]!!
//                            champion.version = response.version
//                            data.setValue(Resource.success(champion))
//                        },
//                        // onError
//                        { t ->
//                            Timber.e(t)
//                            if (t is IOException) {
//                                data.value = Resource.error(context.getString(R.string.error_io))
//                            } else {
//                                data.value = Resource.error(context.getString(R.string.error_something_went_wrong))
//                            }
//                        },
//                        // onComplete
//                        {}
//                )

        return data
    }
}
