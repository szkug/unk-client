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
        register("KMPLibConfigurationPlugin") {
            id = "org.szkug.unk.kmp.lib"
            implementationClass = "KMPLibConfigurationPlugin"
        }
    }
    plugins {
        register("AndroidLibConfigurationPlugin") {
            id = "org.szkug.unk.android.lib"
            implementationClass = "AndroidLibConfigurationPlugin"
        }
    }
}