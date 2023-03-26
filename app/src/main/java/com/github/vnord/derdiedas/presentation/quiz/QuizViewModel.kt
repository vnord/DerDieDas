package com.github.vnord.derdiedas.presentation.quiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vnord.derdiedas.data.entity.Noun
import com.github.vnord.derdiedas.domain.model.Categories
import com.github.vnord.derdiedas.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val currentNounState = MutableStateFlow<Noun?>(null)
    private val genderButtonStates = MutableStateFlow<Map<Noun.Gender, GenderButtonState>>(
        Noun.Gender.values().associateWith { GenderButtonState.Normal },
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
            currentNounState?.nounString ?: "",
            genderButtonStates,
            currentNounDone,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = QuizUiState(),
    )

    private fun Noun.Gender.isCorrect() = this == currentNounState.value?.gender

    private val _eventFlow = MutableStateFlow<UiEvent?>(null)
    val eventFLow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            val firstNoun =
                useCases.getNextNoun.getFirstNoun(
                    category = Categories.fromString(
                        savedStateHandle.get<String>(
                            "category",
                        ) ?: "My Nouns",
                    ), /* TODO: this isn't very neat */
                )
            if (firstNoun == null) {
                _eventFlow.emit(UiEvent.NavigateToDone)
            } else {
                currentNounState.value = firstNoun
            }
        }
    }

    fun genderButtonClicked(gender: Noun.Gender) {
        if (gender.isCorrect()) {
            genderButtonStates.value =
                Noun.Gender.values().associateWith { GenderButtonState.Eliminated }
                    .plus(gender to GenderButtonState.Normal)
        } else {
            genderButtonStates.value =
                genderButtonStates.value.plus(gender to GenderButtonState.Eliminated)
        }
    }

    fun clickNext() {
        genderButtonStates.value = Noun.Gender.values().associateWith { GenderButtonState.Normal }
        viewModelScope.launch {
            currentNounState.value = useCases.getNextNoun()
            if (currentNounState.value == null) {
                _eventFlow.emit(UiEvent.NavigateToDone)
            }
        }
    }
}

sealed class GenderButtonState {
    object Normal : GenderButtonState()
    object Eliminated : GenderButtonState()
}

sealed class UiEvent {
    object NavigateToDone : UiEvent()
}
