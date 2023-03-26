package com.github.vnord.derdiedas.presentation.nounlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vnord.derdiedas.domain.model.Categories
import com.github.vnord.derdiedas.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class NounListViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val routeFlow =
        savedStateHandle.getStateFlow("category", Categories.MyNouns.categoryName)

    val uiState = routeFlow.flatMapLatest { route ->
        val nounsFlow = when (route) {
            Categories.MyNouns.categoryName -> useCases.getNouns(Categories.MyNouns)
            Categories.Top100.categoryName -> useCases.getNouns(Categories.Top100)
            else -> useCases.getNouns(Categories.MyNouns)
        }
        nounsFlow.map {
            NounListUiState(
                nouns = it,
                shouldShowAddButton = route == Categories.MyNouns.categoryName,
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), NounListUiState())
}
