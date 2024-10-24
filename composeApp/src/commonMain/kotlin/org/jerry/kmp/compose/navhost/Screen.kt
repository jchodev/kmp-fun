package org.jerry.kmp.compose.navhost

sealed class Screen(val route: String) {
    data object PodcastList: Screen(route = "PodcastList")
}