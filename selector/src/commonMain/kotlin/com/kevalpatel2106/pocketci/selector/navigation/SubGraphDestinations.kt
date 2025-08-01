package com.kevalpatel2106.pocketci.selector.navigation

import com.kevalpatel2106.pocketci.core.navigation.routes.Destinations
import com.kevalpatel2106.pocketci.entity.CIType
import kotlinx.serialization.Serializable

internal sealed interface SubGraphDestinations: Destinations {

    @Serializable
    data object Selector : SubGraphDestinations

    @Serializable
    data class Register(val ciType: CIType) : SubGraphDestinations
}