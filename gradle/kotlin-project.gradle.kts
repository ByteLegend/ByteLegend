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
rootProject.extensions.configure<ExtraPropertiesExtension>("ext") {
    val libs: (String) -> String by (rootProject.extensions.getByName("ext") as ExtraPropertiesExtension)

    val function: Project.() -> Unit = {
        apply(plugin = "java-library")
        apply(plugin = "org.jetbrains.kotlin.jvm")

        extensions.configure<JavaPluginExtension>("java") {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        dependencies {
            add("testImplementation", libs("kotlinx-coroutines-jdk8"))
            add("testImplementation", libs("kotlinx-coroutines-core"))
            add("testImplementation", libs("kotlin-stdlib-jdk8"))
        }
    }
    set("configureKotlin", function)
}
