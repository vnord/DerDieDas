package com.github.vnord.derdiedas.presentation.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vnord.derdiedas.domain.model.Gender
import com.github.vnord.derdiedas.domain.model.Noun
import com.github.vnord.derdiedas.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {
    private val currentNounState = MutableStateFlow<Noun?>(null)
    private val genderButtonStates = MutableStateFlow<Map<Gender, GenderButtonState>>(
        Gender.values().associateWith { GenderButtonState.Normal },
    )
    private val currentNounDone: StateFlow<Boolean> = combine(
        genderButtonStates,
        currentNounState,
    ) { genderButtonStates, currentNounState ->
        genderButtonStates.values.count { it == GenderButtonState.Normal } == 1 || currentNounState == null
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

    val uiState: StateFlow<QuizUiState> = combine(
        currentNounState,
        genderButtonStates,
        currentNounDone,
    ) { currentNounState, genderButtonStates, currentNounDone ->
        QuizUiState(
            currentNounState?.noun ?: "",
            genderButtonStates,
            currentNounDone,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = QuizUiState(),
    )

    private fun Gender.isCorrect() = this == currentNounState.value?.gender

    private val quizQueue = mutableListOf<Noun>()

    init {
        viewModelScope.launch {
            quizQueue.addAll(useCases.getNouns().first())
            currentNounState.value = useCases.getNextNoun()
        }
    }

    fun genderButtonClicked(gender: Gender) {
        if (gender.isCorrect()) {
            genderButtonStates.value =
                Gender.values().associateWith { GenderButtonState.Eliminated }
                    .plus(gender to GenderButtonState.Normal)
        } else {
            genderButtonStates.value =
                genderButtonStates.value.plus(gender to GenderButtonState.Eliminated)
        }
    }

    fun clickNext() {
        genderButtonStates.value = Gender.values().associateWith { GenderButtonState.Normal }
        viewModelScope.launch {
            currentNounState.value = useCases.getNextNoun()
        }
    }
}

sealed class GenderButtonState {
    object Normal : GenderButtonState()
    object Eliminated : GenderButtonState()
}
