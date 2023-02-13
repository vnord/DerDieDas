package com.github.vnord.derdiedas.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.vnord.derdiedas.presentation.Screen.AddNounScreen
import com.github.vnord.derdiedas.presentation.Screen.DoneScreen
import com.github.vnord.derdiedas.presentation.Screen.HomeScreen
import com.github.vnord.derdiedas.presentation.Screen.NounListScreen
import com.github.vnord.derdiedas.presentation.Screen.QuizScreen
import com.github.vnord.derdiedas.presentation.addnoun.AddNounScreen
import com.github.vnord.derdiedas.presentation.done.DoneScreen
import com.github.vnord.derdiedas.presentation.home.HomeScreen
import com.github.vnord.derdiedas.presentation.nounlist.NounListScreen
import com.github.vnord.derdiedas.presentation.quiz.QuizScreen

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
