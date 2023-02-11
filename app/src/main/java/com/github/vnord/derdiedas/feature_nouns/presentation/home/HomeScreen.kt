package com.github.vnord.derdiedas.feature_nouns.presentation.home

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.vnord.derdiedas.feature_nouns.presentation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = { navController.navigate(Screen.NounListScreen.route) },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Image(
                imageVector = Icons.Default.List,
                contentDescription = "Noun list"
            )
        }
        Button(
            onClick = { navController.navigate(Screen.DoneScreen.route) },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Image(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Start quiz"
            )
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
