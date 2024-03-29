package com.github.vnord.derdiedas.presentation.addnoun

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.vnord.derdiedas.R
import com.github.vnord.derdiedas.core.util.TestTags
import com.github.vnord.derdiedas.core.util.rememberStateWithLifecycle
import com.github.vnord.derdiedas.data.entity.Noun

@Composable
fun AddNounScreen(
    navController: NavController = rememberNavController(),
    viewModel: AddNounViewModel = hiltViewModel(),
) {
    val uiState by rememberStateWithLifecycle(viewModel.uiState)

    MaterialTheme {
        AddNounScreen(
            uiState = uiState,
            onEnteredNounText = viewModel::enterNounText,
            onSelectedGender = viewModel::selectGender,
            onSave = {
                viewModel.onSave()
                navController.navigateUp()
            },
        )
    }
}

@Composable
private fun AddNounScreen(
    uiState: AddNounUiState,
    onEnteredNounText: (String) -> Unit,
    onSelectedGender: (Noun.Gender) -> Unit,
    onSave: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column {
            Row(horizontalArrangement = Arrangement.End) {
                Column(modifier = Modifier.testTag(TestTags.GENDER_BUTTONS)) {
                    Noun.Gender.values().forEach { gender ->
                        Row {
                            RadioButton(
                                selected = (gender == uiState.selectedNounGender),
                                onClick = { onSelectedGender(gender) },
                            )
                            Text(
                                text = gender.str,
                                modifier = Modifier.align(CenterVertically),
                            )
                        }
                    }
                }
                OutlinedTextField(
                    value = uiState.nounText,
                    onValueChange = { onEnteredNounText(it) },
                    singleLine = true,
                    modifier = Modifier
                        .align(CenterVertically)
                        .fillMaxWidth()
                        .padding(16.dp),
                )
            }
            Button(
                onClick = {
                    onSave()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 100.dp),
                enabled = !uiState.selectedNounGender?.str?.trim().isNullOrBlank() &&
                    uiState.nounText != "",
            ) {
                Image(
                    imageVector = Icons.Default.Save,
                    contentDescription = stringResource(R.string.save_noun),
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NewEntryScreenPreview() {
    AddNounScreen(
        uiState = AddNounUiState(),
        onEnteredNounText = {},
        onSelectedGender = {},
        onSave = {},
    )
}
