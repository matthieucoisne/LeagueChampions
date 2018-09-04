package com.leaguechampions.ui.champions

import android.os.Bundle
import android.support.annotation.StringRes
import android.view.MenuItem
import com.leaguechampions.R
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.model.RiotResponse
import com.leaguechampions.data.remote.Api
import javax.inject.Inject

class ChampionsPresenter @Inject constructor(private val view: ChampionsView, private val api: Api)
//    : ChampionsAdapter.OnItemClickListener
{

    interface ChampionsView {
        fun setAdapter(riotResponse: RiotResponse)
        fun showError(@StringRes stringId: Int)
        fun showError(@StringRes stringId: Int, errorCode: Int)
        fun showDetails(version: String, championId: String)
        fun showSettings()
    }

    fun onActivityCreated(savedInstanceState: Bundle?, arguments: Bundle?) {
        getChampions()
    }

    fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                view.showSettings()
                true
            }
            else -> false
        }
    }

//    override
    fun onItemClick(version: String, champion: Champion) {
        view.showDetails(version, champion.id)
    }

    private fun getChampions() {
//        api.getChampions().enqueue(object : Callback<RiotResponse> {
//            override fun onResponse(call: Call<RiotResponse>, response: Response<RiotResponse>) {
//                if (response.isSuccessful) {
//                    view.setAdapter(response.body()!!)
//                } else {
//                    view.showError(R.string.error_code, response.code())
//                }
//            }
//
//            override fun onFailure(call: Call<RiotResponse>, t: Throwable) {
//                view.showError(if (t is IOException) R.string.error_io else R.string.error_something_went_wrong)
//            }
//        })
    }
}
