package com.github.vnord.derdiedas.domain.repository

import com.github.vnord.derdiedas.domain.model.Noun
import kotlinx.coroutines.flow.Flow

interface NounRepository {
    fun getNouns(limit: Int? = null): Flow<List<Noun>>

    suspend fun insertNoun(noun: Noun)
}
