/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("configure-ktlint")
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
                api(libs("dynamodb-enhanced"))
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
                jvmTarget = "11"
            }
        }
    }
    js {
        compilations.getByName("main") {
            kotlinOptions {
            }
        }
    }
}

repositories {
    mavenCentral()
}
