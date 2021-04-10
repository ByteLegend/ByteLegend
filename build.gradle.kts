plugins {
    id("idea")
    kotlin("jvm") version "1.4.32" apply false
    kotlin("js") version "1.4.32" apply false
    kotlin("multiplatform") version "1.4.32" apply false
    kotlin("plugin.serialization") version "1.4.32" apply false
    id("build-scan")
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