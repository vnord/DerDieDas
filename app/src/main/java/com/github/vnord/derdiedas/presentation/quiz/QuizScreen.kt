package com.github.vnord.derdiedas.presentation.quiz

import NextNounButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.vnord.derdiedas.core.util.rememberStateWithLifecycle
import com.github.vnord.derdiedas.domain.model.Gender
import com.github.vnord.derdiedas.presentation.Screen
import com.github.vnord.derdiedas.presentation.quiz.components.GenderButtons
import kotlinx.coroutines.flow.collectLatest

@Composable
fun QuizScreen(
    navController: NavController = rememberNavController(),
    viewModel: QuizViewModel = hiltViewModel(),
) {
    val uiState by rememberStateWithLifecycle(viewModel.uiState)

    LaunchedEffect(key1 = true) {
        viewModel.eventFLow.collectLatest { event ->
            when (event) {
                is UiEvent.NavigateToDone -> navController.navigate(Screen.DoneScreen.route) {
                    popUpTo(Screen.QuizScreen.route) { inclusive = true }
                }
                null -> {}
            }
        }
    }

    QuizScreen(
        uiState = uiState,
        onClickGenderButton = viewModel::genderButtonClicked,
        onClickNext = viewModel::clickNext,
    )
}

@Composable
private fun QuizScreen(
    uiState: QuizUiState,
    onClickGenderButton: (Gender) -> Unit,
    onClickNext: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = uiState.nounString,
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(CenterHorizontally),
                softWrap = false, // TODO: probably not right
            )
            Spacer(modifier = Modifier.weight(1f))
            GenderButtons(
                isGenderButtonEnabled = { gender -> uiState.genderButtonStates[gender] == GenderButtonState.Normal },
                onClickGenderButton = onClickGenderButton,
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        NextNounButton(
            modifier = Modifier.align(Alignment.BottomCenter).padding(10.dp),
            onClick = onClickNext,
            enabled = uiState.currentNounDone,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun QuizScreenPreview() {
    QuizScreen(
        uiState = QuizUiState(),
        onClickGenderButton = {},
        onClickNext = {},
    )
}
