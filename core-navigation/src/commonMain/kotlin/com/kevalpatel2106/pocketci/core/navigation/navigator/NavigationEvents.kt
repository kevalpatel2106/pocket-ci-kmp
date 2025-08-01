package com.kevalpatel2106.pocketci.core.navigation.navigator

import androidx.navigation.NavOptionsBuilder
import com.kevalpatel2106.pocketci.core.navigation.routes.Destinations

sealed interface NavigationEvents {

    data class NavigateTo(
        val destination: Destinations,
        val navOptionsBuilder: NavOptionsBuilder.() -> Unit = {},
    ) : NavigationEvents
}