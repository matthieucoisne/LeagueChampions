package com.leaguechampions.core.data.repository

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leaguechampions.core.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.coroutineContext

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MutableLiveData<com.leaguechampions.core.utils.Resource<ResultType>>()
    private val supervisorJob = SupervisorJob()

    suspend fun build(): NetworkBoundResource<ResultType, RequestType> {
        setValue(com.leaguechampions.core.utils.Resource.Loading())

        CoroutineScope(coroutineContext).launch(Dispatchers.IO + supervisorJob) {
            val dbResult = loadFromDb()
            if (shouldFetch(dbResult)) {
                try {
                    fetchFromNetwork(dbResult)
                } catch (e: Exception) {
                    Timber.e("An error happened: $e")
                    setValue(com.leaguechampions.core.utils.Resource.Error(e.toString(), loadFromDb()))
                }
            } else {
                Timber.d("Return data from local database")
                setValue(com.leaguechampions.core.utils.Resource.Success(dbResult))
            }
        }
        return this
    }

    fun asLiveData() = result as LiveData<com.leaguechampions.core.utils.Resource<ResultType>>

    private suspend fun fetchFromNetwork(dbResult: ResultType) {
        Log.d(NetworkBoundResource::class.java.name, "Fetch data from network")
        setValue(com.leaguechampions.core.utils.Resource.Loading(dbResult)) // Dispatch latest value quickly (UX purpose)
        val apiResponse = createCall()
        Log.e(NetworkBoundResource::class.java.name, "Data fetched from network")
        saveCallResults(processResponse(apiResponse))
        setValue(com.leaguechampions.core.utils.Resource.Success(loadFromDb()))
    }

    @MainThread
    private fun setValue(newValue: com.leaguechampions.core.utils.Resource<ResultType>) {
        Timber.d("Resource: $newValue")
        if (result.value != newValue) {
            result.postValue(newValue)
        }
    }

    @WorkerThread
    protected abstract fun processResponse(response: RequestType): ResultType

    @WorkerThread
    protected abstract suspend fun saveCallResults(data: ResultType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract suspend fun loadFromDb(): ResultType

    @MainThread
    protected abstract suspend fun createCall(): RequestType
}
