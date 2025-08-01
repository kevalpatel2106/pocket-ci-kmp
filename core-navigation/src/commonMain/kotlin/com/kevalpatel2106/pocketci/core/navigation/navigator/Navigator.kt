package com.kevalpatel2106.pocketci.core.navigation.navigator

import androidx.navigation.NavOptionsBuilder
import com.kevalpatel2106.pocketci.core.navigation.routes.Destinations
import kotlinx.coroutines.flow.Flow

interface Navigator {

    val navigationEvents: Flow<NavigationEvents>

    suspend fun navigate(
        destination : Destinations,
        navOptionsBuilder: NavOptionsBuilder.() -> Unit = {},
    )
}