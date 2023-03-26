package com.github.vnord.derdiedas.data.repository

import com.github.vnord.derdiedas.data.entity.Category
import com.github.vnord.derdiedas.data.entity.Noun
import com.github.vnord.derdiedas.domain.repository.NounRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNounRepository : NounRepository {
    private val nouns = mutableListOf<Noun>()
    val category = Category("My Nouns", 0)

    override fun getNouns(category: Category, limit: Int): Flow<List<Noun>> =
        flow { emit(nouns.take(limit)) }

    override suspend fun insertNoun(noun: Noun, category: Category) {
        nouns.add(noun)
    }

    override fun getCategories(): Flow<List<Category>> =
        flow { emit(listOf(category)) }
}
