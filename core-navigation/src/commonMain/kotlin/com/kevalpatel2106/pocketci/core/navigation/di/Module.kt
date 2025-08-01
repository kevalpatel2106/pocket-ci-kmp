package com.kevalpatel2106.pocketci.core.navigation.di

import com.kevalpatel2106.pocketci.core.navigation.navigator.Navigator
import com.kevalpatel2106.pocketci.core.navigation.navigator.NavigatorImpl
import org.koin.dsl.module

val navigationModule = module {
    single<Navigator> { NavigatorImpl() }
}
