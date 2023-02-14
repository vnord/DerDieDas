package com.github.vnord.derdiedas.presentation.nounlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vnord.derdiedas.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NounListViewModel @Inject constructor(
    useCases: UseCases,
) : ViewModel() {
    val uiState = useCases.getNouns().map { NounListUiState(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), NounListUiState())
}
