package com.github.vnord.derdiedas.domain.usecase

import com.github.vnord.derdiedas.domain.model.Noun
import com.github.vnord.derdiedas.domain.repository.NounRepository

class AddNoun(private val repository: NounRepository) {
    suspend operator fun invoke(noun: Noun) = repository.insertNoun(noun)
}
