
plugins {
    alias(libs.plugins.kmp.lib)
    alias(krpcLibs.plugins.wire)
}

buildscript {
    dependencies {
        classpath(libs.krpc.schema)
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
    sourceSets {
        iosMain.dependencies {
            implementation(libs.ktor.cio)
        }
        jvmMain.dependencies {
            implementation(libs.ktor.okhttp)
        }
        commonMain.dependencies {
            api(libs.coroutines.core)
            api(libs.krpc.runtime)
            implementation(libs.ktor.core)
            implementation(libs.ktor.loging)
        }
    }
}