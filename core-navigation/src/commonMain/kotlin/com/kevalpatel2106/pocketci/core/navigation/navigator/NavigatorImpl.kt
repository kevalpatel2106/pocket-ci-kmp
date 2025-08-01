package com.kevalpatel2106.pocketci.core.navigation.navigator

import androidx.navigation.NavOptionsBuilder
import com.kevalpatel2106.pocketci.core.navigation.routes.Destinations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.withContext

internal class NavigatorImpl : Navigator {

    private val _navigationEvents = Channel<NavigationEvents>()
    override val navigationEvents: Flow<NavigationEvents> = _navigationEvents.receiveAsFlow()

    override suspend fun navigate(
        destination: Destinations,
        navOptionsBuilder: NavOptionsBuilder.() -> Unit,
    ) = withContext(Dispatchers.Main.immediate) {   // Make sure event not lost when in background
            _navigationEvents.send(
                NavigationEvents.NavigateTo(
                    destination = destination,
                    navOptionsBuilder = navOptionsBuilder,
                )
            )
        }
}