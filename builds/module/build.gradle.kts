plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.gradle.plugin.kotlin)
    implementation(libs.kotlinpoet)
}

gradlePlugin {
    plugins {
        register("KMPLibConfigurationPlugin") {
            id = "org.szkug.unk.kmp.lib"
            implementationClass = "KMPLibConfigurationPlugin"
        }
    }
}