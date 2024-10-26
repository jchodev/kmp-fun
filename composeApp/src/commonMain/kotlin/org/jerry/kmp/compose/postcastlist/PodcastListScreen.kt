package org.jerry.kmp.compose.postcastlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kmp_fun.composeapp.generated.resources.Res
import kmp_fun.composeapp.generated.resources.podcast_list_title
import org.jerry.kmp.compose.common.AppTopBar
import org.jerry.kmp.data.Podcast
import org.jerry.kmp.viewmodel.PodcastListState
import org.jerry.kmp.viewmodel.PodcastListViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PodcastListScreen(
    viewModel: PodcastListViewModel = koinViewModel(),
    onPodcastClick: (Podcast) -> Unit = {},
) {
    val state = viewModel.podcastListState.collectAsStateWithLifecycle().value
    PodcastListScreen(
        podcastListState = state,
        onPodcastClick = {
            onPodcastClick.invoke(it)
        },
        onRefresh = {
            viewModel.getData()
        },
        onErrorDismissRequest = {
            viewModel.clearError()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PodcastListScreen(
    podcastListState: PodcastListState,
    onPodcastClick: (Podcast) -> Unit,
    onRefresh: () -> Unit,
    onErrorDismissRequest: () -> Unit,
) {
    Scaffold (
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            AppTopBar (
                modifier = Modifier
                    .shadow(elevation = 4.dp),
                title = stringResource(Res.string.podcast_list_title),
                showBack = false,
                actions = {
                    IconButton(onClick = onRefresh) {
                        Icon(Icons.Filled.Refresh,
                            contentDescription = "", //stringResource(id = R.string.refresh)
                        )
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)){

            PodcastListScreenContent(
                podcasts = podcastListState.data,
                onPodcastClick = onPodcastClick
            )

            if (podcastListState.isLoading) {
                //LoadingCompose()
            }

            podcastListState.errorMessage?.let {
//                ErrorDialog(
//                    text = it,
//                    onRetryRequest = onRefresh,
//                    onDismissRequest = onErrorDismissRequest,
//                )
            }
        }
    }
}

@Composable
private fun PodcastListScreenContent(
    podcasts: List<Podcast> = emptyList(),
    onPodcastClick: (Podcast) -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            horizontal = 12.dp,
            vertical = 24.dp
        )
    ) {
        items(
            items = podcasts,
            key = { it.id }
        ) { podcast ->
            PodcastListItemView(
                podcast = podcast,
                onClick = {
                    onPodcastClick.invoke(podcast)
                }
            )
        }
    }
}

