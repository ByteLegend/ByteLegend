package com.bytelegend.utils

import java.io.File

/**
 * Merge all YAMLs in `game-data` map directory to missions-all.json for backend.
 *
 * Arguments:
 * 1. Input dir: ./game-data
 * 2. Output json: RRBD/map/missions-all.json
 */
fun main(args: Array<String>) {
    val mapIdToCheckpointDir = File(args[0]).listFiles()
        .filter { it.isDirectory }.associate { it.name to it.resolve("missions") }
    val checkpointReader = MissionDataReader(mapIdToCheckpointDir)
    uglyObjectMapper.writeValue(File(args[1]), checkpointReader.getAllMissions())
}
