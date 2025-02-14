package com.kevalpatel2106.pocketci.selector.di

import com.kevalpatel2106.pocketci.selector.Greeting
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val internalModule = module {
    singleOf(::Greeting)
}

internal expect val platformModule: Module

val selectorModule: List<Module>
    get() = listOf(
        internalModule,
        platformModule,
    )
