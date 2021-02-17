plugins {
    kotlin("js")
    id("configure-ktlint")
}

dependencies {
    compileOnly(project(":client:game-api"))
}

kotlin {
    js {
        browser {
        }
    }
}
