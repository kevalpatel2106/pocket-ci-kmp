package com.kevalpatel2106.pocketci.core.test

inline fun <reified T : Throwable> assertThrows(block: () -> Unit): Boolean {
    try {
        block()
        return false
    } catch (e: Throwable) {
        return e is T
    }
}
