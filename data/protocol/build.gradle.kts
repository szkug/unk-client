
plugins {
    alias(libs.plugins.kmp.lib)
    alias(libs.plugins.krpc)
}

wire {
    sourcePath {
        srcDir("../../protocol")
        include("account/model/**")
    }
}

