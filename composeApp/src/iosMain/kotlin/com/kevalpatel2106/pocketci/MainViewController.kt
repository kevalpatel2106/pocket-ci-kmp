package com.kevalpatel2106.pocketci

import androidx.compose.ui.window.ComposeUIViewController
import com.kevalpatel2106.pocketci.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}