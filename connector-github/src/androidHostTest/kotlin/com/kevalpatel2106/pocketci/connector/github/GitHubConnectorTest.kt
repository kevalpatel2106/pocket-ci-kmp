package com.kevalpatel2106.pocketci.connector.github

import com.kevalpatel2106.pocketci.entity.CIInfo
import com.kevalpatel2106.pocketci.entity.CIType
import com.kevalpatel2106.pocketci.entity.Url
import com.kevalpatel2106.pocketci.entity.toToken
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GitHubConnectorTest {

    @Test
    fun `verify ci info`() = runTest {
        val bitriseConnector = GitHubConnector().getCIInfo()

        val expected = CIInfo(
            type = CIType.GITHUB,
            infoUrl = Url("https://www.github.com/"),
            defaultBaseUrl = Url("https://api.github.com/v0.1/"),
            authTokenExplainLink = Url("https://devcenter.github.com/api-authentication/"),
            sampleAuthToken = "sample-token".toToken(),
        )

        assertEquals(expected, bitriseConnector)
    }
}
