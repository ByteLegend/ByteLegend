package com.bytelegend.utils

import com.bytelegend.github.utils.generated.TiledMap
import java.io.File
import com.bytelegend.github.utils.generated.TiledMap.Object as TiledMapObject

/**
 * Read missions in map, and create empty mission YAMLs for missing missions
 */
fun main() {
    val rootProjectDir = File(".")
    val mapId = System.getProperty("mapId")
    val tiledMapJson = rootProjectDir.resolve("resources/raw/maps/$mapId/$mapId.json")
    val mapMissionDir = rootProjectDir.resolve("game-data/$mapId/missions")
    val tiledMap: TiledMap = uglyObjectMapper.readValue(tiledMapJson.readText(), TiledMap::class.java)
    val mapDataReader = MissionDataReader(mapOf(mapId to mapMissionDir))
    val tiledObjectReader = TiledObjectReader(mapId, tiledMap, mapDataReader, emptyMap())

    val gameMapMissions: List<TiledMapObject> = tiledObjectReader.readObjects(TiledObjectType.GameMapMission)
    gameMapMissions.forEach {
        val missionId = it.name
        val yml = mapMissionDir.resolve("$missionId.yml")
        if (!yml.exists()) {
            yml.writeText(
                """id: $missionId
title: $missionId-title"""
            )
        }
    }
}
