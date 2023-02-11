package com.github.vnord.derdiedas.feature_nouns.domain.use_case

import com.github.vnord.derdiedas.feature_nouns.domain.model.Noun
import com.github.vnord.derdiedas.feature_nouns.domain.repository.NounRepository
import kotlinx.coroutines.flow.Flow

class GetNouns(private val repository: NounRepository) {
    operator fun invoke(): Flow<List<Noun>> = repository.getNouns()
}
