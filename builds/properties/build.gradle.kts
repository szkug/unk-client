plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.kotlin.gradle.plugin)
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