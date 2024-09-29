plugins {
    alias(libs.plugins.unk.kmp.lib)
    alias(libs.plugins.krpc)
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
