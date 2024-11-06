package org.jerry.kmp.compose.podcastlist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.jerry.kmp.contants.TEST_TAG_LOADING

@Composable
fun PodcastListLoadingScreen() {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth().testTag(TEST_TAG_LOADING),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            horizontal = 12.dp,
            vertical = 24.dp
        )
    ) {
        for (i in 1..6) {
            item {
                PodcastListItemLoadingView()
            }
        }

    }
}

//@DevicePreviews
//@Composable
//private fun PodcastListItemViewPreview() {
//    AssessmentprojectTheme {
//        PodcastListLoadingScreen ()
//    }
//}
