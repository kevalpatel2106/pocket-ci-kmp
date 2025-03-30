package com.kevalpatel2106.pocketci.core.navigation.routes

sealed class Routes(val route: String) {
    data object Selector : Routes("selector")
}