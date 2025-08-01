package com.kevalpatel2106.pocketci.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface NavigationGraphBuilder {

    fun addRoutes(navController: NavController, graphBuilder: NavGraphBuilder)
}