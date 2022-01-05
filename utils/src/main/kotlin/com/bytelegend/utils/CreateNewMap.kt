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

fun main() {
    val rootProjectDir = File(".")
    val mapId = System.getProperty("mapId")
    val mapGridWidth = System.getProperty("mapGridWidth").toLong()
    val mapGridHeight = System.getProperty("mapGridHeight").toLong()

    rootProjectDir.resolve("client/game-$mapId/src/main/kotlin").mkdirs()
    rootProjectDir.resolve("client/game-$mapId/src/main/kotlin/$mapId.kt").writeText(
        """
import com.bytelegend.app.client.api.GameRuntime
import kotlinx.browser.window

val gameRuntime = window.asDynamic().gameRuntime.unsafeCast<GameRuntime>()

fun main() {
    gameRuntime.sceneContainer.getSceneById("$mapId").apply {
        objects {
        }
    }
}
"""
    )

    rootProjectDir.resolve("client/game-$mapId/game-$mapId.gradle.kts").writeText(
        """
plugins {
    id("configure-kotlin-js")
}
"""
    )

    rootProjectDir.resolve("game-data/$mapId/missions").mkdirs()
    rootProjectDir.resolve("game-data/$mapId/missions/.placeholder").createNewFile()
    rootProjectDir.resolve("game-data/$mapId/i18n.yml").createNewFile()

    val tiledMap = TiledMap()
    tiledMap.compressionlevel = -1
    tiledMap.width = mapGridWidth
    tiledMap.height = mapGridHeight
    tiledMap.infinite = false
    tiledMap.layers = emptyList()
    tiledMap.nextlayerid = 3
    tiledMap.nextobjectid = 1
    tiledMap.orientation = "orthogonal"
    tiledMap.renderorder = "right-down"
    tiledMap.tiledversion = "1.7.1"
    tiledMap.tileheight = 32
    tiledMap.tilewidth = 32
    tiledMap.tilesets = emptyList()
    tiledMap.type = "map"
    tiledMap.version = "1.6"
    tiledMap.layers = listOf(
        createLayer(1, "Player", mapGridWidth, mapGridHeight),
        createLayer(2, "Blockers", mapGridWidth, mapGridHeight)
    )

    rootProjectDir.resolve("resources/raw/maps/$mapId").mkdirs()
    rootProjectDir.resolve("resources/raw/maps/$mapId/$mapId.json").writeText(prettyObjectMapper.writeValueAsString(tiledMap))
}

private fun createLayer(id: Int, name: String, width: Long, height: Long): TiledMap.Layer {
    val ret = TiledMap.Layer()
    ret.data = List(width.toInt() * height.toInt()) { 0L }
    ret.height = height
    ret.width = width
    ret.id = id.toLong()
    ret.name = name
    ret.type = "tilelayer"
    ret.visible = true
    ret.x = 0
    ret.y = 0
    return ret
}
