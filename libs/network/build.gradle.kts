
plugins {
    alias(libs.plugins.kmp.lib)
    alias(libs.plugins.krpc)
}

wire {
    sourcePath {
        srcDir("../../protocol/common")
    }
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
        }
    }
}