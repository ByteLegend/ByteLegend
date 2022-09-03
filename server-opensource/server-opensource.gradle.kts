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
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    kotlin("jvm")
    kotlin("plugin.spring") version "1.7.10"
    id("configure-kotlin")
    id("configure-ktlint")
}

evaluationDependsOn(":utils")

repositories {
    mavenCentral()
}

val libs: (String) -> String by rootProject.ext
val libVersions: (String) -> String by rootProject.ext

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    listOf(
        "jackson-databind",
        "jackson-core",
        "jackson-bom",
        "jackson-annotations",
        "jackson-datatype-jdk8",
        "jackson-datatype-jsr310",
        "jackson-module-parameter-names",
        "jackson-dataformat-yaml"
    ).forEach {
        implementation(libs(it).substringBeforeLast(":")) {
            version {
                strictly(libVersions(it).substringAfter(":"))
            }
        }
    }

    implementation(libs("jackson-module-kotlin")) {
        version {
            strictly(libVersions("jackson-module-kotlin").substringAfter(":"))
        }
        exclude(group = "org.jetbrains.kotlin")
    }

    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(project(":shared"))
    implementation(project(":server-shared:common"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(project(":server-shared:test-fixtures"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

tasks.named<JavaExec>("bootRun") {
    dependsOn(":utils:buildDevelopmentGameResources")

    jvmArgs("-Dlocal.RRBD=${rootProject.ext["developmentRRBD"]}")
}
tasks.named<Test>("test") {
    dependsOn(":utils:buildProductionGameResources")
    systemProperty("local.RRBD", rootProject.file(rootProject.ext["productionRRBD"].toString()))
    systemProperty("project.dir", rootProject.projectDir.absolutePath)
    systemProperty("build.tmp.dir", temporaryDir)
}
