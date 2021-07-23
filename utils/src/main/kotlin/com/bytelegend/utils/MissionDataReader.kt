package com.bytelegend.utils

import com.bytelegend.app.shared.entities.mission.MissionSpec
import com.bytelegend.app.shared.entities.mission.Tutorial
import java.io.File
import java.lang.IllegalStateException

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
    }.map {
        try {
            val missionSpec = YAML_PARSER.readValue(it, MissionSpec::class.java)
            require(missionSpec.id == it.nameWithoutExtension) {
                "${missionSpec.id} must be put into a file named '${missionSpec.id}.yml'!"
            }
            missionSpec
        } catch (e: Throwable) {
            throw IllegalStateException("Failed to parse ${it.absolutePath}", e)
        }
    }.onEach {
        require(duplicateIdChecker.add(it.id)) { "Duplicate id: ${it.id}" }
    }.associateBy {
        it.id
    }

    val tutorials: Map<String, Tutorial> = missionSpecs.values.flatMap {
        it.tutorials
    }.onEach {
        require(duplicateIdChecker.add(it.id)) { "Duplicate id: ${it.id}" }
    }.associateBy {
        it.id
    }
}
