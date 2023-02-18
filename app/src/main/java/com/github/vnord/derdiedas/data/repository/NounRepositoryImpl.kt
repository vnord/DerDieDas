package com.github.vnord.derdiedas.data.repository

import com.github.vnord.derdiedas.data.source.NounDao
import com.github.vnord.derdiedas.domain.model.Noun
import com.github.vnord.derdiedas.domain.repository.NounRepository
import kotlinx.coroutines.flow.Flow

class NounRepositoryImpl(private val dao: NounDao) : NounRepository {
    override fun getNouns(limit: Int?): Flow<List<Noun>> =
        limit?.let { dao.getNouns(limit) } ?: dao.getAllNouns()

    override suspend fun insertNoun(noun: Noun) = dao.insertNoun(noun)
}
