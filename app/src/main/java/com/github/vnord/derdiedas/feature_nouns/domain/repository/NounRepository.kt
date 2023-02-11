package com.github.vnord.derdiedas.feature_nouns.domain.repository

import com.github.vnord.derdiedas.feature_nouns.domain.model.Noun
import kotlinx.coroutines.flow.Flow

interface NounRepository {
    fun getNouns(): Flow<List<Noun>>

    suspend fun insertNoun(noun: Noun)
}
