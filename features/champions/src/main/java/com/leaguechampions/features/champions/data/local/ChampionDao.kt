package com.leaguechampions.features.champions.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leaguechampions.features.champions.data.model.ChampionEntity

@Dao
interface ChampionDao {

    @Query("SELECT * FROM champions ORDER BY name ASC")
    fun getChampions(): List<ChampionEntity>

    @Query("SELECT * FROM champions WHERE id = :id")
    fun getChampion(id: String): ChampionEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(champion: ChampionEntity)

    @Query("DELETE FROM champions")
    fun deleteChampions()
}
