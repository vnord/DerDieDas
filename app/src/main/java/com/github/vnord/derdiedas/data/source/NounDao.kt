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
    fun getAllNouns(): Flow<List<Noun>>

    @Query("SELECT * FROM noun LIMIT :limit")
    fun getNouns(limit: Int): Flow<List<Noun>>

    // Only used for deciding whether to seed the database or not for now, so does
    // not need to be further abstracted until that changes
    @Query("SELECT COUNT(*) FROM noun")
    fun getNounCount(): Flow<Int>
}
