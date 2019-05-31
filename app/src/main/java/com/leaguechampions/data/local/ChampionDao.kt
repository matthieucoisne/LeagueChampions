package com.leaguechampions.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.leaguechampions.data.model.Champion

@Dao
interface ChampionDao {

    @Query("SELECT * from champions ORDER BY name ASC")
    fun getChampions(): LiveData<List<Champion>>

    @Insert
    suspend fun insert(champion: Champion)

    @Query("DELETE FROM champions")
    fun deleteChampions()
}