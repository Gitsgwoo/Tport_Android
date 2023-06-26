package com.example.tport.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tport.network.dto.previous.Path0

@Database(entities = [Path0::class], version = 1, exportSchema = false)
abstract class PathDatabase: RoomDatabase() {

    abstract fun pathDao(): PathDao

    companion object {
        @Volatile
        private var INSTANCE: PathDatabase? = null
        fun getDatabase(context: Context): PathDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PathDatabase::class.java,
                    "path_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}