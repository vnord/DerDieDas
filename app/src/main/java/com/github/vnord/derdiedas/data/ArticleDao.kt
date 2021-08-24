package com.github.vnord.derdiedas.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM article WHERE article = :article")
    fun getArticle(article: String): Flow<Article>

    @Query("SELECT * FROM article ORDER BY article ASC")
    fun getArticles(): Flow<List<Article>>
}