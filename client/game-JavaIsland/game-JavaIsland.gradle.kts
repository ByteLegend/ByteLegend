plugins {
    id("configure-kotlin-js")
}

dependencies {
    implementation(project(":client:game-api")) {
        attributes {
            attribute(Attribute.of("org.jetbrains.kotlin.js.compiler", org.jetbrains.kotlin.gradle.targets.js.KotlinJsCompilerAttribute::class.java), org.jetbrains.kotlin.gradle.targets.js.KotlinJsCompilerAttribute.ir)
        }
    }
}
