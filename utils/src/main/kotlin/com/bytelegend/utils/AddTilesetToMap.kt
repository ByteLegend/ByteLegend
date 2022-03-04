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
import com.bytelegend.github.utils.generated.TiledTileset
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

/**
 * Add a tileset layer to either `DynamicSprites` or `Animations` to a map
 */
fun main() {
    val tilesetName = System.getProperty("tilesetName")!!
    val mapId = System.getProperty("mapId")!!
    val groupType = SpecialMapGroup.valueOf(System.getProperty("groupType")!!)

    val mapJson = File("resources/raw/maps/$mapId/$mapId.json")
    val mapData = uglyObjectMapper.readValue<TiledMap>(mapJson)
    val tilesetSource = "../../${groupType.tilesetJsonDir}/$tilesetName.json"
    val tileset = readTileset(mapJson, tilesetSource)
    if (mapData.tilesets.none { it.source == tilesetSource }) {
        val lastExistingTileset = mapData.tilesets.lastOrNull()
        val firstGidOfTilesetToAdd = if (lastExistingTileset == null) 1 else lastExistingTileset.firstgid + readTileset(mapJson, lastExistingTileset.source).tilecount
        mapData.tilesets.add(TiledMap.Tileset(firstGidOfTilesetToAdd, tilesetSource))
    }
    val tilesetFrameGridHeight = tileset.imageheight / tileset.tileheight
    val tilesetFrameGridWidth = tileset.tiles.size / tilesetFrameGridHeight
    val tilesetFrameCount = tileset.imagewidth / tilesetFrameGridWidth / tileset.tilewidth

    val firstGidOfTileset = mapData.tilesets.first { it.source == tilesetSource }.firstgid

    val animationGroup = mapData.createOrGetGroupLayer(groupType)
    animationGroup.layers.removeIf { it.name == tilesetName }
    animationGroup.layers.add(
        TiledMap.Layer2().apply {
            data = mutableListOf()
            for (x in 0 until tilesetFrameGridWidth) {
                for (y in 0 until tilesetFrameGridHeight) {
                    data.add(firstGidOfTileset + tilesetFrameGridWidth * tilesetFrameCount * y + x)
                }
            }
            width = tilesetFrameGridWidth
            height = tilesetFrameGridHeight
            id = mapData.getNextLayerIdAndIncrement()
            name = tilesetName
            type = "tilelayer"
            opacity = 1
            visible = true
            x = 0
            y = animationGroup.layers.size.toLong()
        }
    )

    mapData.layers.forEach {
        // Avoid diffs in data/layers/objects
        if (it.data?.isEmpty() == true) {
            it.data = null
        }
        if (it.layers?.isEmpty() == true) {
            it.layers = null
        }
        if (it.objects?.isEmpty() == true) {
            it.objects = null
        }
        it.objects?.forEach {
            // https://github.com/mapeditor/tiled/issues/3234
            if (it.polygon?.isEmpty() == true) {
                it.polygon = null
            }
        }
    }
    prettyObjectMapper.writeValue(mapJson, mapData)
}

private fun TiledMap.createOrGetGroupLayer(mapGroup: SpecialMapGroup): TiledMap.Layer {
    if (layers.none { it.name == mapGroup.name }) {
        val layer = TiledMap.Layer(
            null,
            null,
            getNextLayerIdAndIncrement(),
            SpecialMapGroup.Animations.name,
            1,
            "group",
            true,
            null,
            0,
            0,
            mutableListOf<TiledMap.Layer2>(),
            "topdown",
            mutableListOf<TiledMap.Object>()
        )
        layers.add(layer)
        return layer
    } else {
        return layers.first { it.name == mapGroup.name }
    }
}

private fun readTileset(mapJson: File, source: String): TiledTileset {
    return uglyObjectMapper.readValue(mapJson.parentFile.resolve(source))
}

private fun TiledMap.getNextLayerIdAndIncrement(): Long {
    return nextlayerid++
}
