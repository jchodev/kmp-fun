package org.jerry.kmp.compose.postcastlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import org.jerry.kmp.compose.common.CoilLoadingCompose
import org.jerry.kmp.compose.common.ShimmerEffect
import org.jerry.kmp.data.Podcast

@Composable
fun PodcastListItemView(
    podcast: Podcast,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
            }
        ,
    ) {
        SubcomposeAsyncImage(
            model = podcast.imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(MaterialTheme.shapes.small)
            ,
            contentDescription = podcast.title,
            contentScale = ContentScale.Crop,
            loading = {
                ShimmerEffect()
            },
            error = {
                ShimmerEffect()
            }
        )
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = podcast.title,
            maxLines = 1,
            style = MaterialTheme.typography.titleSmall,
        )
        Text(
            text = podcast.author,
            maxLines = 1,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

//
//@Preview
//@Composable
//private fun PodcastListItemViewPreview() {
//
//        PodcastListItemView (
//            podcast = Podcast(
//                id = 0,
//                author = "auth 1",
//                description = "description 1",
//                imageUrl = "image 1",
//                title = "title 1",
//                link = "link 1",
//            ),
//            onClick = {}
//        )
//
//}