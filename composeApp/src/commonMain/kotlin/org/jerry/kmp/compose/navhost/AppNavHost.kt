package org.jerry.kmp.compose.navhost

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jerry.kmp.compose.podcastdetail.PodcastDetailScreen
import org.jerry.kmp.compose.postcastlist.PodcastListScreen
import org.jerry.kmp.data.Podcast
import org.jerry.kmp.viewmodel.podcastlist.PodcastListViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun AppNavHost(
    podcastListViewModel: PodcastListViewModel = koinViewModel(),
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.PodcastList.route
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ){ paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = startDestination
        ) {
            composable(route = Screen.PodcastList.route) {
                PodcastListScreen(
                    viewModel = podcastListViewModel,
                    onPodcastClick = {
                        navController.navigate(it)
                    }
                )
            }
            composable<Podcast> { backStackEntry ->
                val podcast: Podcast = backStackEntry.toRoute()

                PodcastDetailScreen(
                    selectedPodcast = podcast,
                    onBackClick = { navController.navigateUp() },
                    onEpisodeClick = {
                        //navController.navigate(it)
                    }
                )
            }
        }
    }
}