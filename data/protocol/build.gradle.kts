
plugins {
    alias(libs.plugins.unk.kmp.lib)
    alias(libs.plugins.krpc)
}

wire {
    sourcePath {
        srcDir("../../protocol")
        include("account/model/**")
    }
}

