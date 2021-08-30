package com.github.vnord.derdiedas.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NounPhraseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(nounPhrase: NounPhrase)

    @Delete
    suspend fun delete(nounPhrase: NounPhrase)

    @Query("SELECT * FROM noun_phrase ORDER BY noun ASC")
    fun getNounPhrases(): Flow<List<NounPhrase>>

    @Query("SELECT COUNT(*) FROM noun_phrase")
    suspend fun getNumberOfNounPhrases(): Int

    @Query("SELECT * FROM noun_phrase ORDER BY noun ASC LIMIT 1 OFFSET :n")
    suspend fun getNthNounPhrase(n: Int): NounPhrase
}