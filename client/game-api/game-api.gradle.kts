plugins {
    kotlin("js")
    id("configure-ktlint")
}

dependencies {
    api(project(":shared"))
    implementation(project(":client:common"))
}

kotlin {
    js {
        browser {
        }
    }
}
