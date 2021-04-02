package com.bytelegend.utils

import java.io.File

/**
 * Merge all YAMLs in `game-data` map directory to checkpoints-all.json for backend.
 *
 * Arguments:
 * 1. Input dir: ./game-data
 * 2. Output json: RRBD/map/checkpoints-all.json
 */
fun main(args: Array<String>) {
    val mapIdToCheckpointDir = File(args[0]).listFiles()
        .filter { it.isDirectory }
        .map { it.name to it.resolve("checkpoints") }
        .toMap()
    val checkpointReader = CheckpointDataReader(mapIdToCheckpointDir)
    objectMapper.writeValue(File(args[1]), checkpointReader.getAllCheckpoints())
}
