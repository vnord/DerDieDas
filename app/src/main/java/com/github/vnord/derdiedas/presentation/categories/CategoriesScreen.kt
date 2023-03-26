package com.github.vnord.derdiedas.presentation.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.vnord.derdiedas.core.util.rememberStateWithLifecycle
import com.github.vnord.derdiedas.data.entity.Category
import com.github.vnord.derdiedas.domain.model.Categories
import com.github.vnord.derdiedas.presentation.Screen

@Composable
fun CategoriesScreen(
    navController: NavController = rememberNavController(),
    viewModel: CategoriesViewModel = hiltViewModel(),
) {
    val uiState by rememberStateWithLifecycle(viewModel.uiState)

    CategoriesScreen(
        uiState = uiState,
        onClickCategory = { category ->
            navController.navigate(Screen.NounListScreen.route + "?category=${category.categoryName}")
        },
    )
}

@Composable
fun CategoriesScreen(
    uiState: CategoriesUiState,
    onClickCategory: (Category) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(uiState.categories) { category ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .height(128.dp)
                    .padding(vertical = 16.dp, horizontal = 48.dp)
                    .background(color = Color(category.color), shape = RoundedCornerShape(12.dp))
                    .clickable { onClickCategory(category) },
            ) {
                Text(
                    text = category.categoryName,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CategoriesScreenPreview() {
    CategoriesScreen(
        uiState = CategoriesUiState(
            categories = listOf(Categories.MyNouns, Categories.Top100),
        ),
        onClickCategory = {},
    )
}
