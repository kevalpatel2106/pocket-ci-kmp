package com.kevalpatel2106.pocketci

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.kevalpatel2106.pocketci.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "PocketCI",
        ) {
            App()
        }
    }
}