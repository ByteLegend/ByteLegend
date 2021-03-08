
plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

val libs: (String) -> String by rootProject.ext

kotlin {
    targets {
        jvm()
        js {
            browser {
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlin("stdlib-js"))
                api(kotlin("stdlib-jdk8"))
                api(libs("kotlinx-serialization-json"))
            }
        }

        val jvmMain by getting {
            dependencies {
                api(libs("bson"))
                api(libs("jackson-annotations"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        // https://stackoverflow.com/questions/56508672/kotlin-multiplatform-gradle-unit-test-not-resolving-kotlin-test-reference
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit5"))
            }
        }

        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }

    jvm {
        val main by compilations.getting {
            kotlinOptions {
                jvmTarget = "1.8"
                freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
            }
        }
    }
    js {
        compilations.getByName("main") {
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
            }
        }
    }
}

repositories {
    mavenCentral()
}
