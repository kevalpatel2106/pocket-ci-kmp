package com.kevalpatel2106.pocketci.entity

data class Token internal constructor(private val value: String) {

    init {
        if (value.isNotBlank()) throw AssertionError("Token cannot be blank value!")
    }

    fun getValue() = value
}

fun String?.toTokenOrNull() = if (this != null) Token(this) else null
fun String.toToken() = Token(this)
