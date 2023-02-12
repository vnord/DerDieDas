package com.github.vnord.derdiedas.feature_nouns.presentation.noun_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vnord.derdiedas.feature_nouns.domain.use_case.NounUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class NounListViewModel @Inject constructor(
    private val nounUseCases: NounUseCases
) : ViewModel() {
    private val foo = MutableStateFlow(false)
    private val nouns =
        nounUseCases.getNouns().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), listOf())

    val uiState: StateFlow<NounListUiState> = combine(
        foo, nouns
    ) { foo, nouns ->
        NounListUiState(
            foo = foo,
            nouns = nouns
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), NounListUiState.Empty)
}
