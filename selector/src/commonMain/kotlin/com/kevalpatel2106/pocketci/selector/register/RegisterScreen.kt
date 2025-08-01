package com.kevalpatel2106.pocketci.selector.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun RegisterScreen(viewState: Int) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .safeDrawingPadding(),
    ) {
        Text(
            text = "Register!",
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(1)
}
