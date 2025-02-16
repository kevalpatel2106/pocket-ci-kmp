package com.kevalpatel2106.pocketci.entity

data class CIInfo(
    val type: CIType,
    val infoUrl: Url,
    val defaultBaseUrl: Url,
    val authTokenExplainLink: Url,
    val sampleAuthToken: Token,
)
