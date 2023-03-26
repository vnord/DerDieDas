package com.github.vnord.derdiedas.domain.usecase

import com.github.vnord.derdiedas.data.entity.Category
import com.github.vnord.derdiedas.domain.repository.NounRepository
import kotlinx.coroutines.flow.Flow

class GetCategories(private val repository: NounRepository) {
    operator fun invoke(): Flow<List<Category>> = repository.getCategories()
}
