plugins {
    id("idea")
    kotlin("jvm") apply false
    kotlin("js") apply false
    kotlin("multiplatform") apply false
    kotlin("plugin.serialization") apply false
    id("build-scan")
    id("build-receipt")
    id("dependencies")
}

allprojects {
    group = "com.bytelegend.app"
    version = "1.0.0-SNAPSHOT"
}

idea {
    module {
        excludeDirs = setOf(file("server/app/logs"), file("build"))
    }
}
