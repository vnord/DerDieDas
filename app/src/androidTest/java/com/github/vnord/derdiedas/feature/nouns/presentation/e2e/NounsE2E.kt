package com.github.vnord.derdiedas.feature.nouns.presentation.e2e

import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isSelectable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.github.vnord.derdiedas.R
import com.github.vnord.derdiedas.di.AppModule
import com.github.vnord.derdiedas.feature.nouns.presentation.MainActivity
import com.github.vnord.derdiedas.feature.nouns.presentation.SetupNavGraph
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
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
    fun addNoun(): Unit = with(composeRule) {
        onNodeWithContentDescription(activity.getString(R.string.noun_list)).performClick()
        onNodeWithContentDescription(activity.getString(R.string.add_noun)).performClick()
        onAllNodes(isSelectable()).onFirst().performClick()
        onNode(hasSetTextAction()).performTextInput("Mann")
        onNodeWithContentDescription(activity.getString(R.string.save_noun)).performClick()
        onNode(hasText("Der", ignoreCase = true)).assertExists()
        onNode(hasText("Mann", ignoreCase = true)).assertExists()
    }
}
