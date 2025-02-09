package com.kevalpatel2106.pocketci

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform