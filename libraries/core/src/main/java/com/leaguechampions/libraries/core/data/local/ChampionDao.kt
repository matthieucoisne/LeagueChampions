package com.leaguechampions.libraries.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leaguechampions.libraries.core.data.model.Champion

@Dao
interface ChampionDao {

    @Query("SELECT * FROM champions ORDER BY name ASC")
    fun getChampions(): List<Champion>

    @Query("SELECT * FROM champions WHERE id = :id")
    fun getChampion(id: String): Champion

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(champion: Champion)

    @Query("DELETE FROM champions")
    fun deleteChampions()
}
