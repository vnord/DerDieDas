package com.github.vnord.derdiedas.domain.usecase

import com.github.vnord.derdiedas.data.entity.Category
import com.github.vnord.derdiedas.data.entity.Noun
import com.github.vnord.derdiedas.domain.model.Categories
import com.github.vnord.derdiedas.domain.repository.NounRepository

class AddNoun(private val repository: NounRepository) {
    suspend operator fun invoke(noun: Noun?, category: Category = Categories.MyNouns) =
        noun?.let { repository.insertNoun(it, category) }
}
