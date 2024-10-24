package org.jerry.kmp.compose

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import org.jerry.kmp.compose.common.AppTopBar
import kotlin.test.Test

class AppTopBarTest {

    //ref: https://github.com/philipplackner/CMP-Testing/blob/master/composeApp/src/commonTest/kotlin/com/plcoding/cmp_testing/SampleUiTest.kt
    @OptIn(ExperimentalTestApi::class, ExperimentalMaterial3Api::class)
    @Test
    fun testTitleIsAssigned() = runComposeUiTest {
        setContent {
            AppTopBar (
                title = "this is title"
            )
        }

        onNodeWithText("this is title").assertExists()
    }
}