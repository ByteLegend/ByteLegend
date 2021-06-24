plugins {
    kotlin("js")
    id("configure-ktlint")
}

dependencies {
    implementation(project(":client:game-api"))
}

kotlin {
    js(IR) {
        browser {
        }
    }
}
