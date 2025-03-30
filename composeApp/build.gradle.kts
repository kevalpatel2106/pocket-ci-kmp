import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.detekt)
}

detekt {
    config.setFrom("../rules/detekt-config.yml")
    baseline = file("../rules/baseline/baseline.xmk")
    buildUponDefaultConfig = true
    autoCorrect = true
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.fromTarget(getJDKVersion()))
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm("desktop")

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }

    cocoapods {
        version = "2.0"
        podfile = project.file("../iosApp/Podfile")
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(androidx.activity.compose)
            implementation(libs.bundles.koin.android)
            implementation(androidx.core.splashscreen)
        }
        androidInstrumentedTest.dependencies {
            implementation(androidx.bundles.android.test)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.navigation.compose)
            implementation(androidx.lifecycle.viewmodel)
            implementation(androidx.lifecycle.runtime.compose)
            implementation(libs.bundles.koin)

            // Projects
            implementation(projects.entity)
            implementation(projects.coreNavigation)
            implementation(projects.selector)
            implementation(projects.connectorPublic)
            implementation(projects.connectorBitrise)
            implementation(projects.connectorGithub)
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutines.swing)
            }
        }
    }
}

android {
    namespace = "com.kevalpatel2106.pocketci"
    compileSdk = app.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.kevalpatel2106.pocketci"
        minSdk = app.versions.android.minSdk.get().toInt()
        targetSdk = app.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("debug") {
            versionNameSuffix = "-debug"
            applicationIdSuffix = ".debug"
            isDebuggable = true
            isJniDebuggable = true
            isMinifyEnabled = false
        }
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = false
            isJniDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "../rules/proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.valueOf("VERSION_${getJDKVersion()}")
        targetCompatibility = JavaVersion.valueOf("VERSION_${getJDKVersion()}")
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
    detektPlugins(libs.bundles.detekt.rules)
}

compose.desktop {
    application {
        mainClass = "com.kevalpatel2106.pocketci.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.kevalpatel2106.pocketci"
            packageVersion = "1.0.0"

            val iconsRoot = project.file("desktop-icons")
            macOS {
                iconFile.set(iconsRoot.resolve("icon-mac.icns"))
            }
            windows {
                iconFile.set(iconsRoot.resolve("icon-windows.ico"))
                menuGroup = "Compose Examples"
                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "FD3AD4D7-30DB-425C-AA27-F6ADB8819284"
            }
            linux {
                iconFile.set(iconsRoot.resolve("icon-linux.png"))
            }
        }
    }
}

tasks.withType<Detekt>().configureEach {
    exclude { it.file.path.contains("build") }
}

private fun getJDKVersion() = File(".java-version").readText().trim()