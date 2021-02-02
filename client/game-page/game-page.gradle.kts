plugins {
    kotlin("js")
    id("configure-ktlint")
}

val libs: (String) -> String by rootProject.ext

repositories {
    maven(url = "https://dl.bintray.com/kodein-framework/Kodein-DI/")
}

dependencies {
    implementation(project(":client:common"))
    implementation(project(":client:game-api"))
    implementation(libs("kodein-di"))
    testImplementation(kotlin("test-js"))
}

kotlin {
    js {
        browser {
        }
    }
}