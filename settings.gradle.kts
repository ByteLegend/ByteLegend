rootProject.name = "ByteLegend"

include(":shared")
include(":utils")

include(":client:game-page")
include(":client:game-api")
include(":client:game-JavaIsland")
include(":client:game-JavaIslandNewbieVillagePub")
include(":client:common")
include(":client:common-test-fixtures")

include(":server-api")

when {
    System.getProperty("server.impl") == "opensource" -> useOpensourceServer()
    System.getProperty("server.impl") == "default" -> useDefaultServer()
    settingsDir.resolve("server/server.gradle.kts").isFile -> useDefaultServer()
    else -> useOpensourceServer()
}

rootProject.children.forEach { it.configureBuildScriptName() }

fun ProjectDescriptor.configureBuildScriptName() {
    buildFileName = "${name}.gradle.kts"
    children.forEach { it.configureBuildScriptName() }
}

fun useOpensourceServer() {
    include(":server-opensource")
}

fun useDefaultServer() {
    include(":server:app")
    include(":server:json-model")
    include(":server:sync-server")
}