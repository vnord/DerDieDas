package com.github.vnord.derdiedas.presentation.quiz

import com.github.vnord.derdiedas.domain.model.Gender

data class QuizUiState(
    val nounString: String = "Noun",
    val genderButtonStates: Map<Gender, GenderButtonState> = Gender.values().associateWith { GenderButtonState.Normal },
    val currentNounDone: Boolean = false,
)
