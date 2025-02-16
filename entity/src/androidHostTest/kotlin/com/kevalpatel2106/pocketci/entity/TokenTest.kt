package com.kevalpatel2106.pocketci.entity

import com.kevalpatel2106.pocketci.core.test.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TokenTest {

    @Test
    fun `given token with empty string when initialised check assertion error thrown`() {
        assertThrows<AssertionError> { Token("") }
    }

    @Test
    fun `given token with blank string when initialised check assertion error thrown`() {
        assertThrows<AssertionError> { Token("  ") }
    }

    @Test
    fun `given value when converted to token then check token`() {
        listOf(
            Pair("token", Token("token")),
            Pair("1223", Token("1223")),
        ).forEach { (value, expectedToken) ->
            assertEquals(expectedToken, value.toToken())
        }
    }

    @Test
    fun `given nullable value when converted to token then check token`(
        value: String?,
        expectedToken: Token?,
    ) {
        listOf(
            Pair("token", Token("token")),
            Pair("123", Token("123")),
            Pair(null, null),
        ).forEach { (value, expectedToken) ->
            assertEquals(expectedToken, value.toTokenOrNull())
        }
    }
}
