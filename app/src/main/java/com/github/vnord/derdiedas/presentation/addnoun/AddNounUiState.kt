package com.github.vnord.derdiedas.presentation.addnoun

import com.github.vnord.derdiedas.data.entity.Noun

data class AddNounUiState(
    val selectedNounGender: Noun.Gender? = null,
    val nounText: String = "",
)
