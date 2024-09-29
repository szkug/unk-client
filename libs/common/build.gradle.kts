
plugins {
    alias(libs.plugins.unk.kmp.lib)
    alias(libs.plugins.unk.android.lib)
}

android {
    namespace = "org.szkug.unk.common"
}

kotlin {
    androidTarget()
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