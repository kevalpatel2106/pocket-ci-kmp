package com.kevalpatel2106.pocketci.connector.github.di

import com.kevalpatel2106.pocketci.connector.github.GitHubConnector
import com.kevalpatel2106.pocketci.connector.public.CIConnector
import com.kevalpatel2106.pocketci.entity.CIType
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val gitHubModule = module {
    factory(qualifier = named(CIType.GITHUB)) { GitHubConnector() }.bind(CIConnector::class)
}
