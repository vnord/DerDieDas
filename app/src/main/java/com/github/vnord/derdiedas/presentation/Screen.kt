package com.github.vnord.derdiedas.presentation

sealed class Screen(val route: String) {
    object HomeScreen : Screen(route = "home_screen")
    object NounListScreen : Screen(route = "noun_list_screen")
    object AddNounScreen : Screen(route = "add_noun_screen")
    object QuizScreen : Screen(route = "quiz_screen/{category}") {
        fun createRoute(category: String) = "quiz_screen/$category"
    }
    object DoneScreen : Screen(route = "done_screen")
    object CategoriesScreen : Screen(route = "categories_screen")
}
