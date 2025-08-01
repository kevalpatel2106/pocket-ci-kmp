package com.kevalpatel2106.pocketci.selector.selector

import com.kevalpatel2106.pocketci.connector.public.CIConnector
import com.kevalpatel2106.pocketci.entity.CIInfo

internal class SelectorRepository(
    private val ciConnectors : List<CIConnector>,
) {

    suspend fun getSupportedCIInfo() : List<CIInfo> {
        return ciConnectors.map { it.getCIInfo() }
            .sortedBy { it.type.id }
    }
}