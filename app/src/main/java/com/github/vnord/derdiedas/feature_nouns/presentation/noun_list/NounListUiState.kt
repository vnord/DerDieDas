package com.github.vnord.derdiedas.feature_nouns.presentation.noun_list

import com.github.vnord.derdiedas.feature_nouns.domain.model.Noun

data class NounListUiState(
    val nouns: List<Noun> = emptyList()
)
