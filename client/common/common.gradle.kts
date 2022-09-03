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
    kotlin("plugin.serialization")
    kotlin("js")
    id("generate-karma-config")
    id("configure-ktlint")
}

val libs: (String) -> String by rootProject.ext
val libVersions: (String) -> String by rootProject.ext

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

dependencies {
    api(project(":shared"))

    api(libs("kotlin-react"))
    api(libs("kotlin-react-dom"))
    api(libs("kotlin-extensions"))
    api(npm("react", libVersions("react")))
    api(npm("react-dom", libVersions("react")))
    api(libs("kotlinx-coroutines-core-js"))
    api(libs("kotlinx-browser"))

    api(npm("react-bootstrap", libVersions("react-bootstrap")))
    api(npm("bootstrap-switch-button-react", libVersions("bootstrap-switch-button-react")))

    testImplementation(kotlin("test-js"))
}

