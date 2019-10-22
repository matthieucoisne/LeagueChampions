package com.leaguechampions.features.champions.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leaguechampions.features.champions.data.model.ChampionDetailsEntity
import com.leaguechampions.features.champions.data.model.ChampionEntity

@Dao
interface ChampionDao {

    @Query(value = "SELECT * FROM Champions ORDER BY name ASC")
    suspend fun getChampions(): List<ChampionEntity>

    @Query(value = "SELECT * FROM ChampionDetails WHERE id = :championId")
    suspend fun getChampion(championId: String): ChampionDetailsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChampions(champions: List<ChampionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChampionDetails(championDetails: ChampionDetailsEntity)
}
