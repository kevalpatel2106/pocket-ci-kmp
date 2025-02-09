package com.kevalpatel2106.pocketci

import android.app.Application
import com.kevalpatel2106.pocketci.di.initKoin
import org.koin.android.ext.koin.androidContext

class PocketCIApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@PocketCIApplication)
        }
    }
}
