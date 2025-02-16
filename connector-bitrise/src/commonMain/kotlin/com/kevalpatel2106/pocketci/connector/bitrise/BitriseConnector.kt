package com.kevalpatel2106.pocketci.connector.bitrise

import com.kevalpatel2106.pocketci.connector.public.CIConnector
import com.kevalpatel2106.pocketci.entity.CIInfo
import com.kevalpatel2106.pocketci.entity.CIType
import com.kevalpatel2106.pocketci.entity.Url
import com.kevalpatel2106.pocketci.entity.toToken

internal class BitriseConnector : CIConnector {

    override suspend fun getCIInfo(): CIInfo {
        return CIInfo(
            type = CIType.BITRISE,
            infoUrl = Url("https://www.bitrise.io/"),
            defaultBaseUrl = Url("https://api.bitrise.io/v0.1/"),
            authTokenExplainLink = Url("https://devcenter.bitrise.io/api-authentication/"),
            sampleAuthToken = "sample-token".toToken(),
        )
    }
}
