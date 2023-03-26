package com.github.vnord.derdiedas.presentation.quiz

import com.github.vnord.derdiedas.data.entity.Noun

data class QuizUiState(
    val nounString: String = "Noun",
    val genderButtonStates: Map<Noun.Gender, GenderButtonState> = Noun.Gender.values()
        .associateWith { GenderButtonState.Normal },
    val currentNounDone: Boolean = false,
)
