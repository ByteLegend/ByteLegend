package com.bytelegend.utils

import com.bytelegend.app.shared.entities.BackendMissionDefinition
import com.bytelegend.app.shared.entities.MissionDefinition
import com.fasterxml.jackson.core.type.TypeReference
import java.io.File

/**
 * Merge all missions.yml to missions-all.json for backend.
 */
fun main(args: Array<String>) {
    merge(File(args[0]), File(args[1]))
}

fun merge(inputMapsDir: File, outputAllJson: File) {
    val missions: MutableList<BackendMissionDefinition> = mutableListOf()
    forEachMap(inputMapsDir) { mapId ->
        val missionsYml = File(inputMapsDir, "$mapId/missions.yml")
        if (missionsYml.isFile) {
            YAML_PARSER.readValue(missionsYml, object : TypeReference<List<MissionDefinition>>() {})
                .map { BackendMissionDefinition(mapId, it) }
                .forEach(missions::add)
        }
    }
    outputAllJson.writeText(jsonReader.writeValueAsString(missions))
}
