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
val libs: (String) -> String by rootProject.ext

dependencies {
    api(libs("junit-jupiter-api"))
    api(libs("junit-jupiter-engine"))
    api(libs("junit-jupiter-params"))
    api(libs("mockk")) {
        exclude(group = "org.jetbrains.kotlin")
    }
    api(libs("org.testcontainers:testcontainers"))
    api(libs("org.testcontainers:selenium"))
    api(libs("org.testcontainers:junit-jupiter"))
    api(libs("selenium-api"))
    api(libs("selenium-chrome-driver"))
    api(libs("selenium-firefox-driver"))
    api(project(":shared"))
    api(project(":server-shared:common"))
    api(project(":server-shared:json-model"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}
