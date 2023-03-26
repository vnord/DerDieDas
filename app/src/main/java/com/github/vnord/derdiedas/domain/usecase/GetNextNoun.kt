package com.github.vnord.derdiedas.domain.usecase

import com.github.vnord.derdiedas.data.entity.Category
import com.github.vnord.derdiedas.data.entity.Noun
import com.github.vnord.derdiedas.domain.model.Categories
import com.github.vnord.derdiedas.domain.repository.NounRepository
import kotlinx.coroutines.flow.firstOrNull

class GetNextNoun(val repository: NounRepository, private val limit: Int = 3) {
    suspend fun getFirstNoun(category: Category = Categories.MyNouns): Noun? {
        repository.getNouns(limit = limit, category = category)
            .firstOrNull()
            ?.let { nouns.addAll(it) }
        return nouns.removeFirstOrNull()
    }

    operator fun invoke(): Noun? {
        return nouns.removeFirstOrNull()
    }

    companion object {
        private val nouns = mutableListOf<Noun>()
    }
}
