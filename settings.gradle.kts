dependencyResolutionManagement {
    versionCatalogs {
        versionCatalogs {
            create("krpcLibs") {
                from(files("krpc/gradle/libs.versions.toml"))
            }
        }
    }
}


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
rootProject.name = "unk-client"

// gradle feature
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// build logic
includeBuild("builds")
includeBuild("krpc") {
    dependencySubstitution {
        substitute(module("org.szkug.krpc:schema")).using(project(":wire-schema"))
        substitute(module("org.szkug.krpc:runtime")).using(project(":krpc-runtime"))
    }
}


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