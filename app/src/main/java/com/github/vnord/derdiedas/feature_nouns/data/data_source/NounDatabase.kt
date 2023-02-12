package com.github.vnord.derdiedas.feature_nouns.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.vnord.derdiedas.feature_nouns.domain.model.Noun

@Database(entities = [Noun::class], version = 1, exportSchema = false)
abstract class NounDatabase : RoomDatabase() {
    abstract val nounDao: NounDao

    companion object {
        const val DATABASE_NAME = "noun_db"
    }
}
