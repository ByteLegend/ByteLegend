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
import com.bytelegend.buildsupport.OpenSourceLibrary

plugins {
    id("configure-kotlin")
    id("configure-ktlint")
    id("json2Java")
    id("buildGameResources")
}

val libs: (String) -> String by rootProject.ext
val oss: List<OpenSourceLibrary> by rootProject.ext

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

dependencies {
    implementation(project(":shared"))
    implementation(platform(libs("libraries-bom")))
    implementation("com.google.cloud:google-cloud-translate")
    implementation(libs("core-kotlin"))
    implementation(libs("java-jwt"))
    implementation(libs("bcprov-jdk15on"))
    implementation(libs("jackson-dataformat-yaml"))
    implementation(libs("jackson-module-kotlin")) {
        exclude(group = "org.jetbrains.kotlin")
    }
    implementation(libs("spring-core"))
    implementation(libs("opencc4j"))
    implementation(libs("kotlin-reflect"))
    implementation(libs("kotlinx-serialization-json"))
    implementation(libs("batik-svggen"))
    implementation(libs("batik-dom"))
    implementation(libs("batik-swing"))
    implementation(libs("commonmark"))
    implementation(libs("commonmark-ext-task-list-items"))
    implementation(libs("animated-gif-lib-for-java"))
    implementation(libs("commons-io"))

    testImplementation(libs("junit-jupiter-api"))
    testImplementation(libs("junit-jupiter-engine"))
    testImplementation(libs("junit-jupiter-params"))
}

fun registerJavaExecInRootProject(
    name: String,
    mainClassName: String,
    mandatorySystemProperties: List<String> = emptyList(),
    optionalSystemProperties: List<String> = emptyList(),
    action: JavaExec.() -> Unit = {}
): TaskProvider<JavaExec> =
    tasks.register<JavaExec>(name) {
        doFirst {
            mandatorySystemProperties.forEach {
                jvmArgs("-D${it}=${System.getProperty(it) ?: throw IllegalArgumentException("No system property $it!")}")
            }
            optionalSystemProperties.filter { System.getProperty(it) != null }.forEach {
                jvmArgs("-D${it}=${System.getProperty(it)}")
            }
        }
        jvmArgs("-Dapple.awt.UIElement=true")
        classpath = project.sourceSets["main"].runtimeClasspath
        workingDir = rootProject.rootDir
        mainClass.set(mainClassName)
        action()
    }

registerJavaExecInRootProject("createNewMap", "com.bytelegend.utils.CreateNewMapKt", listOf("mapId", "mapGridWidth", "mapGridHeight"))
registerJavaExecInRootProject("createEmptyMissionYamls", "com.bytelegend.utils.CreateEmptyMissionYamlsKt", listOf("mapId"))
registerJavaExecInRootProject(
    "generateTileset", "com.bytelegend.utils.GenerateTilesetKt",
    listOf("inputFiles", "tilesetName", "groupType"), listOf("outputFrameWidth", "backgroundColor")
)
registerJavaExecInRootProject(
    "addTilesetToMap", "com.bytelegend.utils.AddTilesetToMapKt",
    listOf("tilesetName", "groupType"), listOf("mapId")
) {
    mustRunAfter("generateTileset")
}

val checkLicenses = registerJavaExecInRootProject("checkLicenses", "com.bytelegend.utils.CheckLicensesKt")
registerJavaExecInRootProject("addLicenses", "com.bytelegend.utils.AddLicensesKt")

tasks.named("check") {
    dependsOn(checkLicenses)
}


