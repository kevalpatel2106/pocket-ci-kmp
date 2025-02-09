package com.kevalpatel2106.pocketci

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "PocketCI",
    ) {
        App()
    }
}