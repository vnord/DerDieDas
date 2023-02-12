package com.github.vnord.derdiedas.feature_nouns.presentation.add_noun

import android.content.Context
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
import androidx.test.core.app.ApplicationProvider
import com.github.vnord.derdiedas.R
import com.github.vnord.derdiedas.core.util.TestTags
import com.github.vnord.derdiedas.di.AppModule
import com.github.vnord.derdiedas.feature_nouns.presentation.MainActivity
import com.github.vnord.derdiedas.feature_nouns.presentation.Screen
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
                    startDestination = Screen.AddNounScreen.route
                ) {
                    composable(route = Screen.AddNounScreen.route) {
                        AddNounScreen(navController = navController)
                    }
                }
            }
        }
    }

    val getString: (Int) -> String =
        { ApplicationProvider.getApplicationContext<Context>().resources.getString(it) }

    @Test
    fun addANoun() {
        // The gender selection buttons should exist
        composeRule.onNodeWithTag(TestTags.GENDER_BUTTONS).assertExists()
        // There should be 3 radio buttons, all unselected
        composeRule.onAllNodes(isSelectable()).assertCountEquals(3).assertAll(isNotSelected())
        // Click one of them
        composeRule.onAllNodes(isSelectable()).onFirst().performClick()
        composeRule.onAllNodes(isSelected()).assertCountEquals(1)
        composeRule.onNode(hasSetTextAction()).performTextInput("Noun")
        composeRule.onNode(hasContentDescription(getString(R.string.save_noun))).assert(isEnabled())
    }

    @Test
    fun saveButtonDisabledWhenContentIsMissing() {
        // Save button should be disabled initially
        composeRule
            .onNode(hasContentDescription(getString(R.string.save_noun)))
            .assert(isNotEnabled())
        // Should still be disabled after entering text because no gender is selected
        composeRule
            .onNode(hasSetTextAction())
            .performTextInput("Noun")
        composeRule
            .onNode(hasContentDescription(getString(R.string.save_noun)))
            .assert(isNotEnabled())
        // Delete the text and make sure that save button is disabled when the text is empty but
        // a gender is selected
        composeRule
            .onNode(hasSetTextAction())
            .performTextClearance()
        composeRule
            .onAllNodes(isSelectable())
            .onFirst()
            .performClick()
        composeRule
            .onNode(hasContentDescription(getString(R.string.save_noun)))
            .assert(isNotEnabled())
    }
}
