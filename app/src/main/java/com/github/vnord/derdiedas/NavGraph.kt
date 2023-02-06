
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.vnord.derdiedas.*

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(
            route = Screen.HomeScreen.route
        ) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.NounListScreen.route
        ) {
            NounListScreen(navController = navController)
        }
        composable(
            route = Screen.NewEntryScreen.route
        ) {
            NewEntryScreen(navController = navController)
        }
        composable(
            route = Screen.QuizScreen.route
        ) {
            QuizScreen(navController = navController)
        }
        composable(
            route = Screen.DoneScreen.route
        ) {
            DoneScreen()
        }
    }
}
