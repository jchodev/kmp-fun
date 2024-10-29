package org.jerry.kmp

import androidx.compose.ui.window.ComposeUIViewController
import org.jerry.kmp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}