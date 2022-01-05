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
@file:Suppress("UNCHECKED_CAST")

package com.bytelegend.app.testfixtures

import com.bytelegend.app.shared.CompressedGameMap
import com.bytelegend.app.shared.GameMap
import com.bytelegend.app.shared.objects.GameMapObject
import kotlinx.serialization.json.Json
import java.io.File

class GameObjectContainerForTest(val rrbd: File) {
    constructor(rrbd: String) : this(File(rrbd))

    private val maps: MutableMap<String, GameMap> = mutableMapOf()

    fun <T : GameMapObject> getById(mapId: String, objId: String): T {
        val gameMap = maps.getOrPut(mapId) {
            rrbd.resolve("map/$mapId/map.json").readAsGameMap()
        }
        return gameMap.objects.first { it.id == objId } as T
    }

    private fun File.readAsGameMap(): GameMap {
        return Json.decodeFromString(CompressedGameMap.serializer(), readText()).decompress()
    }
}
