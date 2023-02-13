package com.github.vnord.derdiedas.feature.nouns.domain.usecase

import com.github.vnord.derdiedas.feature.nouns.domain.model.Noun
import com.github.vnord.derdiedas.feature.nouns.domain.repository.NounRepository

class AddNoun(private val repository: NounRepository) {
    suspend operator fun invoke(noun: Noun) = repository.insertNoun(noun)
}
