package com.leaguechampions.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.leaguechampions.R
import com.leaguechampions.data.model.RiotResponse
import com.leaguechampions.data.remote.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ChampionRepository @Inject constructor(private val api: Api) {

    fun getChampions(): LiveData<Resource<RiotResponse>> {
        val data = MutableLiveData<Resource<RiotResponse>>()

        api.getChampions().enqueue(object : Callback<RiotResponse> {
            override fun onResponse(call: Call<RiotResponse>, response: Response<RiotResponse>) {
                if (response.isSuccessful) {
                    val riotResponse = response.body()
                    data.setValue(Resource.success(riotResponse!!))
                } else {
                    // TODO find a way to add `response.code()`
                    data.setValue(Resource.error(R.string.error_code, null as RiotResponse))
                }
            }

            override fun onFailure(call: Call<RiotResponse>, t: Throwable) {
                if (t is IOException) {
                    data.setValue(Resource.error(R.string.error_io, null as RiotResponse))
                } else {
                    data.setValue(Resource.error(R.string.error_something_went_wrong, null as RiotResponse))
                }
            }
        })

        return data
    }

    fun getChampionDetails(championId: String): LiveData<Resource<RiotResponse>> {
        val data = MutableLiveData<Resource<RiotResponse>>()

        api.getChampion(championId).enqueue(object : Callback<RiotResponse> {
            override fun onResponse(call: Call<RiotResponse>, response: Response<RiotResponse>) {
                if (response.isSuccessful) {
                    val riotResponse = response.body()
                    data.setValue(Resource.success(riotResponse!!))
                } else {
                    // TODO find a way to add `response.code()`
                    data.setValue(Resource.error(R.string.error_code, null as RiotResponse))
                }
            }

            override fun onFailure(call: Call<RiotResponse>, t: Throwable) {
                if (t is IOException) {
                    data.setValue(Resource.error(R.string.error_io, null as RiotResponse))
                } else {
                    data.setValue(Resource.error(R.string.error_something_went_wrong, null as RiotResponse))
                }
            }
        })

        return data
    }
}
