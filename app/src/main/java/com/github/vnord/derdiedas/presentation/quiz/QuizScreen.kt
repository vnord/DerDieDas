package com.github.vnord.derdiedas.presentation.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.vnord.derdiedas.util.rememberStateWithLifecycle
import kotlinx.coroutines.launch

@Composable
fun QuizScreen(
    navController: NavController = rememberNavController(),
    viewModel: QuizViewModel = hiltViewModel(),
) {
    val uiState by rememberStateWithLifecycle(viewModel.uiState)

    val coroutineScope = rememberCoroutineScope()

    QuizScreen(
        uiState = uiState,
        onClickDerButton = { coroutineScope.launch { viewModel.clickDer() } },
        onClickDieButton = { coroutineScope.launch { viewModel.clickDie() } },
        onClickDasButton = { coroutineScope.launch { viewModel.clickDas() } },
    )
}

@Composable
private fun QuizScreen(
    uiState: QuizUiState,
    onClickDerButton: () -> Unit,
    onClickDieButton: () -> Unit,
    onClickDasButton: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Hello",
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(CenterHorizontally),
            )
        }
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Button(
                onClick = { onClickDerButton() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = uiState.derStatus.getColor()),
            ) {
                Text(text = "Der", style = MaterialTheme.typography.h2)
            }
            Button(
                onClick = { onClickDieButton() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = uiState.dieStatus.getColor()),
            ) {
                Text(text = "Die", style = MaterialTheme.typography.h2)
            }
            Button(
                onClick = { onClickDasButton() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = uiState.dasStatus.getColor()),
            ) {
                Text(text = "Das", style = MaterialTheme.typography.h2)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun QuizScreenPreview() {
    QuizScreen(
        uiState = QuizUiState(),
        onClickDasButton = {},
        onClickDerButton = {},
        onClickDieButton = {},
    )
}
