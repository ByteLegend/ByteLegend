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
    id("configure-kotlin-js")
}

val libs: (String) -> String by rootProject.ext
val libVersions: (String) -> String by rootProject.ext

repositories {
    maven(url = "https://dl.bintray.com/kodein-framework/Kodein-DI/")
}

dependencies {
    implementation(project(":client:game-utils"))
    implementation(libs("kodein-di"))
    implementation(npm("react-textarea-autosize", libVersions("react-textarea-autosize")))
    implementation(npm("canvas-confetti", libVersions("canvas-confetti")))
    testImplementation(kotlin("test-js"))
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

