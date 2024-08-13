pluginManagement {
    repositories {
        google()
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}


plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}


// project setting
rootProject.name = "unk-client"

// gradle feature
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// build logic
includeBuild("builds")

// module include
// libs
include("libs:common")
include("libs:network")

// data
include("data:protocol")

// domain
include("domain:repositories")

// feature
include("feature:account")