package com.github.vnord.derdiedas.feature_nouns.presentation.add_noun

import com.github.vnord.derdiedas.feature_nouns.domain.model.Gender

data class AddNounUiState(
    val selectedNounGender: Gender? = null,
    val nounText: String = ""
) {
    companion object {
        val Empty = AddNounUiState()
    }
}
