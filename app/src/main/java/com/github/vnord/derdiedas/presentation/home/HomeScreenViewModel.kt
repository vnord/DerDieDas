package com.github.vnord.derdiedas.presentation.home

import androidx.lifecycle.ViewModel
import com.github.vnord.derdiedas.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    useCases: UseCases,
) : ViewModel() {
    val categories = useCases.getCategories().onEach { flow { emit(it) } }
}
