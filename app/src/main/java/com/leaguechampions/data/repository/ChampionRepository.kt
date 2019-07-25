package com.leaguechampions.data.repository

import androidx.lifecycle.LiveData
import com.leaguechampions.data.local.ChampionDao
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.remote.Api
import com.leaguechampions.utils.Resource
import javax.inject.Inject

class ChampionRepository @Inject constructor(private val api: Api, private val championDao: ChampionDao) {

    suspend fun getChampions(): List<Champion> {
        val realm = api.getRealm()
        val result = api.getChampions(realm.getVersion())
        return result.data.values.toMutableList().apply { sort() }
    }

    suspend fun getChampion(championId: String): Champion {
        val realm = api.getRealm()
        val result = api.getChampionDetails(realm.getVersion(), championId)
        return result.data.getValue(championId).apply { version = result.version }
    }

    suspend fun getChampionWithCacheFromDb(championId: String): LiveData<Resource<Champion>> {
        return object: NetworkBoundResource<Champion, Champion>() {
            override fun processResponse(response: Champion): Champion {
                return response
            }

            override suspend fun loadFromDb(): Champion {
                return championDao.getChampion(championId)
            }

            override fun shouldFetch(data: Champion?): Boolean {
                return data == null || data.name.isEmpty()
            }

            override suspend fun createCall(): Champion {
                return getChampion(championId)
            }

            override suspend fun saveCallResults(data: Champion) {
                championDao.insert(data)
            }
        }.build().asLiveData()
    }
}
