package com.example.tport.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Path::class, Path2::class], version = 1, exportSchema = false)
abstract class PathDatabase: RoomDatabase() {

    abstract fun pathDao(): PathDao
    abstract fun pathDao2(): PathDao2

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