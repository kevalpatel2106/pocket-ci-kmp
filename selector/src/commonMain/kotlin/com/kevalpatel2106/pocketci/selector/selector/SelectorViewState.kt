package com.kevalpatel2106.pocketci.selector.selector

import com.kevalpatel2106.pocketci.entity.CIType
import kotlinx.collections.immutable.ImmutableList

internal sealed interface SelectorViewState {

    data object Loading : SelectorViewState

    data class Error(
        val message: String,
    ) : SelectorViewState

    data class Success(
        val items: ImmutableList<SelectorItem>,
    ) : SelectorViewState
}

data class SelectorItem(
    val name: String,
    val isSelected: Boolean = false,
    val ciType: CIType,
)