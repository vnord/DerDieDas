package com.github.vnord.derdiedas.presentation.addnoun

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vnord.derdiedas.data.entity.Noun
import com.github.vnord.derdiedas.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNounViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {
    private val selectedNounGender = MutableStateFlow<Noun.Gender?>(null)
    private val nounText = MutableStateFlow("")

    val uiState: StateFlow<AddNounUiState> = combine(
        selectedNounGender,
        nounText,
    ) { selectedNounGender, nounText ->
        AddNounUiState(
            selectedNounGender = selectedNounGender,
            nounText = nounText,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = AddNounUiState(),
    )

    fun selectGender(gender: Noun.Gender) {
        selectedNounGender.value = gender
    }

    fun enterNounText(text: String) {
        nounText.value = text
    }

    fun onSave() = viewModelScope.launch {
        val noun = selectedNounGender.value?.let { gender ->
            Noun(
                nounString = nounText.value.trim(),
                gender = gender,
            )
        }
        useCases.addNoun(noun)
    }
}
