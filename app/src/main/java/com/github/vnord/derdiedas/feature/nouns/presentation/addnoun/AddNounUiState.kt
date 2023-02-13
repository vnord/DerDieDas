package com.github.vnord.derdiedas.feature.nouns.presentation.addnoun

import com.github.vnord.derdiedas.feature.nouns.domain.model.Gender

data class AddNounUiState(
    val selectedNounGender: Gender? = null,
    val nounText: String = ""
)
