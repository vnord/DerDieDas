package com.github.vnord.derdiedas.presentation.addnoun

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vnord.derdiedas.domain.model.Gender
import com.github.vnord.derdiedas.domain.model.Noun
import com.github.vnord.derdiedas.domain.usecase.NounUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class AddNounViewModel @Inject constructor(
    private val nounUseCases: NounUseCases,
) : ViewModel() {
    private val selectedNounGender = MutableStateFlow<Gender?>(null)
    private val nounText = MutableStateFlow("")

    val uiState: StateFlow<AddNounUiState> = combine(
        selectedNounGender, nounText
    ) { selectedNounGender, nounText ->
        AddNounUiState(
            selectedNounGender = selectedNounGender,
            nounText = nounText
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = AddNounUiState()
    )

    fun selectGender(gender: Gender) {
        selectedNounGender.value = gender
    }

    fun enterNounText(text: String) {
        nounText.value = text
    }

    fun onSave() = viewModelScope.launch {
        nounUseCases.addNoun(
            Noun(
                noun = nounText.value.trim(),
                gender = selectedNounGender.value
                    ?: Gender.DAS // TODO: probably shouldn't default when null
            )
        )
    }
}
