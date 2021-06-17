import com.bytelegend.buildsupport.isCI

plugins {
    id("idea")
    kotlin("jvm") apply false
    kotlin("js") apply false
    kotlin("multiplatform") apply false
    kotlin("plugin.serialization") apply false
    id("build-scan")
    id("setupBuildVersion")
    id("dependencies")
}

allprojects {
    group = "com.bytelegend.app"
    version = "1.0.0-SNAPSHOT"

    repositories {
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }

    tasks.withType<AbstractArchiveTask>().configureEach {
        // KotlinWebpack.runtimeClasspath normalization is AbsoluteFile
        isPreserveFileTimestamps = false
        isReproducibleFileOrder = true
    }
    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
        if (isCI()) {
            jvmArgs("-Djava.io.tmpdir=${rootProject.buildDir.resolve("tmp").absolutePath}")
        }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack>().configureEach {
        outputs.cacheIf { true }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask>().configureEach {
        outputs.cacheIf { true }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinPackageJsonTask>().configureEach {
        outputs.cacheIf { true }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.targets.js.npm.tasks.RootPackageJsonTask>().configureEach {
        outputs.cacheIf { true }
    }
//    tasks.withType<org.jetbrains.kotlin.gradle.targets.js.dukat.IntegratedDukatTask>().configureEach {
//        // it takes 8s to fingerprint the inputs
//        outputs.upToDateWhen { false }
//    }
}

idea {
    module {
        excludeDirs = setOf(file("server/app/logs"), file("build"))
    }
}
