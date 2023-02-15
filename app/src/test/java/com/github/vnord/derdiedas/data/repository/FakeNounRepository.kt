package com.github.vnord.derdiedas.data.repository

import com.github.vnord.derdiedas.domain.model.Noun
import com.github.vnord.derdiedas.domain.repository.NounRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNounRepository : NounRepository {
    private val nouns = mutableListOf<Noun>()

    override fun getNouns(limit: Int?): Flow<List<Noun>> =
        flow { if (limit == null) emit(nouns) else emit(nouns.take(limit)) }

    override suspend fun insertNoun(noun: Noun) {
        nouns.add(noun)
    }
}
