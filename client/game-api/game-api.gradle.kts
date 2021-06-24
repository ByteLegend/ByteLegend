plugins {
    kotlin("js")
    id("configure-ktlint")
}

dependencies {
    api(project(":client:common")) {
        attributes {
            attribute(Attribute.of(
                "org.jetbrains.kotlin.js.compiler",
                org.jetbrains.kotlin.gradle.targets.js.KotlinJsCompilerAttribute::class.java
            ), org.jetbrains.kotlin.gradle.targets.js.KotlinJsCompilerAttribute.ir)
        }
    }
    testImplementation(kotlin("test-js"))
}

kotlin {
    js(IR) {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                    useConfigDirectory(project.buildDir.resolve("karmaConfig"))
                }
            }
        }
    }
}
