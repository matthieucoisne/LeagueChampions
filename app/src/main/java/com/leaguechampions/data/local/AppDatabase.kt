package com.leaguechampions.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leaguechampions.features.champions.data.local.ChampionDao
import com.leaguechampions.features.champions.data.model.ChampionDetailsEntity
import com.leaguechampions.features.champions.data.model.ChampionEntity

@Database(
    entities = [
        ChampionEntity::class,
        ChampionDetailsEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun championDao(): ChampionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "AppDatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
