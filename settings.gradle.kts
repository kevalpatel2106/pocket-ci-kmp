rootProject.name = "PocketCI"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    versionCatalogs {
        create("app") { from(files("./gradle/app.versions.toml")) }
        create("androidx") { from(files("./gradle/androidx.versions.toml")) }
    }
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}
rootDir.walk().maxDepth(2).filter {
    val notBuildSrc = it.name != "buildSrc"
    val notTemplateDir = !it.name.contains("template")
    val containsBuildFile = File(it, "build.gradle").exists() || File(it, "build.gradle.kts").exists()
    notBuildSrc && notTemplateDir && containsBuildFile
}.forEach {
    if (it.parentFile == rootDir) {
        include(it.name)
    } else {
        include("${it.parentFile.name}:${it.name}")
    }
}