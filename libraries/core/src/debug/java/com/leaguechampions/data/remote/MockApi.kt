package com.leaguechampions.data.remote

import android.content.Context
import com.google.gson.Gson
import com.leaguechampions.libraries.core.data.model.Champion
import com.leaguechampions.libraries.core.data.model.RiotRealm
import com.leaguechampions.libraries.core.data.model.RiotResponse
import com.leaguechampions.libraries.core.data.remote.Api
import okio.buffer
import okio.source
import retrofit2.mock.BehaviorDelegate
import java.lang.reflect.Type
import java.nio.charset.Charset

class MockApi(
    private val context: Context,
    private val delegate: BehaviorDelegate<Api>
) : Api {

    private val gson: Gson = Gson()

    private fun getStringFromFile(filePath: String): String {
        val stream = context.assets.open(filePath)
        val source = stream.source().buffer()
        return source.readString(Charset.defaultCharset())
    }

    private fun <T> getDataFromFile(filePath: String, type: Type): T? {
        return gson.fromJson<T>(getStringFromFile(filePath), type)
    }

//    override fun getVersion(): Observable<RiotRealm> {
//        val filePath = "json/getVersion.json"
//
//        return try {
//            val riotRealm = getDataFromFile<RiotRealm>(filePath, RiotRealm::class.java)
//            delegate.returningResponse(riotRealm!!).getVersion()
//        } catch (e: IOException) {
//            Observable.error(e)
//        }
//    }
//
//    override fun getChampions(version: String): Observable<RiotResponse> {
//        val filePath = "json/getChampions.json"
//
//        return try {
//            val riotResponse = getDataFromFile<RiotResponse>(filePath, RiotResponse::class.java)
//            delegate.returningResponse(riotResponse!!).getChampions(version)
//        } catch (e: IOException) {
//            Observable.error(e)
//        }
//    }
//
//    override fun getChampionDetails(version: String, championId: String): Observable<RiotResponse> {
//        val filePath = "json/getChampion.json"
//
//        return try {
//            // The file "getChampion.json" is a map (championId -> data) containing data for "Riven" only.
//            // We need to replace the key "Riven" with the provided championId.
//            val json = getStringFromFile(filePath)
//            val jsonObjectResponse = gson.fromJson(json, JsonElement::class.java).asJsonObject
//            if ("Riven" != championId) {
//                val jsonObjectData = jsonObjectResponse.getAsJsonObject("data")
//                jsonObjectData.add(championId, jsonObjectData.get("Riven"))
//                jsonObjectData.remove("Riven")
//            }
//
//            val riotResponse = gson.fromJson(jsonObjectResponse, RiotResponse::class.java)
//            delegate.returningResponse(riotResponse).getChampionDetails(version, championId)
//        } catch (e: IOException) {
//            Observable.error(e)
//        }
//    }

    override suspend fun getRealm(): RiotRealm {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getChampions(version: String): RiotResponse<Champion> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getChampionDetails(version: String, championId: String): RiotResponse<Champion> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
