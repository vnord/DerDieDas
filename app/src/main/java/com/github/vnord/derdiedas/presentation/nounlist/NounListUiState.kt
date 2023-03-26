package com.github.vnord.derdiedas.presentation.nounlist

import com.github.vnord.derdiedas.data.entity.Noun

data class NounListUiState(
    val nouns: List<Noun> = emptyList(),
    val shouldShowAddButton: Boolean = false,
)
