package com.leaguechampions.ui.championdetails

import android.os.Bundle
import android.support.annotation.StringRes
import android.view.MenuItem
import com.leaguechampions.R
import com.leaguechampions.data.local.Const
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.model.RiotResponse
import com.leaguechampions.data.remote.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ChampionDetailsPresenter @Inject constructor(private val view: ChampionDetailsView,
                                                   private val api: Api) {

    private lateinit var championId: String
    private lateinit var version: String

    interface ChampionDetailsView {
        fun showDetails(version: String, champion: Champion)
        fun showError(@StringRes stringId: Int)
        fun showError(@StringRes stringId: Int, errorCode: Int)
        fun doFinish()
    }

    fun onActivityCreated(savedInstanceState: Bundle?, arguments: Bundle?) {
        if (savedInstanceState != null) {
            championId = savedInstanceState.getString(Const.KEY_CHAMPION_ID)
            version = savedInstanceState.getString(Const.KEY_VERSION)
        } else {
            championId = arguments!!.getString(Const.KEY_CHAMPION_ID)
            version = arguments.getString(Const.KEY_VERSION)
        }

        getChampionDetails()
    }

    fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(Const.KEY_CHAMPION_ID, championId)
        outState?.putString(Const.KEY_VERSION, version)
    }

    fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                view.doFinish()
                true
            }
            else -> false
        }
    }

    private fun getChampionDetails() {
        api.getChampion(championId).enqueue(object : Callback<RiotResponse> {
            override fun onResponse(call: Call<RiotResponse>, response: Response<RiotResponse>) {
                if (response.isSuccessful) {
                    val riotResponse = response.body()
                    view.showDetails(version, riotResponse?.data?.get(championId)!!)
                } else {
                    view.showError(R.string.error_code, response.code())
                }
            }

            override fun onFailure(call: Call<RiotResponse>, t: Throwable) {
                view.showError(if (t is IOException) R.string.error_io else R.string.error_something_went_wrong)
            }
        })
    }
}
