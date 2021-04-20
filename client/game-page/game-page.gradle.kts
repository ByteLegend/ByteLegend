plugins {
    kotlin("js")
    id("configure-ktlint")
}

val libs: (String) -> String by rootProject.ext
val libVersions: (String) -> String by rootProject.ext

repositories {
    maven(url = "https://dl.bintray.com/kodein-framework/Kodein-DI/")
}

dependencies {
    implementation(project(":client:common"))
    implementation(project(":client:game-api"))
    implementation(libs("kodein-di"))
    implementation(npm("react-player", libVersions("react-player")))
    implementation(npm("react-select", libVersions("react-select")))
    testImplementation(kotlin("test-js"))
}

kotlin {
    js {
        browser {
        }
    }
}