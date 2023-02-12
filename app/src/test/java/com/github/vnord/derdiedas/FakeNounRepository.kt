package com.github.vnord.derdiedas

import com.github.vnord.derdiedas.feature_nouns.domain.model.Gender
import com.github.vnord.derdiedas.feature_nouns.domain.model.Noun
import com.github.vnord.derdiedas.feature_nouns.domain.repository.NounRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeNounRepository : NounRepository {
    private val nouns = mutableListOf(Noun("Mann", Gender.DER), Noun("Frau", Gender.DIE))
    private val flow = MutableSharedFlow<List<Noun>>()

    override fun getNouns(): Flow<List<Noun>> = flow

    override suspend fun insertNoun(noun: Noun) {
        nouns.add(noun)
        flow.emit(nouns)
    }
}
