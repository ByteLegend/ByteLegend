plugins {
    kotlin("js")
    id("configure-ktlint")
}

kotlin {
    js(IR) {
        browser {
        }
    }
}

dependencies {
    dependencies {
        api(project(":client:common")) {
            attributes {
                attribute(Attribute.of(
                    "org.jetbrains.kotlin.js.compiler",
                    org.jetbrains.kotlin.gradle.targets.js.KotlinJsCompilerAttribute::class.java
                ), org.jetbrains.kotlin.gradle.targets.js.KotlinJsCompilerAttribute.ir)
            }
        }
        api(kotlin("test-js"))
    }
}
