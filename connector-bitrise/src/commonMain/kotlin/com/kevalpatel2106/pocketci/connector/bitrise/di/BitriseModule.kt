package com.kevalpatel2106.pocketci.connector.bitrise.di

import com.kevalpatel2106.pocketci.connector.bitrise.BitriseConnector
import com.kevalpatel2106.pocketci.connector.public.CIConnector
import com.kevalpatel2106.pocketci.entity.CIType
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val bitriseModule = module {
    factory(qualifier = named(CIType.BITRISE)) { BitriseConnector() }.bind(CIConnector::class)
}
