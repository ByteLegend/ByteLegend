import org.jetbrains.kotlin.gradle.dsl.KotlinJsProjectExtension
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack
import org.jetbrains.kotlin.gradle.targets.js.KotlinJsCompilerAttribute

plugins {
    kotlin("js")
    id("configure-ktlint")
}

//dependencies {
//    "implementation"(project(":client:game-api")) {
//        attributes {
//            attribute(Attribute.of("org.jetbrains.kotlin.js.compiler", KotlinJsCompilerAttribute::class.java), KotlinJsCompilerAttribute.ir)
//        }
//    }
//}

(extensions.getByName("kotlin") as KotlinJsProjectExtension).js {
    browser()
}

tasks.named<KotlinWebpack>("browserDevelopmentWebpack").configure {
    destinationDirectory = project.buildDir.resolve("distribution-development")
}

tasks.named<KotlinWebpack>("browserProductionWebpack").configure {
    destinationDirectory = project.buildDir.resolve("distribution-production")
}
