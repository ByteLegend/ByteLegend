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
val libVersions: (String) -> String by rootProject.ext

dependencies {
    listOf(
        "jackson-databind",
        "jackson-core",
        "jackson-dataformat-yaml"
    ).forEach {
        api(libs(it).substringBeforeLast(":")) {
            version {
                strictly(libVersions(it).substringAfter(":"))
            }
        }
    }
    api(libs("jackson-module-kotlin")) {
        version {
            strictly(libVersions("jackson-module-kotlin").substringAfter(":"))
        }
        exclude(group = "org.jetbrains.kotlin")
    }
    api(project(":shared"))
}
