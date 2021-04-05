plugins {
    kotlin("js")
    id("configure-ktlint")
}

dependencies {
    api(project(":shared"))
    api(project(":client:common"))
    testImplementation(kotlin("test-js"))
}

kotlin {
    js {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                    useConfigDirectory(project.buildDir.resolve("karmaConfig"))
                }
            }
        }
    }
}