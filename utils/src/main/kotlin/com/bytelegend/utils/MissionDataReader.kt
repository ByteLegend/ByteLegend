package com.bytelegend.utils

import com.bytelegend.app.shared.entities.Mission
import com.bytelegend.app.shared.entities.Tutorial
import com.bytelegend.app.shared.entities.flatten
import java.io.File

/**
 * Read data under `game-data/` directory.
 */
class MissionDataReader(
    private val mapIdToMissionsDir: Map<String, File>
) {
    fun getCheckpointsOnMap(mapId: String): List<Mission> = maps.getValue(mapId).missions.values.toList()
    fun getMissionsOnMap(mapId: String): List<Mission> = maps.getValue(mapId).missions.values.toList()
    fun getAllMissions(): Map<String, Map<String, Mission>> = maps.map { it.key to it.value.missions }.toMap()

    private val maps: Map<String, MapData> = mapIdToMissionsDir.map {
        it.key to MapData(it.value)
    }.toMap()
}

class MapData(ymlDir: File) {
    private val duplicateIdChecker: HashSet<String> = HashSet()
    val missions: Map<String, Mission> = ymlDir.listFiles().filter {
        it.name.endsWith(".yml")
    }.flatMap {
        YAML_PARSER
            .readValues(YAML_FACTORY.createParser(it), Mission::class.java)
            .readAll()
    }.onEach {
        require(duplicateIdChecker.add(it.id)) { "Duplicate id: ${it.id}" }
    }.flatMap {
        it.flatten()
    }.associateBy {
        it.id
    }

    val tutorials: Map<String, Tutorial> = missions.values.flatMap {
        it.tutorials
    }.onEach {
        require(duplicateIdChecker.add(it.id)) { "Duplicate id: ${it.id}" }
    }.associateBy {
        it.id
    }
}
