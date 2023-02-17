package com.github.vnord.derdiedas.domain.usecase

import com.github.vnord.derdiedas.domain.model.Noun
import com.github.vnord.derdiedas.domain.repository.NounRepository
import kotlinx.coroutines.flow.firstOrNull

class GetNextNoun(val repository: NounRepository, private val limit: Int = 3) {
    suspend fun getFirstNoun(): Noun? {
        repository.getNouns(limit = limit).firstOrNull()?.let { nouns.addAll(it) }
        return nouns.removeFirstOrNull()
    }

    operator fun invoke(): Noun? {
        return nouns.removeFirstOrNull()
    }

    companion object {
        private val nouns = mutableListOf<Noun>()
    }
}
