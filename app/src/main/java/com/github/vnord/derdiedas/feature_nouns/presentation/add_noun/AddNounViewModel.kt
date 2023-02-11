package com.github.vnord.derdiedas.feature_nouns.presentation.add_noun

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vnord.derdiedas.feature_nouns.domain.model.Gender
import com.github.vnord.derdiedas.feature_nouns.domain.model.Noun
import com.github.vnord.derdiedas.feature_nouns.domain.use_case.NounUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddNounViewModel @Inject constructor(
    private val nounUseCases: NounUseCases,
) : ViewModel() {
    private val _nounGender = mutableStateOf<Gender?>(null)
    val nounGender: State<Gender?> = _nounGender

    private val _nounText = mutableStateOf("")
    val nounText: State<String> = _nounText

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: AddNounEvent) {
        when (event) {
            is AddNounEvent.SaveNoun -> viewModelScope.launch {
                nounUseCases.addNoun(
                    Noun(
                        noun = nounText.value.trim(),
                        gender = nounGender.value
                            ?: Gender.DAS // TODO: probably shouldn't default when null
                    )
                )
                _eventFlow.emit(UiEvent.SaveNoun)
            }
            is AddNounEvent.EnteredText -> _nounText.value = event.value
            is AddNounEvent.SelectedGender -> _nounGender.value = event.value
        }
    }

    sealed class UiEvent {
        object SaveNoun : UiEvent()
    }
}
