
plugins {
    alias(libs.plugins.kmp.lib)
    alias(krpcLibs.plugins.wire)
}

buildscript {
    dependencies {
        classpath(libs.krpc.schema)
    }
}

val buildLocal = layout.buildDirectory.asFile.get()


wire {
    sourcePath {
        srcDir("../../protocol/common")
        include("account/model")
    }
    custom {
        schemaHandlerFactory = org.szkug.krpc.plugin.KrpcSchemaHandlerFactory.client()
        out = "$buildLocal/generated"
    }
}

