package com.kevalpatel2106.pocketci.selector.di

import com.kevalpatel2106.pocketci.selector.Greeting
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val selectorModule = module {
    singleOf(::Greeting)
}

expect val platformModule : Module