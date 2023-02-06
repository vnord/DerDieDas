package com.github.vnord.derdiedas

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun QuizScreen(navController: NavController) {}

@Composable
@Preview
fun QuizScreenPreview() {
    QuizScreen(navController = rememberNavController())
}
