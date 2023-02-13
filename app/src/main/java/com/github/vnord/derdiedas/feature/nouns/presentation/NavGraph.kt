package com.github.vnord.derdiedas.feature.nouns.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.vnord.derdiedas.feature.nouns.presentation.Screen.AddNounScreen
import com.github.vnord.derdiedas.feature.nouns.presentation.Screen.DoneScreen
import com.github.vnord.derdiedas.feature.nouns.presentation.Screen.HomeScreen
import com.github.vnord.derdiedas.feature.nouns.presentation.Screen.NounListScreen
import com.github.vnord.derdiedas.feature.nouns.presentation.Screen.QuizScreen
import com.github.vnord.derdiedas.feature.nouns.presentation.addnoun.AddNounScreen
import com.github.vnord.derdiedas.feature.nouns.presentation.done.DoneScreen
import com.github.vnord.derdiedas.feature.nouns.presentation.home.HomeScreen
import com.github.vnord.derdiedas.feature.nouns.presentation.nounlist.NounListScreen
import com.github.vnord.derdiedas.feature.nouns.presentation.quiz.QuizScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = HomeScreen.route) {
        composable(
            route = HomeScreen.route
        ) {
            HomeScreen(navController = navController)
        }
        composable(
            route = NounListScreen.route
        ) {
            NounListScreen(navController = navController)
        }
        composable(
            route = AddNounScreen.route
        ) {
            AddNounScreen(navController = navController)
        }
        composable(
            route = QuizScreen.route
        ) {
            QuizScreen()
        }
        composable(
            route = DoneScreen.route
        ) {
            DoneScreen()
        }
    }
}
