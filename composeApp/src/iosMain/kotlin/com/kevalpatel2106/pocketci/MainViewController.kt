package com.kevalpatel2106.pocketci

import androidx.compose.ui.window.ComposeUIViewController
import com.kevalpatel2106.pocketci.di.initKoin

@Suppress("FunctionNaming")
fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    },
) {
    App()
}
