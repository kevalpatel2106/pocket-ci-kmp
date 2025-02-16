package com.kevalpatel2106.pocketci.entity

data class Url(val value: String) {
    // TODO: Add validation for URL

    companion object {
        val EMPTY = Url("")
    }
}

fun String.toUrl() = Url(this)

fun String?.toUrlOrNull() = if (isNullOrBlank()) {
    null
} else {
    Url(this)
}
