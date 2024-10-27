package org.jerry.kmp.compose

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import org.jerry.kmp.compose.navhost.AppNavHost

@Composable
fun AppContent() {
    val navController = rememberNavController()

    Scaffold() {
        AppNavHost(
            //modifier = Modifier.padding(it),
            navController = navController,
//            episodePlayerViewModel = episodePlayerViewModel,
//            onPlayVideoClick = onPlayVideoClick
        )
    }
}