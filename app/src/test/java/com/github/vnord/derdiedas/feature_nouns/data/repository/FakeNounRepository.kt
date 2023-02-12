package com.github.vnord.derdiedas.feature_nouns.data.repository

import com.github.vnord.derdiedas.feature_nouns.domain.model.Noun
import com.github.vnord.derdiedas.feature_nouns.domain.repository.NounRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNounRepository : NounRepository {
    private val nouns = mutableListOf<Noun>()

    override fun getNouns(): Flow<List<Noun>> = flow { emit(nouns) }

    override suspend fun insertNoun(noun: Noun) {
        nouns.add(noun)
    }
}
