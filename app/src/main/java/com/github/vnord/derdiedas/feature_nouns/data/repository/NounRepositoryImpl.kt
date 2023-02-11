package com.github.vnord.derdiedas.feature_nouns.data.repository

import com.github.vnord.derdiedas.feature_nouns.data.data_source.NounDao
import com.github.vnord.derdiedas.feature_nouns.domain.model.Noun
import com.github.vnord.derdiedas.feature_nouns.domain.repository.NounRepository
import kotlinx.coroutines.flow.Flow

class NounRepositoryImpl(private val dao: NounDao) : NounRepository {
    override fun getNouns(): Flow<List<Noun>> = dao.getNouns()

    override suspend fun insertNoun(noun: Noun) = dao.insertNoun(noun)
}
