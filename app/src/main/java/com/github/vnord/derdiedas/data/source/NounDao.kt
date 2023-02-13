package com.github.vnord.derdiedas.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.vnord.derdiedas.domain.model.Noun
import kotlinx.coroutines.flow.Flow

@Dao
interface NounDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNoun(noun: Noun)

    @Query("SELECT * FROM noun")
    fun getNouns(): Flow<List<Noun>>
}
