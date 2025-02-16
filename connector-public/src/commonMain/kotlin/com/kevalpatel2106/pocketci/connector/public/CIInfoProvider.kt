package com.kevalpatel2106.pocketci.connector.public

import com.kevalpatel2106.pocketci.entity.CIInfo

interface CIInfoProvider {

    suspend fun getCIInfo(): CIInfo
}
