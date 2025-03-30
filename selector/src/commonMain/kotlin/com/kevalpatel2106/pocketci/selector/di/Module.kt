package com.kevalpatel2106.pocketci.selector.di

import com.kevalpatel2106.pocketci.core.navigation.NavigationGraphBuilder
import com.kevalpatel2106.pocketci.selector.navigation.SelectorNavigationGraphBuilder
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

private const val MODULE_QUALIFIER = "Selector"

internal val internalModule = module {
    factory(qualifier = named(MODULE_QUALIFIER)) {
        SelectorNavigationGraphBuilder()
    }.bind(NavigationGraphBuilder::class)
}

val selectorModule: List<Module>
    get() = listOf(internalModule)
