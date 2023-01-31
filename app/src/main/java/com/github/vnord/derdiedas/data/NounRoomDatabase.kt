package com.github.vnord.derdiedas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Noun::class], version = 3, exportSchema = false)
abstract class NounRoomDatabase : RoomDatabase() {
    abstract fun nounDao(): NounDao

    companion object {
        @Volatile
        private var INSTANCE: NounRoomDatabase? = null

        fun getDatabase(context: Context): NounRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NounRoomDatabase::class.java,
                    "noun_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
