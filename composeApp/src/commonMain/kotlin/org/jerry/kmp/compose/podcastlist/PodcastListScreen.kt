package org.jerry.kmp.compose.podcastlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kmp_fun.composeapp.generated.resources.Res
import kmp_fun.composeapp.generated.resources.podcast_list_title
import kmp_fun.composeapp.generated.resources.refresh
import org.jerry.kmp.compose.common.AppTopBar
import org.jerry.kmp.compose.common.ErrorDialog
import org.jerry.kmp.compose.common.ErrorText
import org.jerry.kmp.data.Podcast
import org.jerry.kmp.data.database.entity.Favourite
import org.jerry.kmp.viewmodel.podcastlist.PodcastListState
import org.jerry.kmp.viewmodel.podcastlist.PodcastListViewModel
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PodcastListScreen(
    viewModel: PodcastListViewModel = koinViewModel(),
    onPodcastClick: (Podcast) -> Unit = {},
) {
    val state = viewModel.podcastListState.collectAsStateWithLifecycle().value
    PodcastListScreen(
        podcastListState = state,
        onFavouriteClick = {
            viewModel.toggleFavourite(it)
        },
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
    onFavouriteClick: (Long) -> Unit,
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
                            contentDescription = stringResource(Res.string.refresh)
                        )
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)){
            when {
                podcastListState.isLoading -> {
                    PodcastListLoadingScreen()
                }
                podcastListState.errorMessage != null -> {
                    ErrorDialog(
                        text = podcastListState.errorMessage,
                        onRetryRequest = onRefresh,
                        onDismissRequest = onErrorDismissRequest,
                    )
                }
                else -> {
                    PodcastListScreenContent(
                        podcasts = podcastListState.podcasts,
                        favourites = podcastListState.favourites,
                        favouriteError = podcastListState.favouriteError,
                        onPodcastClick = onPodcastClick,
                        onFavouriteClick = onFavouriteClick,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PodcastListScreenContent(
    podcasts: List<Podcast> = emptyList(),
    favourites: List<Favourite> = emptyList(),
    favouriteError: String? = null,
    onPodcastClick: (Podcast) -> Unit = {},
    onFavouriteClick: (Long) -> Unit = {},
) {
    Column (modifier = Modifier.fillMaxSize() ){
        if (favouriteError != null) {
            ErrorText(
                modifier = Modifier.padding(vertical = 8.dp),
                text  = favouriteError
            )
        }
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
                val isFavourite = favourites.any { it.podcastId == podcast.id }
                PodcastListItemView(
                    podcast = podcast,
                    isFavourite = isFavourite,
                    onFavouriteClick = { onFavouriteClick(podcast.id) },
                    onClick = { onPodcastClick(podcast) },
                    visibleFavourite = favouriteError == null
                )
            }
        }
    }

}

@Preview
@Composable
fun PodcastListScreenContentPreview(){
    PodcastListScreenContent(
        favouriteError = "this is error messaage",
        podcasts = listOf(
            Podcast(),
            Podcast(),
            Podcast(),
            Podcast(),
        )
    )
}

@Preview
@Composable
fun PodcastListScreenContentPreview2(){
    Text("this is preview test")
}