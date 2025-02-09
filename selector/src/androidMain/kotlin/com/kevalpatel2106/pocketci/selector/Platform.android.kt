package com.kevalpatel2106.pocketci.selector

import android.os.Build

internal class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}
