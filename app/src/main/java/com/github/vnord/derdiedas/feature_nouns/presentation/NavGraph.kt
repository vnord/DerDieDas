package com.github.vnord.derdiedas.feature_nouns.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.vnord.derdiedas.feature_nouns.presentation.Screen.DoneScreen
import com.github.vnord.derdiedas.feature_nouns.presentation.Screen.HomeScreen
import com.github.vnord.derdiedas.feature_nouns.presentation.Screen.NewEntryScreen
import com.github.vnord.derdiedas.feature_nouns.presentation.Screen.NounListScreen
import com.github.vnord.derdiedas.feature_nouns.presentation.Screen.QuizScreen
import com.github.vnord.derdiedas.feature_nouns.presentation.add_noun.NewEntryScreen
import com.github.vnord.derdiedas.feature_nouns.presentation.done.DoneScreen
import com.github.vnord.derdiedas.feature_nouns.presentation.home.HomeScreen
import com.github.vnord.derdiedas.feature_nouns.presentation.noun_list.NounListScreen
import com.github.vnord.derdiedas.feature_nouns.presentation.quiz.QuizScreen

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
            route = NewEntryScreen.route
        ) {
            NewEntryScreen(navController = navController)
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
