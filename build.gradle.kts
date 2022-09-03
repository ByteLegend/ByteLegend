/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.bytelegend.buildsupport.isCI

plugins {
    id("idea")
    kotlin("jvm") apply false
    kotlin("js") apply false
    kotlin("multiplatform") apply false
    kotlin("plugin.serialization") apply false
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
            jvmArgs("-Djava.io.tmpdir=${project.buildDir.resolve("tmp").absolutePath}")
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

// Sync with Kotlin embedded version
rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin> {
    rootProject.the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>().nodeVersion = "16.17.0"
}

idea {
    module {
        excludeDirs = setOf(file("server/app/logs"), file("build"), file("github1s/dist"), file("github1s/vscode-web-github1s/dist"))
    }
}
