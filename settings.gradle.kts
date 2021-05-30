rootProject.name = "ByteLegend"

include(":shared")
include(":utils")

include(":client:game-page")
include(":client:game-api")
include(":client:game-JavaIsland")
include(":client:game-GradleIsland")
include(":client:game-KotlinIsland")
include(":client:game-PythonIsland")
include(":client:game-GitIsland")
include(":client:game-GopherIsland")
include(":client:game-JavaIslandNewbieVillagePub")
include(":client:common")
include(":client:common-test-fixtures")
include(":server-shared:common")
include(":server-shared:test-fixtures")

include(":server-opensource")
if (settingsDir.resolve("server/server.gradle.kts").isFile) {
    include(":server:app")
    include(":server:json-model")
    include(":server:sync-server")
    include(":server:dal-dynamodb")
    include(":server:dal-api")
}

rootProject.children.forEach { it.configureBuildScriptName() }

fun ProjectDescriptor.configureBuildScriptName() {
    buildFileName = "${name}.gradle.kts"
    children.forEach { it.configureBuildScriptName() }
}

pluginManagement {
    plugins {
        id("org.jetbrains.kotlin.js") version "1.5.10"
        id("org.jetbrains.kotlin.jvm") version "1.5.10"
        id("org.jetbrains.kotlin.multiplatform") version "1.5.10"
        id("org.jetbrains.kotlin.plugin.serialization") version "1.5.10"
    }
}