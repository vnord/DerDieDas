package com.github.vnord.derdiedas.domain.repository

import com.github.vnord.derdiedas.data.entity.Category
import com.github.vnord.derdiedas.data.entity.Noun
import kotlinx.coroutines.flow.Flow

interface NounRepository {
    fun getNouns(category: Category, limit: Int? = null): Flow<List<Noun>>

    suspend fun insertNoun(noun: Noun, category: Category)

    fun getCategories(): Flow<List<Category>>
}
