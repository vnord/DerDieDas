package com.github.vnord.derdiedas.presentation.quiz

import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vnord.derdiedas.domain.model.Gender
import com.github.vnord.derdiedas.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    useCases: UseCases,
) : ViewModel() {
    private val nounText = MutableStateFlow("Noun")
    private val derStatus = MutableStateFlow<CorrectnessStatus>(CorrectnessStatus.Neutral)
    private val dieStatus = MutableStateFlow<CorrectnessStatus>(CorrectnessStatus.Neutral)
    private val dasStatus = MutableStateFlow<CorrectnessStatus>(CorrectnessStatus.Neutral)
    val statuses = mapOf(Gender.DER to derStatus, Gender.DIE to dieStatus, Gender.DAS to dasStatus)

    val uiState: StateFlow<QuizUiState> = combine(
        nounText,
        derStatus,
        dieStatus,
        dasStatus,
    ) { nounText, derStatus, dieStatus, dasStatus ->
        QuizUiState(
            nounText,
            derStatus,
            dieStatus,
            dasStatus,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = QuizUiState(),
    )

    suspend fun clickDer() {
        statuses[correctGender]?.value = CorrectnessStatus.Correct
        if (correctGender != Gender.DER) derStatus.value = CorrectnessStatus.Incorrect
        resetStatuses()
        correctGender = Gender.values().random()
    }
    suspend fun clickDie() {
        statuses[correctGender]?.value = CorrectnessStatus.Correct
        if (correctGender != Gender.DIE) dieStatus.value = CorrectnessStatus.Incorrect
        resetStatuses()
        correctGender = Gender.values().random()
    }
    suspend fun clickDas() {
        statuses[correctGender]?.value = CorrectnessStatus.Correct
        if (correctGender != Gender.DAS) dasStatus.value = CorrectnessStatus.Incorrect
        resetStatuses()
        correctGender = Gender.values().random()
    }

    private suspend fun resetStatuses() = coroutineScope {
        delay(2000)
        statuses.values.forEach { it.value = CorrectnessStatus.Neutral }
    }

    private var correctGender: Gender = Gender.values().random()
        set(value) {
            println("new correct gender: ${value.str}")
            field = value
        }
}

sealed class CorrectnessStatus {
    object Neutral : CorrectnessStatus()
    object Correct : CorrectnessStatus()
    object Incorrect : CorrectnessStatus()

    @Composable
    fun getColor(): Color {
        return when (this) {
            Incorrect -> MaterialTheme.colors.error
            Correct -> Color.Green
            Neutral -> MaterialTheme.colors.primarySurface
        }
    }
}
