package com.kevalpatel2106.pocketci.selector.selector

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kevalpatel2106.pocketci.entity.CIType
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun SelectionScreen(
    viewState: SelectorViewState,
    onSelected: (CIType) -> Unit,
    onRetry: () -> Unit = {},
) {
    Box(modifier = Modifier.padding(horizontal = 16.dp).fillMaxSize()) {
        when (viewState) {
            is SelectorViewState.Loading -> LoadingScreen()
            is SelectorViewState.Error -> ErrorScreen(viewState.message, onRetry)
            is SelectorViewState.Success -> SuccessScreen(viewState.items, onSelected)
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize().safeContentPadding()) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(96.dp)
                .height(96.dp)
                .align(Alignment.Center),
            color = MaterialTheme.colors.primary,
        )
    }
}

@Composable
fun ErrorScreen(message: String, onRetry: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxWidth().safeContentPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Error: $message"
        )
        Button(
            onClick = onRetry,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun SuccessScreen(items: List<SelectorItem>, onSelected: (CIType) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = WindowInsets.safeDrawing.asPaddingValues()
    ) {
        items(items = items) {
            Column(modifier = Modifier.clickable { onSelected(it.ciType) }) {
                Text(
                    text = it.name,
                    modifier = Modifier.background(color = if (it.isSelected) Color.Red else Color.Green)
                )
            }
        }
    }
}

@Preview
@Composable
fun SelectionScreenPreview() {
    SelectionScreen(viewState = SelectorViewState.Loading, {}, {})
}
