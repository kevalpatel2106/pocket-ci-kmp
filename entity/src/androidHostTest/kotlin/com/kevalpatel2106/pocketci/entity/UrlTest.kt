package com.kevalpatel2106.pocketci.entity

import kotlin.test.Test
import kotlin.test.assertEquals

internal class UrlTest {

    @Test
    fun `check empty url value`() {
        assertEquals(Url(""), Url.EMPTY)
    }

    @Test
    fun `given url string check convert to url`() {
        assertEquals(Url("http://example.com"), "http://example.com".toUrl())
    }
}
