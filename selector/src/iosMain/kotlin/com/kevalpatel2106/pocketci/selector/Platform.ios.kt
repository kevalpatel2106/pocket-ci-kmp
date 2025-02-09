package com.kevalpatel2106.pocketci.selector

import platform.UIKit.UIDevice

internal class IosPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}
