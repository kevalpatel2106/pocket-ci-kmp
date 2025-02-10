package com.kevalpatel2106.pocketci.di

import com.kevalpatel2106.pocketci.selector.di.platformModule
import com.kevalpatel2106.pocketci.selector.di.selectorModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            selectorModule,
            platformModule,
        )
    }
}
