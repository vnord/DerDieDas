package com.github.vnord.derdiedas.presentation.quiz.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.vnord.derdiedas.domain.model.Gender

@Composable
fun GenderButtons(
    isGenderButtonEnabled: (Gender) -> Boolean,
    onClickGenderButton: (Gender) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        Gender.values().map { gender ->
            Button(
                onClick = { onClickGenderButton(gender) },
                modifier = Modifier.fillMaxWidth(0.4f).align(CenterHorizontally),
                enabled = isGenderButtonEnabled(gender),
            ) {
                Text(text = gender.str, style = MaterialTheme.typography.h4)
            }
        }
    }
}
