package com.github.vnord.derdiedas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.vnord.derdiedas.data.Gender
import com.github.vnord.derdiedas.data.Noun
import com.github.vnord.derdiedas.data.NounDao
import com.github.vnord.derdiedas.data.NounRoomDatabase

@Composable
fun NounListScreen(navController: NavController) {
    val nounDao: NounDao = NounRoomDatabase.getDatabase(LocalContext.current).nounDao()
    val nounList by nounDao.getNouns().collectAsState(initial = listOf())
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) { items(items = nounList) { NounEntry(it) } }
        Button(
            onClick = { navController.navigate(Screen.NewEntryScreen.route) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Image(
                imageVector = Icons.Default.Add,
                contentDescription = "Start quiz"
            )

        }
    }
}

@Composable
fun NounListBox(navController: NavController, nounList: List<Noun>) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) { items(nounList) { NounEntry(it) } }
        Button(
            onClick = { navController.navigate(Screen.NewEntryScreen.route) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Image(
                imageVector = Icons.Default.Add,
                contentDescription = "Start quiz"
            )

        }
    }
}

@Composable
fun NounEntry(noun: Noun) {
    Row(modifier = Modifier.fillMaxSize()) {
        Text(text = noun.gender.toString(), textAlign = TextAlign.Start)
        Text(text = " ")
        Text(text = noun.noun, textAlign = TextAlign.End)
    }
}

@Composable
@Preview
fun NounListScreenPreview() {
    NounListBox(
        navController = rememberNavController(),
        nounList = List(20) { Noun("Mann", Gender.DER) }
    )
}
