package com.kevalpatel2106.pocketci.entity

import kotlin.test.Test
import kotlin.test.assertEquals

internal class CITypeTest {
    private val testCases = listOf(
        // Format: ci type, expected id
        Pair(CIType.BITRISE, 1),
        Pair(CIType.GITHUB, 2),
    )

    @Test
    fun `given ci type check id`() {
        testCases.forEach { (ciType, expectedId) ->
            assertEquals(expectedId, ciType.id)
        }
    }
}
