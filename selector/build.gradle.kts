import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlinxSerialization)
}

detekt {
    config.setFrom("../rules/detekt-config.yml")
    baseline = file("../rules/baseline/baseline.xmk")
    buildUponDefaultConfig = true
    autoCorrect = true
}

kotlin {
    @Suppress("UnstableApiUsage")
    androidLibrary {
        namespace = "com.kevalpatel2106.pocketci.selector"
        minSdk = app.versions.android.minSdk.get().toInt()
        compileSdk = app.versions.android.compileSdk.get().toInt()

        withHostTestBuilder {
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    jvm()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
    }

    cocoapods {
        version = "2.0"
        podfile = project.file("../iosApp/Podfile")
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.entity)

            implementation(libs.bundles.common.base)
            implementation(libs.bundles.common.koin)
            implementation(libs.bundles.common.coroutines)

            // Compose
            implementation(libs.navigation.compose)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            // Projects
            implementation(projects.coreNavigation)
            implementation(projects.connectorPublic)
        }

        commonTest.dependencies {
            implementation(libs.bundles.common.test)

            // Projects
            implementation(projects.coreTest)
        }
    }
}

dependencies {
    detektPlugins(libs.bundles.detekt.rules)
}

tasks.withType<Detekt>().configureEach {
    exclude { it.file.path.contains("build") }
}