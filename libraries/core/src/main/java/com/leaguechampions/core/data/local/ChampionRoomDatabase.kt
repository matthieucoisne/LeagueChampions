package com.leaguechampions.core.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leaguechampions.core.data.model.Champion

@Database(entities = [Champion::class], version = 1)
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
