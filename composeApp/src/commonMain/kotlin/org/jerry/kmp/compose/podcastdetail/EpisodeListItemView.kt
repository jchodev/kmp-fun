package org.jerry.kmp.compose.podcastdetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import kmp_fun.composeapp.generated.resources.Res
import kmp_fun.composeapp.generated.resources.duration
import kmp_fun.composeapp.generated.resources.release_on
import org.jerry.kmp.compose.common.ShimmerEffect
import org.jerry.kmp.data.Episode
import org.jerry.kmp.data.Podcast
import org.jerry.kmp.ext.toDateString
import org.jerry.kmp.ext.toDuration
import org.jetbrains.compose.resources.stringResource

@Composable
fun EpisodeListItemView(
    podcast: Podcast,
    episode: Episode,
    onClick: () -> Unit = {},
) {
    ListItem(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
        ,
        leadingContent = {
            SubcomposeAsyncImage(
                model = podcast.imageUrl,
                modifier = Modifier
                    .width(56.dp)
                    .aspectRatio(1f)
                    .clip(MaterialTheme.shapes.small),
                contentDescription = podcast.title,
                contentScale = ContentScale.Crop,
                loading = {
                    ShimmerEffect()
                },
                error = {
                    ShimmerEffect()
                }
            )
        },
        headlineContent = {
            Column {
                Text(
                    text = episode.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(Res.string.release_on, episode.published.toDateString()),
                        style = MaterialTheme.typography.labelSmall,
                    )
                }

                Text(
                    text = stringResource(Res.string.duration, episode.duration.toDuration()),
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        },
        trailingContent = {
            IconButton(onClick = onClick ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}
//
//@DevicePreviews
//@Composable
//private fun EpisodeListItemViewPreview(){
//    AssessmentprojectTheme {
//        EpisodeListItemView(
//            episode = Episode(
//                title = "this is episode title",
//                description = "this is episode description"
//            ),
//            podcast = Podcast(
//                title = "this is podcast title",
//                description = "this is podcast description"
//            )
//        )
//    }
//}
