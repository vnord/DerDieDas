package com.github.vnord.derdiedas.feature.nouns.presentation

sealed class Screen(val route: String) {
    object HomeScreen : Screen(route = "home_screen")
    object NounListScreen : Screen(route = "noun_list_screen")
    object AddNounScreen : Screen(route = "add_noun_screen")
    object QuizScreen : Screen(route = "quiz_screen")
    object DoneScreen : Screen(route = "done_screen")
}
