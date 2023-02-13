package com.github.vnord.derdiedas.feature.nouns.presentation.addnoun

import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.isEnabled
import androidx.compose.ui.test.isNotEnabled
import androidx.compose.ui.test.isNotSelected
import androidx.compose.ui.test.isSelectable
import androidx.compose.ui.test.isSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.vnord.derdiedas.R
import com.github.vnord.derdiedas.core.util.TestTags
import com.github.vnord.derdiedas.di.AppModule
import com.github.vnord.derdiedas.feature.nouns.presentation.MainActivity
import com.github.vnord.derdiedas.feature.nouns.presentation.Screen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class AddNounScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            MaterialTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.AddNounScreen.route,
                ) {
                    composable(route = Screen.AddNounScreen.route) {
                        AddNounScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun addANoun(): Unit = with(composeRule) {
        // The gender selection buttons should exist
        onNodeWithTag(TestTags.GENDER_BUTTONS).assertExists()
        // There should be 3 radio buttons, all unselected
        onAllNodes(isSelectable()).assertCountEquals(3).assertAll(isNotSelected())
        // Click one of them
        onAllNodes(isSelectable()).onFirst().performClick()
        onAllNodes(isSelected()).assertCountEquals(1)
        onNode(hasSetTextAction()).performTextInput("Noun")
        val saveNounString = activity.getString(R.string.save_noun)
        onNode(hasContentDescription(saveNounString)).assert(isEnabled())
    }

    @Test
    fun saveButtonDisabledWhenContentIsMissing(): Unit = with(composeRule) {
        // Save button should be disabled initially
        onNode(hasContentDescription(activity.getString(R.string.save_noun))).assert(isNotEnabled())
        // Should still be disabled after entering text because no gender is selected
        onNode(hasSetTextAction()).performTextInput("Noun")
        onNode(hasContentDescription(activity.getString(R.string.save_noun))).assert(isNotEnabled())
        // Delete the text and make sure that save button is disabled when the text is empty but
        // a gender is selected
        onNode(hasSetTextAction()).performTextClearance()
        onAllNodes(isSelectable()).onFirst().performClick()
        onNode(hasContentDescription(activity.getString(R.string.save_noun))).assert(isNotEnabled())
    }
}
