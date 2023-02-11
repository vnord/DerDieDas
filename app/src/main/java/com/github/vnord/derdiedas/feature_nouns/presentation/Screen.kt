package com.github.vnord.derdiedas.feature_nouns.presentation

sealed class Screen(val route: String) {
    object HomeScreen : Screen(route = "home_screen")
    object NounListScreen : Screen(route = "noun_list_screen")
    object NewEntryScreen : Screen(route = "new_entry_screen")
    object QuizScreen : Screen(route = "quiz_screen")
    object DoneScreen : Screen(route = "done_screen")
}