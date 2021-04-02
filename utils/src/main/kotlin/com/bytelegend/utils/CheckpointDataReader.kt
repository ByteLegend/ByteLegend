package com.bytelegend.utils

import com.bytelegend.app.shared.entities.Checkpoint
import com.bytelegend.app.shared.entities.Mission
import com.bytelegend.app.shared.entities.Tutorial
import java.io.File

/**
 * Read data under `game-data/` directory.
 */
class CheckpointDataReader(
    private val mapIdToCheckpointDir: Map<String, File>
) {
    fun getCheckpointsOnMap(mapId: String): List<Checkpoint> = maps.getValue(mapId).checkpoints.values.toList()
    fun getMissionsOnMap(mapId: String): List<Mission> = maps.getValue(mapId).missions.values.toList()
    fun getAllCheckpoints(): Map<String, Map<String, Checkpoint>> = maps.map { it.key to it.value.checkpoints }.toMap()

    private val maps: Map<String, MapData> = mapIdToCheckpointDir.map {
        it.key to MapData(it.value)
    }.toMap()
}

class MapData(ymlDir: File) {
    private val duplicateIdChecker: HashSet<String> = HashSet()
    val checkpoints: Map<String, Checkpoint> = ymlDir.listFiles().filter {
        it.name.endsWith(".yml")
    }.flatMap {
        YAML_PARSER
            .readValues(YAML_FACTORY.createParser(it), Checkpoint::class.java)
            .readAll()
    }.onEach {
        require(duplicateIdChecker.add(it.id)) { "Duplicate id: ${it.id}" }
    }.map {
        it.id to it
    }.toMap()

    val missions: Map<String, Mission> = checkpoints.values.flatMap {
        it.missions
    }.onEach {
        require(duplicateIdChecker.add(it.id)) { "Duplicate id: ${it.id}" }
    }.map {
        it.id to it
    }.toMap()

    val tutorials: Map<String, Tutorial> = checkpoints.values.flatMap {
        it.tutorials
    }.onEach {
        require(duplicateIdChecker.add(it.id)) { "Duplicate id: ${it.id}" }
    }.map {
        it.id to it
    }.toMap()
}
