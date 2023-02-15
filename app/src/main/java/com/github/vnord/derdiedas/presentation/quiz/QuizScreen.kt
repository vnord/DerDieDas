package com.github.vnord.derdiedas.presentation.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.vnord.derdiedas.R
import com.github.vnord.derdiedas.core.util.rememberStateWithLifecycle
import com.github.vnord.derdiedas.domain.model.Gender

@Composable
fun QuizScreen(
    navController: NavController = rememberNavController(),
    viewModel: QuizViewModel = hiltViewModel(),
) {
    val uiState by rememberStateWithLifecycle(viewModel.uiState)

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = uiState.nounString,
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(CenterHorizontally),
            )
        }
        // TODO: button column should remain fixed even if text is two rows
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.Bottom),
        ) {
            Gender.values().map { gender ->
                Button(
                    onClick = { onClickGenderButton(gender) },
                    modifier = Modifier.fillMaxWidth(0.4f).align(CenterHorizontally),
                    enabled = uiState.genderButtonStates[gender] == GenderButtonState.Normal,
                ) {
                    Text(text = gender.str, style = MaterialTheme.typography.h4)
                }
            }
        }
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { onClickNext() }, enabled = uiState.currentNounDone) {
                Icon(
                    imageVector = Icons.Default.SkipNext,
                    contentDescription = stringResource(R.string.next_noun),
                )
            }
        }
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
