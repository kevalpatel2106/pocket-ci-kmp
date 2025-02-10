package com.kevalpatel2106.pocketci.selector

class Greeting(private val platform: Platform) {

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}
