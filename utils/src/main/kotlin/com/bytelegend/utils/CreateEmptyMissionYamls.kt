/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    val i18nYaml = rootProjectDir.resolve("game-data/$mapId/i18n.yml")
    val tiledMap: TiledMap = uglyObjectMapper.readValue(tiledMapJson.readText(), TiledMap::class.java)
    val mapDataReader = MissionDataReader(mapOf(mapId to mapMissionDir))
    val tiledObjectReader = TiledObjectReader(mapId, tiledMap, mapDataReader, emptyMap())

    val gameMapMissions: List<TiledMapObject> = tiledObjectReader.readObjects(TiledObjectType.GameMapMission)
    val i18nYamlText = i18nYaml.let { if (it.isFile) it.readText() else "" }
    val i18nTextToAppend = StringBuilder()

    gameMapMissions.forEach {
        val missionId = it.name
        val yml = mapMissionDir.resolve("$missionId.yml")
        if (!yml.exists()) {
            yml.writeText(
                """id: $missionId
title: $missionId-title"""
            )
        }
        if (!i18nYamlText.contains("$missionId-title")) {
            i18nTextToAppend.append(
                """- id: "$missionId-title"
  data:
    EN: "$missionId"
    ZH_HANS: "$missionId"
"""
            )
        }
    }

    i18nYaml.appendText(i18nTextToAppend.toString())
}
