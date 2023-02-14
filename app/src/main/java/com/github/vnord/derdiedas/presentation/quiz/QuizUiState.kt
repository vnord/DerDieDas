package com.github.vnord.derdiedas.presentation.quiz

data class QuizUiState(
    val nounString: String = "Noun",
    val derStatus: CorrectnessStatus = CorrectnessStatus.Neutral,
    val dieStatus: CorrectnessStatus = CorrectnessStatus.Neutral,
    val dasStatus: CorrectnessStatus = CorrectnessStatus.Neutral,
)
