package com.github.vnord.derdiedas.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.vnord.derdiedas.R
import com.github.vnord.derdiedas.presentation.Screen

@Composable
fun HomeScreen(navController: NavController = rememberNavController()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Button(
            onClick = { navController.navigate(Screen.NounListScreen.route) },
            modifier = Modifier.align(Alignment.TopEnd),
        ) {
            Image(
                imageVector = Icons.Default.List,
                contentDescription = stringResource(R.string.noun_list),
            )
        }
        Button(
            onClick = { navController.navigate(Screen.QuizScreen.route) },
            modifier = Modifier.align(Alignment.BottomCenter),
        ) {
            Image(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = stringResource(id = R.string.start_quiz),
            )
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}
