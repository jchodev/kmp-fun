package org.jerry.kmp.compose.postcastlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun PodcastListItemLoadingView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(MaterialTheme.shapes.small)
                .background(Color.LightGray)
                .shimmer()
        )
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "",
            maxLines = 1,
            style = MaterialTheme.typography.titleSmall,
        )
        Text(
            text = "",
            maxLines = 1,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

//@DevicePreviews
//@Composable
//private fun PodcastListItemViewPreview() {
//    AssessmentprojectTheme {
//        PodcastListItemLoadingView (
//        )
//    }
//}
