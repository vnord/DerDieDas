package com.github.vnord.derdiedas.feature.nouns.presentation.nounlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vnord.derdiedas.feature.nouns.domain.usecase.NounUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class NounListViewModel @Inject constructor(
    nounUseCases: NounUseCases
) : ViewModel() {
    val uiState = nounUseCases.getNouns().map { NounListUiState(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), NounListUiState())
}
