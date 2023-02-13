package com.github.vnord.derdiedas.feature.nouns.data.repository

import com.github.vnord.derdiedas.feature.nouns.data.source.NounDao
import com.github.vnord.derdiedas.feature.nouns.domain.model.Noun
import com.github.vnord.derdiedas.feature.nouns.domain.repository.NounRepository
import kotlinx.coroutines.flow.Flow

class NounRepositoryImpl(private val dao: NounDao) : NounRepository {
    override fun getNouns(): Flow<List<Noun>> = dao.getNouns()

    override suspend fun insertNoun(noun: Noun) = dao.insertNoun(noun)
}
