package com.github.vnord.derdiedas.presentation.done

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.github.vnord.derdiedas.R

@Composable
fun DoneScreen() {
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.done_text),
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
        )
    }
}

@Composable
@Preview
fun DoneScreenPreview() {
    DoneScreen()
}
