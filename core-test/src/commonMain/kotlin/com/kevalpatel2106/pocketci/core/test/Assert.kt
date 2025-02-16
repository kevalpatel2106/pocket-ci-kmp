package com.kevalpatel2106.pocketci.core.test

inline fun <reified T : Throwable> assertThrows(block: () -> Unit) {
    try {
        block()
    } catch (e: Throwable) {
        if (e is T) throw AssertionError("Exception of type ${T::class.simpleName} is thrown.")
        else throw e
    }
}
