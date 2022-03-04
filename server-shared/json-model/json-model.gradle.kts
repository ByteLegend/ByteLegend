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
import com.bytelegend.buildsrc.json2java.Json2JavaConversion

plugins {
    id("java-library")
}

apply(plugin = "json2Java")

val libs: (String) -> String by rootProject.ext

dependencies {
    implementation(libs("com.google.guava:guava"))
    implementation(libs("jackson-core"))
    implementation(libs("jackson-databind"))
    implementation(libs("jackson-annotations"))
}

extra["json2Java"] = listOf(
    Json2JavaConversion().setSrcDir(projectDir.resolve("src/main/resources/json/event"))
        .setDestDir(projectDir.resolve("src/main/java"))
        .setTargetPackage("com.bytelegend.app.jsonmodel.generated.event"),
    Json2JavaConversion().setSrcDir(projectDir.resolve("src/main/resources/json/query"))
        .setDestDir(projectDir.resolve("src/main/java"))
        .setTargetPackage("com.bytelegend.app.jsonmodel.generated.query"),
    Json2JavaConversion().setSrcDir(projectDir.resolve("src/main/resources/json/stream"))
        .setDestDir(projectDir.resolve("src/main/java"))
        .setTargetPackage("com.bytelegend.app.jsonmodel.generated.stream"),
    Json2JavaConversion().setSrcDir(projectDir.resolve("src/main/resources/json/response"))
        .setDestDir(projectDir.resolve("src/main/java"))
        .setTargetPackage("com.bytelegend.app.jsonmodel.generated.response")
)
