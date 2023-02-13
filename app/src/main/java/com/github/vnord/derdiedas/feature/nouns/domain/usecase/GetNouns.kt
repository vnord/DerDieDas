package com.github.vnord.derdiedas.feature.nouns.domain.usecase

import com.github.vnord.derdiedas.feature.nouns.domain.model.Noun
import com.github.vnord.derdiedas.feature.nouns.domain.repository.NounRepository
import kotlinx.coroutines.flow.Flow

class GetNouns(private val repository: NounRepository) {
    operator fun invoke(): Flow<List<Noun>> = repository.getNouns()
}
