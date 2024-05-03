plugins {
    alias(libs.plugins.kmp.lib)
    alias(krpcLibs.plugins.wire)
    alias(libs.plugins.properties)
}

buildscript {
    dependencies {
        classpath(libs.krpc.schema)
    }
}

val buildLocal = layout.buildDirectory.asFile.get()

wire {
    sourcePath {
        srcDir("../../protocol")
        include("account/rpc")
    }
    custom {
        schemaHandlerFactory = org.szkug.krpc.plugin.KrpcSchemaHandlerFactory.client()
        out = "$buildLocal/generated"
    }
}

localProperties {
    packageName = "org.szkug.keeting.network"
    properties = mapOf(
        "network.host.dev" to "DEV_HOST",
        "network.host.release" to "RELEASE_HOST"
    )
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
            implementation(projects.data.protocol)
        }
    }
}
