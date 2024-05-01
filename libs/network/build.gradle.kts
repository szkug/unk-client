
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(krpcLibs.plugins.wire)
}

buildscript {
    dependencies {
        classpath("org.szkug.krpc:schema")
    }
}

wire {
    sourcePath {
        srcDir("../../protocol/common")
    }
    custom {
        schemaHandlerFactory = org.szkug.krpc.plugin.KrpcSchemaHandlerFactory.client()
        out = "${layout.buildDirectory.asFile.get()}/generated"
    }
}


kotlin {

    jvm() // MARK: jvm target could be desktop & android library
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    // Set gradle property kotlin.mpp.applyDefaultHierarchyTemplate=true
    // https://youtrack.jetbrains.com/issue/KT-60734/
    // applyDefaultHierarchyTemplate()

    sourceSets {
        iosMain.dependencies {
            implementation(libs.ktor.cio)
        }
        jvmMain.dependencies {
            implementation(libs.ktor.okhttp)
        }
        commonMain.dependencies {
            api(libs.coroutines.core)
            api(krpcLibs.wire.runtime)
            implementation(libs.ktor.core)
            implementation(libs.ktor.loging)
        }
    }
}