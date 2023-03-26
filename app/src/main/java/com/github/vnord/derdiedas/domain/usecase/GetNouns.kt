package com.github.vnord.derdiedas.domain.usecase

import com.github.vnord.derdiedas.data.entity.Category
import com.github.vnord.derdiedas.data.entity.Noun
import com.github.vnord.derdiedas.domain.model.Categories
import com.github.vnord.derdiedas.domain.repository.NounRepository
import kotlinx.coroutines.flow.Flow

class GetNouns(private val repository: NounRepository) {
    operator fun invoke(category: Category = Categories.MyNouns): Flow<List<Noun>> =
        repository.getNouns(category)
}
