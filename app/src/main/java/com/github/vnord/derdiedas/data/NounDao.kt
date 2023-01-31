package com.github.vnord.derdiedas.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NounDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noun: Noun)

    @Delete
    suspend fun delete(noun: Noun)

    @Update
    suspend fun update(noun: Noun)

    @Query("SELECT * FROM noun ORDER BY noun ASC")
    fun getNouns(): Flow<List<Noun>>

    @Query("SELECT COUNT(*) FROM noun")
    suspend fun getNumberOfNouns(): Int

    @Query("SELECT * FROM noun ORDER BY noun ASC LIMIT 1 OFFSET :n")
    suspend fun getNthNoun(n: Int): Noun

    @Query(
        """SELECT * FROM noun
                 WHERE reviews_done < $NUMBER_OF_REVIEWS_REQUIRED
                 AND (next_review IS NULL OR next_review < :now)"""
    )
    suspend fun getEligibleNouns(
        now: Long = System.currentTimeMillis()
    ): List<Noun>

    @Query(
        """SELECT COUNT(*) FROM noun
                 WHERE reviews_done < $NUMBER_OF_REVIEWS_REQUIRED
                 AND (next_review IS NULL OR next_review < :now)"""
    )
    fun getNumberOfEligibleNouns(
        now: Long = System.currentTimeMillis()
    ): Flow<Int>
}