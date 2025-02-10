import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.kotlinCocoapods)
}

kotlin {
    @Suppress("UnstableApiUsage")
    androidLibrary {
        namespace = "com.kevalpatel2106.pocketci.selector"
        minSdk = app.versions.android.minSdk.get().toInt()
        compileSdk = app.versions.android.compileSdk.get().toInt()

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.bundles.koin)
                // Add KMP dependencies here
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                // Add Android-specific dependencies here.
            }
        }

        getByName("androidDeviceTest") {
            dependencies {
                implementation(androidx.bundles.android.test)
            }
        }

        iosMain {
            dependencies {
                // Add iOS-specific dependencies here.
            }
        }

        jvmMain {
            dependencies {
                // Add Desktop-specific dependencies here.
            }
        }
    }
}
