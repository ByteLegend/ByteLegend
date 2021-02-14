plugins {
    kotlin("js")
    id("configure-ktlint")
}

dependencies {
    api(project(":shared"))
    api(project(":client:common"))
}

kotlin {
    js {
        browser {
        }
    }
}
