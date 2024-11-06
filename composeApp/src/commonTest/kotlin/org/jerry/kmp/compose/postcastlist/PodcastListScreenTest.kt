package org.jerry.kmp.compose.postcastlist

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import org.jerry.kmp.MockStubs
import org.jerry.kmp.compose.podcastlist.PodcastListScreen
import org.jerry.kmp.contants.TEST_TAG_ERROR_MESSAGE
import org.jerry.kmp.contants.TEST_TAG_LOADING
import org.jerry.kmp.viewmodel.podcastlist.PodcastListState
import kotlin.test.Test

class PodcastListScreenTest {

    @OptIn(ExperimentalTestApi::class, ExperimentalMaterial3Api::class)
    @Test
    fun testFavouritesErrorDisplayOnUI() = runComposeUiTest {
        setContent {
            PodcastListScreen (
                podcastListState = PodcastListState(
                    errorMessage = MockStubs.mockExceptionStr
                ),
                onPodcastClick = {},
                onFavouriteClick = {},
                onRefresh = {},
                onErrorDismissRequest = {}
            )
        }

        onNodeWithTag(TEST_TAG_ERROR_MESSAGE).assertExists()
    }

    @OptIn(ExperimentalTestApi::class, ExperimentalMaterial3Api::class)
    @Test
    fun testFavouriteListLoadingStatus() = runComposeUiTest {
        setContent {
            PodcastListScreen (
                podcastListState = PodcastListState(
                    isLoading = true
                ),
                onPodcastClick = {},
                onFavouriteClick = {},
                onRefresh = {},
                onErrorDismissRequest = {}
            )
        }

        onNodeWithTag(TEST_TAG_LOADING).assertExists()
    }
}