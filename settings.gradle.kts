

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}


plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}


// project setting
rootProject.name = "ketting-client"

// gradle feature
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// build logic

// module include