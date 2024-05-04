
plugins {
    alias(libs.plugins.kotlin.multiplatform)
}


kotlin {
    jvm() // MARK: jvm target could be desktop & android library
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    applyDefaultHierarchyTemplate()
    sourceSets {
        iosMain.dependencies {
        }
        jvmMain.dependencies {
        }
        commonMain.dependencies {
            implementation(libs.koin.core)
        }
    }
}