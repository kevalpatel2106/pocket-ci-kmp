package com.kevalpatel2106.pocketci.selector.di

import com.kevalpatel2106.pocketci.selector.Platform
import com.kevalpatel2106.pocketci.selector.WasmPlatform
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { WasmPlatform() }.bind(Platform::class)
}
