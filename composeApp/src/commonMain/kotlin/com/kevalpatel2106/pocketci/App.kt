package com.kevalpatel2106.pocketci

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.kevalpatel2106.pocketci.screen.NavigationHost
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            NavigationHost()
        }
    }
}
