plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.gradle.plugin.kotlin)
    compileOnly(libs.gradle.plugin.android)
    implementation(libs.kotlinpoet)
}

gradlePlugin {
    plugins {
        register("localPropertiesBuildPlugin") {
            id = "org.szkug.keeting.local.properties"
            implementationClass = "LocalPropertiesBuildPlugin"
        }
    }
}