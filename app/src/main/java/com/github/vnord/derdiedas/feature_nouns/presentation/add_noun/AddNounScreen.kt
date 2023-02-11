package com.github.vnord.derdiedas.feature_nouns.presentation.add_noun

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.vnord.derdiedas.feature_nouns.domain.model.Gender
import com.github.vnord.derdiedas.feature_nouns.presentation.Screen

@Composable
fun NewEntryScreen(
    navController: NavController = rememberNavController(),
    viewModel: AddNounViewModel = hiltViewModel()
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        val radioOptions = Gender.values()
        val nounTextState = viewModel.nounText.value
        val selectedGenderState = viewModel.nounGender.value

        Column {
            Row(horizontalArrangement = Arrangement.End) {
                Column {
                    radioOptions.forEach { gender ->
                        Row {
                            RadioButton(
                                selected = (gender == selectedGenderState),
                                onClick = {
                                    viewModel.onEvent(
                                        AddNounEvent.SelectedGender(Gender.parse(gender.str))
                                    )
                                }
                            )
                            Text(
                                text = gender.toString(),
                                modifier = Modifier.align(CenterVertically)
                            )
                        }
                    }
                }
                OutlinedTextField(
                    value = nounTextState,
                    onValueChange = { viewModel.onEvent(AddNounEvent.EnteredText(it)) },
                    modifier = Modifier
                        .align(CenterVertically)
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            Button(
                onClick = {
                    viewModel.onEvent(AddNounEvent.SaveNoun)
                    navController.navigate(Screen.NounListScreen.route) {
                        popUpTo(Screen.NounListScreen.route) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 100.dp),
                enabled = selectedGenderState?.str?.trim() != "" && nounTextState != ""
            ) {
                Image(imageVector = Icons.Default.Save, contentDescription = "Save noun")
            }
        }
    }
}

@Composable
@Preview
fun NewEntryScreenPreview() {
    NewEntryScreen()
}
