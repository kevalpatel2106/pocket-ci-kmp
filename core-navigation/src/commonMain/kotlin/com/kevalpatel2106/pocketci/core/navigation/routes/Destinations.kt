package com.kevalpatel2106.pocketci.core.navigation.routes

import kotlinx.serialization.Serializable

interface Destinations {

    @Serializable
    data object Register : Destinations
}