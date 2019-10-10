package com.leaguechampions

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leaguechampions.features.champions.data.local.ChampionDao
import com.leaguechampions.features.champions.data.model.ChampionEntity

@Database(entities = [ChampionEntity::class], version = 1)
abstract class ChampionRoomDatabase : RoomDatabase() {

    abstract fun championDao(): ChampionDao

    companion object {
        @Volatile
        private var INSTANCE: ChampionRoomDatabase? = null

        fun getDatabase(context: Context): ChampionRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ChampionRoomDatabase::class.java,
                        "Champion_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
