plugins {
    alias(libs.plugins.kmp.lib)
    alias(libs.plugins.krpc)
    alias(libs.plugins.properties)
}

dependencies {
    protoPath(projects.data.protocol)
}

wire {
    sourcePath {
        srcDir("../../protocol")
        include("account/rpc/**")
    }
    protoPath {
        srcDir("../../protocol")
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
        }
        jvmMain.dependencies {
        }
        commonMain.dependencies {
            implementation(projects.libs.network)
            implementation(projects.data.protocol)
        }
    }
}
