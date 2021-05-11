package com.bytelegend.utils

import com.bytelegend.app.shared.entities.mission.MissionSpec
import com.bytelegend.app.shared.entities.mission.Tutorial
import java.io.File

/**
 * Read data under `game-data/` directory.
 */
class MissionDataReader(
    private val mapIdToMissionsDir: Map<String, File>
) {
    fun getMissionsOnMap(mapId: String): List<MissionSpec> = maps.getValue(mapId).missionSpecs.values.toList()

    private val maps: Map<String, MapData> = mapIdToMissionsDir.map {
        it.key to MapData(it.value)
    }.toMap()
}

class MapData(ymlDir: File) {
    private val duplicateIdChecker: HashSet<String> = HashSet()
    val missionSpecs: Map<String, MissionSpec> = ymlDir.listFiles().filter {
        it.name.endsWith(".yml")
    }.flatMap {
        YAML_PARSER
            .readValues(YAML_FACTORY.createParser(it), MissionSpec::class.java)
            .readAll()
    }.onEach {
        require(duplicateIdChecker.add(it.id)) { "Duplicate id: ${it.id}" }
    }.associateBy {
        it.id
    }

    val tutorials: Map<String, Tutorial> = missionSpecs.values.flatMap {
        it.tutorials?.data ?: emptyList()
    }.onEach {
        require(duplicateIdChecker.add(it.id)) { "Duplicate id: ${it.id}" }
    }.associateBy {
        it.id
    }
}
