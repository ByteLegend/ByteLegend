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
pluginManagement {
    plugins {
        id("org.jetbrains.kotlin.js") version "1.7.10"
        id("org.jetbrains.kotlin.jvm") version "1.7.10"
        id("org.jetbrains.kotlin.multiplatform") version "1.7.10"
        id("org.jetbrains.kotlin.plugin.serialization") version "1.7.10"
    }
}

plugins {
    id("com.gradle.enterprise") version "3.11.1"
    id("io.github.gradle.gradle-enterprise-conventions-plugin") version "0.7.6"
}

rootProject.name = "ByteLegend"

require(JavaVersion.current() >= JavaVersion.VERSION_11) {
    "You must use at least Java 11 to build the project, you're currently using ${System.getProperty("java.version")}"
}

include(":shared")
include(":utils")

include(":client:game-page")
include(":client:game-utils")
include(":client:game-api")
include(":client:game-JavaIsland")
include(":client:game-GradleIsland")
include(":client:game-KotlinIsland")
include(":client:game-PythonIsland")
include(":client:game-GitIsland")
include(":client:game-GopherIsland")
include(":client:game-JavaIslandNewbieVillagePub")
include(":client:game-JavaIslandCommentDungeon")
include(":client:game-JavaIslandDebuggerDungeon")
include(":client:game-JavaIslandMavenDungeon")
include(":client:game-JavaIslandSpringDungeon")
include(":client:game-JavaIslandConcurrencyDungeon")
include(":client:game-JavaIslandSeniorJavaCastle")
include(":client:game-ShellIsland")
include(":client:game-DatabaseIsland")
include(":client:game-WebIsland")
include(":client:game-AlgorithmIsland")
include(":client:game-DataStructureIsland")
include(":client:common")
include(":server-shared:common")
include(":server-shared:test-fixtures")
include(":server-shared:json-model")

include(":server-opensource")
if (settingsDir.resolve("server/server.gradle.kts").isFile) {
    include(":server:app")
    include(":server:webeditor-browser-test")
    include(":server:dal-dynamodb")
    include(":server:dal-api")
    include(":server:deployment")
    include(":server:cookie-provider")
    include(":server:code-checker")
    include(":server:code-merger")
    include(":server:repo-cleaner")
    include(":server:github-release")
    include(":server:github-common")
    include(":server:githubdiffparser")
    include(":server:dal-dynamodb-shared")
}

rootProject.children.forEach { it.configureBuildScriptName() }

fun ProjectDescriptor.configureBuildScriptName() {
    buildFileName = "${name}.gradle.kts"
    children.forEach { it.configureBuildScriptName() }
}


