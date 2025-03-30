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
}

detekt {
    config.setFrom("../rules/detekt-config.yml")
    baseline = file("../rules/baseline/baseline.xmk")
    buildUponDefaultConfig = true
    autoCorrect = true
}

kotlin {
    androidLibrary {
        namespace = "com.kevalpatel2106.pocketci.core.navigation"
        minSdk = app.versions.android.minSdk.get().toInt()
        compileSdk = app.versions.android.compileSdk.get().toInt()
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
        commonMain {
            dependencies {
                implementation(libs.navigation.compose)
                // Add KMP dependencies here
            }
        }
    }
}

dependencies {
    detektPlugins(libs.bundles.detekt.rules)
}

tasks.withType<Detekt>().configureEach {
    exclude { it.file.path.contains("build") }
}