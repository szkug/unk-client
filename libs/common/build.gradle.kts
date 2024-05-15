
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
}

android {
    namespace = "org.szkug.keeting.common"
}

kotlin {
    jvm() // MARK: jvm target could be desktop & android library
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    applyDefaultHierarchyTemplate()
    sourceSets {
        iosMain.dependencies {
        }
        androidMain.dependencies {
        }
        jvmMain.dependencies {
        }
        commonMain.dependencies {
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}