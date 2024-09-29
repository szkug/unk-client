
plugins {
    alias(libs.plugins.unk.kmp.lib)
    alias(libs.plugins.krpc)
    alias(libs.plugins.properties)
}

wire {
    sourcePath {
        srcDir("../../protocol/common")
    }
}

localProperties {
    packageName = "org.szkug.unk.network"
    properties = mapOf(
        "network.host.dev" to "DEV_HOST",
        "network.host.release" to "RELEASE_HOST"
    )
}

kotlin {
    sourceSets {
        iosMain.dependencies {
            implementation(libs.ktor.cio)
        }
        jvmMain.dependencies {
            implementation(libs.ktor.okhttp)
        }
        commonMain.dependencies {
            api(libs.coroutines.core)
            implementation(libs.ktor.core)
            implementation(libs.ktor.loging)
            implementation(projects.libs.common)
        }
    }
}