package com.github.vnord.derdiedas.presentation.nounlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.vnord.derdiedas.R
import com.github.vnord.derdiedas.core.util.rememberStateWithLifecycle
import com.github.vnord.derdiedas.data.entity.Noun
import com.github.vnord.derdiedas.presentation.Screen

@Composable
fun NounListScreen(
    navController: NavController = rememberNavController(),
    viewModel: NounListViewModel = hiltViewModel(),
) {
    val uiState by rememberStateWithLifecycle(viewModel.uiState)

    NounListScreen(
        uiState = uiState,
        onClickNewButton = { navController.navigate(Screen.AddNounScreen.route) },
    )
}

@Composable
private fun NounListScreen(
    uiState: NounListUiState,
    onClickNewButton: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            items(items = uiState.nouns) {
                NounEntry(it)
                Divider(Modifier.padding(5.dp))
            }
        }
        if (uiState.shouldShowAddButton) {
            Button(
                onClick = { onClickNewButton() },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
            ) {
                Image(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_noun),
                )
            }
        }
    }
}

@Composable
fun NounEntry(noun: Noun) {
    Row(modifier = Modifier.fillMaxSize()) {
        Text(
            text = noun.gender.str,
            textAlign = TextAlign.Start,
        )
        Text(text = " ")
        Text(
            text = noun.nounString,
            textAlign = TextAlign.End,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun NounListScreenPreview() {
    val nouns = List(20) {
        Noun(
            nounString = listOf("Frau", "Mann", "FooBar", "Whatever").random(),
            gender = Noun.Gender.values().random(),
        )
    }
    NounListScreen(
        uiState = NounListUiState(
            nouns = nouns,
            shouldShowAddButton = true,
        ),
        onClickNewButton = {},
    )
}
