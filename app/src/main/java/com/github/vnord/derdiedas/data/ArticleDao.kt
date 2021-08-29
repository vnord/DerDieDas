package com.github.vnord.derdiedas.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM article ORDER BY article ASC")
    fun getArticles(): Flow<List<Article>>

    @Query("SELECT COUNT(*) FROM article")
    fun getNumberOfArticles(): Int

    @Query("SELECT * FROM article ORDER BY article ASC LIMIT 1 OFFSET :n")
    fun getNthArticle(n: Int): Article
}