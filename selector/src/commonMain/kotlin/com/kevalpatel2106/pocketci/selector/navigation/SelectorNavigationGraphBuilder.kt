package com.kevalpatel2106.pocketci.selector.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kevalpatel2106.pocketci.core.navigation.NavigationGraphBuilder
import com.kevalpatel2106.pocketci.core.navigation.routes.Routes
import com.kevalpatel2106.pocketci.selector.screen.CISelectionScreen

internal class SelectorNavigationGraphBuilder : NavigationGraphBuilder {

    override fun addRoutes(navGraphBuilder: NavGraphBuilder) = with(navGraphBuilder) {
        composable(route = Routes.Selector.route) {
            CISelectionScreen()
        }
    }
}