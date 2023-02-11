package com.github.vnord.derdiedas.feature_nouns.presentation.noun_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vnord.derdiedas.feature_nouns.domain.use_case.NounUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class NounListViewModel @Inject constructor(
    private val nounUseCases: NounUseCases
) : ViewModel() {
    private val _state = mutableStateOf<NounListState>(NounListState())
    val state: State<NounListState> = _state

    private var getNounsJob: Job? = null

    init {
        getNouns()
    }

    private fun getNouns() {
        getNounsJob?.cancel()
        getNounsJob = nounUseCases.getNouns()
            .onEach { nouns -> _state.value = state.value.copy(nouns = nouns) }
            .launchIn(viewModelScope)
    }
}
