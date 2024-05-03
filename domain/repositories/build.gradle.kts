
plugins {
    alias(libs.plugins.kmp.lib)
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
    sourceSets {
        iosMain.dependencies {
        }
        jvmMain.dependencies {
        }
        commonMain.dependencies {
            implementation(libs.krpc.runtime)
            implementation(projects.libs.network)
        }
    }
}
