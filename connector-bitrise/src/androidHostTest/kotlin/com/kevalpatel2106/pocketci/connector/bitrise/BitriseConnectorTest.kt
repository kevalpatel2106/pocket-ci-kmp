package com.kevalpatel2106.pocketci.connector.bitrise

import com.kevalpatel2106.pocketci.entity.CIInfo
import com.kevalpatel2106.pocketci.entity.CIType
import com.kevalpatel2106.pocketci.entity.Url
import com.kevalpatel2106.pocketci.entity.toToken
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class BitriseConnectorTest {

    @Test
    fun `verify ci info`() = runTest {
        val bitriseConnector = BitriseConnector().getCIInfo()

        val expected = CIInfo(
            type = CIType.BITRISE,
            infoUrl = Url("https://www.bitrise.io/"),
            defaultBaseUrl = Url("https://api.bitrise.io/v0.1/"),
            authTokenExplainLink = Url("https://devcenter.bitrise.io/api-authentication/"),
            sampleAuthToken = "sample-token".toToken(),
        )

        assertEquals(expected, bitriseConnector)
    }
}
