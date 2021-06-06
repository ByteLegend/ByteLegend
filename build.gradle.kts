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

    repositories {
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

idea {
    module {
        excludeDirs = setOf(file("server/app/logs"), file("build"))
    }
}
