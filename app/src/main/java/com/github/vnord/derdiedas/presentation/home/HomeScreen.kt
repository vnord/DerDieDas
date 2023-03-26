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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.vnord.derdiedas.R
import com.github.vnord.derdiedas.data.entity.Category
import com.github.vnord.derdiedas.domain.model.Categories
import com.github.vnord.derdiedas.presentation.Screen
import com.github.vnord.derdiedas.presentation.components.DropDownList

@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Button(
            onClick = { navController.navigate(Screen.CategoriesScreen.route) },
            modifier = Modifier.align(Alignment.TopEnd),
        ) {
            Image(
                imageVector = Icons.Default.List,
                contentDescription = stringResource(R.string.categories),
            )
        }

        val categories = viewModel.categories
            .collectAsState(initial = listOf(Categories.MyNouns) /* TODO: this is not quite right */)
            .value.map(Category::categoryName)

        val selectedCategory = remember { mutableStateOf(categories.first()) }
        DropDownList(items = categories, modifier = Modifier.align(Alignment.Center), selectedItem = selectedCategory)

        Button(
            onClick = { navController.navigate(Screen.QuizScreen.createRoute(selectedCategory.value)) },
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
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen()
}
