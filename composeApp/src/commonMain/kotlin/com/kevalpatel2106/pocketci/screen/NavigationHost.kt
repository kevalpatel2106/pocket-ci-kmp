package com.kevalpatel2106.pocketci.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kevalpatel2106.pocketci.core.navigation.NavigationGraphBuilder
import com.kevalpatel2106.pocketci.core.navigation.routes.Routes
import org.koin.compose.getKoin

@Composable
fun NavigationHost() {
    val navGraphBuilders = getKoin().getAll<NavigationGraphBuilder>()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Selector.route) {
        navGraphBuilders.forEach { it.addRoutes(this@NavHost) }
    }
}