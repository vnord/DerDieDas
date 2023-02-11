package com.github.vnord.derdiedas.feature_nouns.presentation.add_noun

import com.github.vnord.derdiedas.feature_nouns.domain.model.Gender

sealed class AddNounEvent {
    data class EnteredText(val value: String) : AddNounEvent()
    data class SelectedGender(val value: Gender) : AddNounEvent()
    object SaveNoun : AddNounEvent()
}
