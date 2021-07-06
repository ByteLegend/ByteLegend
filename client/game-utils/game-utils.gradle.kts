plugins {
    id("configure-kotlin-js")
}

kotlin {
    js {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
    }
}

dependencies {
    testImplementation(kotlin("test-js"))
}
