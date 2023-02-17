package com.github.vnord.derdiedas.presentation.nounlist

import com.github.vnord.derdiedas.domain.model.Noun

data class NounListUiState(
    val nouns: List<Noun> = emptyList()
)
