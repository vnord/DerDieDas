package com.github.vnord.derdiedas.feature_nouns.presentation.noun_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vnord.derdiedas.feature_nouns.domain.use_case.NounUseCases
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
