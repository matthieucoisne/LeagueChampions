package com.leaguechampions.features.champions.data.repository

import androidx.lifecycle.LiveData
import com.leaguechampions.features.champions.data.local.ChampionDao
import com.leaguechampions.features.champions.data.model.ChampionDetailsEntity
import com.leaguechampions.features.champions.data.model.ChampionEntity
import com.leaguechampions.features.champions.data.model.toChampion
import com.leaguechampions.features.champions.data.remote.ChampionApi
import com.leaguechampions.features.champions.domain.model.Champion
import com.leaguechampions.features.champions.domain.repository.ChampionRepository
import com.leaguechampions.libraries.core.data.repository.NetworkBoundResource
import com.leaguechampions.libraries.core.utils.Resource
import javax.inject.Inject

class ChampionDataRepository @Inject constructor(
    private val championApi: ChampionApi,
    private val championDao: ChampionDao
) : ChampionRepository {

    override suspend fun getChampions(version: String): LiveData<Resource<List<Champion>>> {
        return object: NetworkBoundResource<List<ChampionEntity>, List<Champion>>() {
            override fun shouldFetch(data: List<Champion>?): Boolean {
                return true
            }

            override suspend fun createCall(): List<ChampionEntity> {
                val result = championApi.getChampions(version)
                return result.data.values.toList()
            }

            override fun processResponse(response: List<ChampionEntity>): List<ChampionEntity> {
                return response
            }

            override suspend fun saveCallResults(data: List<ChampionEntity>) {
                championDao.saveChampions(data)
            }

            override suspend fun loadFromDb(): List<Champion> {
                return championDao.getChampions().map { it.toChampion() }
            }
        }.build().asLiveData()
    }

    override suspend fun getChampionDetails(championId: String, version: String): LiveData<Resource<Champion>> {
        return object: NetworkBoundResource<ChampionDetailsEntity, Champion>() {
            override fun shouldFetch(data: Champion?): Boolean {
                return true
            }

            override suspend fun createCall(): ChampionDetailsEntity {
                val result = championApi.getChampionDetails(championId, version)
                return result.data.getValue(championId)
            }

            override fun processResponse(response: ChampionDetailsEntity): ChampionDetailsEntity {
                return response.apply { this.version = version }
            }

            override suspend fun saveCallResults(data: ChampionDetailsEntity) {
                championDao.saveChampionDetails(data)
            }

            override suspend fun loadFromDb(): Champion? {
                return championDao.getChampion(championId)?.toChampion()
            }
        }.build().asLiveData()
    }
}
