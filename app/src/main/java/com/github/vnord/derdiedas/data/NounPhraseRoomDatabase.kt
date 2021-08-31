package com.github.vnord.derdiedas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NounPhrase::class], version = 3, exportSchema = false)
abstract class NounPhraseRoomDatabase : RoomDatabase() {
    abstract fun nounPhraseDao(): NounPhraseDao

    companion object {
        @Volatile
        private var INSTANCE: NounPhraseRoomDatabase? = null

        fun getDatabase(context: Context): NounPhraseRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NounPhraseRoomDatabase::class.java,
                    "noun_phrase_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
