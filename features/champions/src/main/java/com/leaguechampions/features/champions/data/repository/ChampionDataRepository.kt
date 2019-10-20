package com.leaguechampions.features.champions.data.repository

import com.leaguechampions.features.champions.data.local.ChampionDao
import com.leaguechampions.features.champions.data.model.toChampion
import com.leaguechampions.features.champions.data.remote.ChampionApi
import com.leaguechampions.features.champions.domain.model.Champion
import com.leaguechampions.features.champions.domain.repository.ChampionRepository
import javax.inject.Inject

class ChampionDataRepository @Inject constructor(
    private val championApi: ChampionApi,
    private val championDao: ChampionDao
) : ChampionRepository {

    override suspend fun getChampions(version: String): List<Champion> {
        val result = championApi.getChampions(version)
        val champions = result.data.values.toMutableList()
        championDao.saveChampions(champions)
        return champions.map { it.toChampion() }
    }

    override suspend fun getChampionDetails(championId: String, version: String): Champion {
        val result = championApi.getChampionDetails(championId, version)
        val championDetails = result.data.getValue(championId).apply { this.version = version }
        championDao.saveChampionDetails(championDetails)
        return championDetails.toChampion()
    }

//    override suspend fun getChampionWithCacheFromDb(championId: String): LiveData<Resource<Champion>> {
//        return object: NetworkBoundResource<ChampionEntity, ChampionEntity>() {
//            override fun processResponse(response: ChampionEntity): ChampionEntity {
//                return response
//            }
//
//            override suspend fun loadFromDb(): ChampionEntity {
//                return championDao.getChampion(championId)
//            }
//
//            override fun shouldFetch(data: ChampionEntity?): Boolean {
//                return data == null || data.name.isEmpty()
//            }
//
//            override suspend fun createCall(): ChampionEntity {
//                return getChampionDetails(championId, "")
//            }
//
//            override suspend fun saveCallResults(data: ChampionEntity) {
//                championDao.insert(data)
//            }
//        }.build().asLiveData()
//    }
}
