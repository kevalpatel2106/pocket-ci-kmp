package com.kevalpatel2106.pocketci.selector.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kevalpatel2106.pocketci.core.navigation.NavigationGraphBuilder
import com.kevalpatel2106.pocketci.core.navigation.navTypes.CustomNavTypes
import com.kevalpatel2106.pocketci.core.navigation.routes.Destinations
import com.kevalpatel2106.pocketci.entity.CIType
import com.kevalpatel2106.pocketci.selector.navigation.SubGraphDestinations.Register
import com.kevalpatel2106.pocketci.selector.register.RegisterScreen
import com.kevalpatel2106.pocketci.selector.register.RegisterViewModel
import com.kevalpatel2106.pocketci.selector.selector.SelectionScreen
import com.kevalpatel2106.pocketci.selector.selector.SelectorViewModel
import org.koin.compose.viewmodel.koinViewModel
import kotlin.reflect.typeOf

internal class SelectorNavigationGraphBuilder : NavigationGraphBuilder {

    override fun addRoutes(
        navController: NavController,
        navGraphBuilder: NavGraphBuilder
    ) = with(navGraphBuilder) {
        navigation<Destinations.Register>(
            startDestination = SubGraphDestinations.Selector,
            typeMap = mapOf(typeOf<CIType>() to CustomNavTypes.CITypeNavType)
        ) {
            composable<SubGraphDestinations.Selector> {
                val viewModel = koinViewModel<SelectorViewModel>()
                val viewState by viewModel.viewState.collectAsState()
                SelectionScreen(
                    viewState = viewState,
                    onSelected = viewModel::onCiSelected,
                    onRetry = viewModel::onRetry
                )
            }
            composable<Register> {
                val viewModel = koinViewModel<RegisterViewModel>()
                val viewState by viewModel.viewState.collectAsState()
                RegisterScreen(viewState)
            }
        }
    }
}
