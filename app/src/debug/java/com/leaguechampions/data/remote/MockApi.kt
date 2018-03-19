package com.leaguechampions.data.remote

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.leaguechampions.data.model.RiotRealm
import com.leaguechampions.data.model.RiotResponse
import io.reactivex.Observable
import okio.Okio
import retrofit2.mock.BehaviorDelegate
import java.io.IOException
import java.lang.reflect.Type
import java.nio.charset.Charset

class MockApi(private val context: Context,
              private val delegate: BehaviorDelegate<Api>) : Api {

    private val gson: Gson = Gson()

    private fun getStringFromFile(filePath: String): String {
        val stream = context.assets.open(filePath)
        val source = Okio.buffer(Okio.source(stream))
        return source.readString(Charset.defaultCharset())
    }

    private fun <T> getDataFromFile(filePath: String, type: Type): T? {
        return gson.fromJson<T>(getStringFromFile(filePath), type)
    }

    override fun getVersion(): Observable<RiotRealm> {
        val filePath = "json/getVersion.json"

        return try {
            val riotRealm = getDataFromFile<RiotRealm>(filePath, RiotRealm::class.java)
            delegate.returningResponse(riotRealm!!).getVersion()
        } catch (e: IOException) {
            Observable.error(e)
        }
    }

    override fun getChampions(version: String): Observable<RiotResponse> {
        val filePath = "json/getChampions.json"

        return try {
            val riotResponse = getDataFromFile<RiotResponse>(filePath, RiotResponse::class.java)
            delegate.returningResponse(riotResponse!!).getChampions(version)
        } catch (e: IOException) {
            Observable.error(e)
        }
    }

    override fun getChampionDetails(version: String, championId: String): Observable<RiotResponse> {
        val filePath = "json/getChampion.json"

        return try {
            // The file "getChampion.json" is a map (championId -> data) containing data for "Riven" only.
            // We need to replace the key "Riven" with the provided championId.
            val json = getStringFromFile(filePath)
            val jsonObjectResponse = gson.fromJson(json, JsonElement::class.java).asJsonObject
            if ("Riven" != championId) {
                val jsonObjectData = jsonObjectResponse.getAsJsonObject("data")
                jsonObjectData.add(championId, jsonObjectData.get("Riven"))
                jsonObjectData.remove("Riven")
            }

            val riotResponse = gson.fromJson(jsonObjectResponse, RiotResponse::class.java)
            delegate.returningResponse(riotResponse).getChampionDetails(version, championId)
        } catch (e: IOException) {
            Observable.error(e)
        }
    }
}
