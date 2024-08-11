plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.gradle.plugin.kotlin)
    compileOnly(libs.gradle.plugin.wire)
    compileOnly(libs.kotlinpoet)
    compileOnly(libs.wire.schema)
    implementation(libs.krpc.schema)
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
        register("WireConfigurationPlugin") {
            id = "org.szkug.unk.krpc"
            implementationClass = "WireConfigurationPlugin"
        }
    }
}