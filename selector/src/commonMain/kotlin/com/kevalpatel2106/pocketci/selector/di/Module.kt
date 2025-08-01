package com.kevalpatel2106.pocketci.selector.di

import androidx.lifecycle.SavedStateHandle
import com.kevalpatel2106.pocketci.core.navigation.NavigationGraphBuilder
import com.kevalpatel2106.pocketci.selector.navigation.SelectorNavigationGraphBuilder
import com.kevalpatel2106.pocketci.selector.register.RegisterViewModel
import com.kevalpatel2106.pocketci.selector.selector.SelectorRepository
import com.kevalpatel2106.pocketci.selector.selector.SelectorViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

private const val MODULE_QUALIFIER = "Selector"

internal val internalModule = module {
    factory(qualifier = named(MODULE_QUALIFIER)) {
        SelectorNavigationGraphBuilder()
    }.bind(NavigationGraphBuilder::class)
    viewModelOf(::SelectorViewModel)
    viewModel { (savedStateHandle: SavedStateHandle) ->
        RegisterViewModel(savedStateHandle = savedStateHandle)
    }
    factory { SelectorRepository(getAll()) }
}

val selectorModule: List<Module>
    get() = listOf(internalModule)
