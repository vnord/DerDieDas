package com.github.vnord.derdiedas.feature.nouns.presentation.nounlist

import com.github.vnord.derdiedas.feature.nouns.domain.model.Noun

data class NounListUiState(
    val nouns: List<Noun> = emptyList()
)
