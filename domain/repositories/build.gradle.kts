
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(krpcLibs.plugins.wire)

    alias(libs.plugins.properties.build.network)
}

buildscript {
    dependencies {
        classpath(libs.krpc.schema)
    }
}

group = "org.szkug.keeting.domain"
val buildLocal = layout.buildDirectory.asFile.get()

wire {
    sourcePath {
    }
    custom {
        schemaHandlerFactory = org.szkug.krpc.plugin.KrpcSchemaHandlerFactory.client()
        out = "$buildLocal/generated"
    }
}


kotlin {

    jvm() // MARK: jvm target could be desktop & android library
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        iosMain.dependencies {
        }
        jvmMain.dependencies {
        }
        commonMain.dependencies {
            implementation(projects.libs.network)
        }
    }
}
