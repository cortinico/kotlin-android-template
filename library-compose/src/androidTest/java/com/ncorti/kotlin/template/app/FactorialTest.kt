package com.ncorti.kotlin.template.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.ncorti.kotlin.template.app.ui.components.Factorial
import org.junit.Rule
import org.junit.Test

class FactorialTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun useAppContext() {
        composeTestRule.setContent {
            Factorial()
        }

        composeTestRule.onNodeWithTag("Input").performClick().performTextInput("5")
        composeTestRule.onNodeWithText("COMPUTE").performClick()
        composeTestRule.onNodeWithTag("FactorialResult")
            .assertIsDisplayed()
            .assert(hasText("120"))
    }
}
