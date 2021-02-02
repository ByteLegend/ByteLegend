plugins {
    kotlin("js")
    id("configure-ktlint")
}

kotlin {
    js {
        browser {
        }
    }
}

dependencies {
    dependencies {
        api(project(":client:common"))
        api(kotlin("test-js"))
    }
}