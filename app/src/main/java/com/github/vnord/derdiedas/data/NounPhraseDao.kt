package com.github.vnord.derdiedas.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NounPhraseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(nounPhrase: NounPhrase)

    @Delete
    suspend fun delete(nounPhrase: NounPhrase)

    @Update
    suspend fun update(nounPhrase: NounPhrase)

    @Query("SELECT * FROM noun_phrase ORDER BY noun ASC")
    fun getNounPhrases(): Flow<List<NounPhrase>>

    @Query("SELECT COUNT(*) FROM noun_phrase")
    suspend fun getNumberOfNounPhrases(): Int

    @Query("SELECT * FROM noun_phrase ORDER BY noun ASC LIMIT 1 OFFSET :n")
    suspend fun getNthNounPhrase(n: Int): NounPhrase

    @Query(
        """SELECT * FROM noun_phrase
                 WHERE reviews_done < $NUMBER_OF_REVIEWS_REQUIRED
                 AND (next_review IS NULL OR next_review < :now)"""
    )
    suspend fun getEligibleNounPhrases(
        now: Long = System.currentTimeMillis()
    ): List<NounPhrase>
}