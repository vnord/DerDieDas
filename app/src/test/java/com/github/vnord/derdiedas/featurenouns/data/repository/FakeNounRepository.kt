package com.github.vnord.derdiedas.featurenouns.data.repository

import com.github.vnord.derdiedas.feature.nouns.domain.model.Noun
import com.github.vnord.derdiedas.feature.nouns.domain.repository.NounRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNounRepository : NounRepository {
    private val nouns = mutableListOf<Noun>()

    override fun getNouns(): Flow<List<Noun>> = flow { emit(nouns) }

    override suspend fun insertNoun(noun: Noun) {
        nouns.add(noun)
    }
}
