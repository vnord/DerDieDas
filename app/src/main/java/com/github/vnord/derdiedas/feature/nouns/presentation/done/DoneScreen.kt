package com.github.vnord.derdiedas.feature.nouns.presentation.done

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun DoneScreen() {
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
        Text(
            text = "No more items to review; well done!",
            textAlign = TextAlign.Center,
            fontSize = 40.sp
        )
    }
}

@Composable
@Preview
fun DoneScreenPreview() {
    DoneScreen()
}
