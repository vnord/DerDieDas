package com.github.vnord.derdiedas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.vnord.derdiedas.data.Gender
import com.github.vnord.derdiedas.data.Noun
import com.github.vnord.derdiedas.data.NounDao
import com.github.vnord.derdiedas.data.NounRoomDatabase
import kotlinx.coroutines.launch

@Composable
fun NewEntryScreen(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        val nounDao: NounDao = NounRoomDatabase.getDatabase(LocalContext.current).nounDao()
        val coroutineScope = rememberCoroutineScope()
        val radioOptions = Gender.values()
        val (selectedGender, onGenderSelected) = remember { mutableStateOf("") }
        val (noun, onNounChanged) = remember { mutableStateOf("") }
        Column {
            Row(horizontalArrangement = Arrangement.End) {
                Column {
                    radioOptions.forEach { gender ->
                        Row {
                            RadioButton(
                                selected = (gender.toString() == selectedGender),
                                onClick = { onGenderSelected(gender.toString()) }
                            )
                            Text(
                                text = gender.toString(),
                                modifier = Modifier.align(CenterVertically)
                            )
                        }
                    }
                }
                OutlinedTextField(
                    value = noun,
                    onValueChange = { onNounChanged(it) },
                    modifier = Modifier
                        .align(CenterVertically)
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            Button(
                onClick = {
                    coroutineScope.launch {
                        nounDao.insert(Noun(noun, Gender.parse(selectedGender)))
                    }
                    navController.navigate(Screen.NounListScreen.route) {
                        popUpTo(Screen.NounListScreen.route) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 100.dp),
                enabled = selectedGender != "" && noun != ""
            ) {
                Image(imageVector = Icons.Default.Check, contentDescription = "Submit noun")
            }
        }
    }
}

@Composable
@Preview
fun NewEntryScreenPreview() {
    NewEntryScreen(navController = rememberNavController())
}
