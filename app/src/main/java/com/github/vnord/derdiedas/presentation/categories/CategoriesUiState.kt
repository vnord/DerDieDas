package com.github.vnord.derdiedas.presentation.categories

import com.github.vnord.derdiedas.data.entity.Category

data class CategoriesUiState(
    val categories: List<Category> = emptyList(),
)
