package com.github.vnord.derdiedas.presentation.e2e

import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isSelectable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.Espresso
import com.github.vnord.derdiedas.R
import com.github.vnord.derdiedas.di.AppModule
import com.github.vnord.derdiedas.domain.model.Categories
import com.github.vnord.derdiedas.presentation.MainActivity
import com.github.vnord.derdiedas.presentation.SetupNavGraph
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@HiltAndroidTest
@UninstallModules(AppModule::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class NounsE2E {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent { MaterialTheme { SetupNavGraph() } }
    }

    @Test
    fun addNoun(): Unit = runBlocking {
        with(composeRule) {
            onNodeWithContentDescription(activity.getString(R.string.categories)).performClick()
            onNodeWithText(Categories.MyNouns.categoryName).performClick()
            onNodeWithContentDescription(activity.getString(R.string.add_noun)).performClick()
            onAllNodes(isSelectable()).onFirst().performClick()
            onNode(hasSetTextAction()).performTextInput("Mann")
            onNodeWithContentDescription(activity.getString(R.string.save_noun)).performClick()

            delay(500)

            onNode(hasText("Der", ignoreCase = true)).assertIsDisplayed()
            onNode(hasText("Mann", ignoreCase = true)).assertIsDisplayed()
        }
    }

    @Test
    fun playQuizWithASingleNoun(): Unit = with(composeRule) {
        onNodeWithContentDescription(activity.getString(R.string.categories)).performClick()
        onNodeWithText(Categories.MyNouns.categoryName).performClick()
        onNodeWithContentDescription(activity.getString(R.string.add_noun)).performClick()
        onAllNodes(isSelectable()).onFirst().performClick()
        onNode(hasSetTextAction()).performTextInput("Mann")
        onNodeWithContentDescription(activity.getString(R.string.save_noun)).performClick()

        Espresso.pressBack()
        Espresso.pressBack()

        onNodeWithContentDescription(activity.getString(R.string.start_quiz)).performClick()

        onNode(hasText("Die", ignoreCase = true)).performClick().assertIsNotEnabled()
        onNode(hasText("Das", ignoreCase = true)).performClick().assertIsNotEnabled()
        onNode(hasText("Der", ignoreCase = true)).performClick().assertIsEnabled()
        onNodeWithContentDescription(activity.getString(R.string.next_noun)).performClick()
        hasText(activity.getString(R.string.done_text), ignoreCase = true)
    }

    @Test
    fun playQuizWithNoNouns(): Unit = with(composeRule) {
        onNodeWithContentDescription(activity.getString(R.string.start_quiz)).performClick()
        hasText(activity.getString(R.string.done_text), ignoreCase = true)
    }
}
