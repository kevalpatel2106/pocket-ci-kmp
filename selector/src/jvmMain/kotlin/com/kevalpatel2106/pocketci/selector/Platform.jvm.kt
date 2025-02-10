package com.kevalpatel2106.pocketci.selector

internal class JvmPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}
