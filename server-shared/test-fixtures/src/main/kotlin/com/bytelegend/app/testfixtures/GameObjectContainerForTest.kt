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
