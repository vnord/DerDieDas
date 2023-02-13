package com.github.vnord.derdiedas.presentation.addnoun

import com.github.vnord.derdiedas.domain.model.Gender

data class AddNounUiState(
    val selectedNounGender: Gender? = null,
    val nounText: String = ""
)
