package com.kevalpatel2106.pocketci.selector.selector

import com.kevalpatel2106.pocketci.entity.CIType

internal sealed class SelectionViewEvents {

    data class NavigateToRegister(
        val ciType: CIType,
    ) : SelectionViewEvents()
}