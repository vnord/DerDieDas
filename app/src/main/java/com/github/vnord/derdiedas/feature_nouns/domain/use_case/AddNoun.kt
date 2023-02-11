package com.github.vnord.derdiedas.feature_nouns.domain.use_case

import com.github.vnord.derdiedas.feature_nouns.domain.model.Noun
import com.github.vnord.derdiedas.feature_nouns.domain.repository.NounRepository

class AddNoun(private val repository: NounRepository) {
    suspend operator fun invoke(noun: Noun) = repository.insertNoun(noun)
}
