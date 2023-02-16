package com.github.vnord.derdiedas.domain.usecase

import com.github.vnord.derdiedas.domain.model.Noun
import com.github.vnord.derdiedas.domain.repository.NounRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class GetNextNoun(val repository: NounRepository, private val limit: Int = 10) {
    private var nouns: MutableList<Noun>? = null

    init {
        runBlocking {
            nouns = repository.getNouns(limit = limit).first().toMutableList()
        }
    }

    operator fun invoke(): Noun? {
        return nouns?.removeFirstOrNull()
    }
}
