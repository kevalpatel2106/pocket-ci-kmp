package com.kevalpatel2106.pocketci.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kevalpatel2106.pocketci.core.navigation.NavigationGraphBuilder
import com.kevalpatel2106.pocketci.core.navigation.navigator.NavigationEvents.NavigateTo
import com.kevalpatel2106.pocketci.core.navigation.navigator.Navigator
import com.kevalpatel2106.pocketci.core.navigation.routes.Destinations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.compose.getKoin
import org.koin.compose.koinInject

@Composable
fun NavigationHost() {
    val navGraphBuilders = getKoin().getAll<NavigationGraphBuilder>()
    val navController = rememberNavController()
    NavigationHandler(navController)
    NavHost(navController = navController, startDestination = Destinations.Register) {
        navGraphBuilders.forEach { it.addRoutes(navController, this@NavHost) }
    }
}

@Composable
private fun NavigationHandler(navController: NavHostController) {
    val navigator = koinInject<Navigator>()
    val lifeCycleOwner = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(lifeCycleOwner) {
        lifeCycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            navigator.navigationEvents.collect { event ->
                when (event) {
                    is NavigateTo -> navController.navigate(
                        event.destination,
                        builder = event.navOptionsBuilder,
                    )
                }
            }
        }
    }
}
