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
package com.bytelegend.app.client.testfixtures

/*
import com.bytelegend.app.shared.GAME_TILE_SIZE
import com.bytelegend.app.shared.GameMapTile
import com.bytelegend.app.shared.GameTileLayer
import com.bytelegend.app.shared.GameTileType
import com.bytelegend.app.shared.GameTileType.AccessibleGround
import com.bytelegend.app.shared.GridSize
import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.RawGameMap
import com.bytelegend.app.shared.TileSheetInfo
import common.widget.ResourceLoader
import js.event.EventBus
import js.event.EventListener

object MockEventBus : EventBus {
    override fun <T> on(eventType: String, listener: EventListener<T>) {
    }

    override fun <T> emit(eventType: String, event: T) {
    }

    override fun <T> remove(eventType: String, listener: EventListener<T>) {
    }
}

object MockResourceLoader : ResourceLoader {
    override fun start() {
    }

    override fun <T> getLoadedResource(id: String): T {
        throw UnsupportedOperationException()
    }
}

fun mockGameTile(
    type: GameTileType = AccessibleGround,
    sheetId: String = "mockSheet",
    pixelBlock: PixelBlock = PixelBlock(0, 0, 0, 0)
) = mockLayeredGameTile(type, sheetId, listOf(pixelBlock))

fun mockLayeredGameTile(
    type: GameTileType = AccessibleGround,
    sheetId: String = "mockSheet",
    layers: List<PixelBlock>
) = GameMapTile().apply {
    this.type = type.toString()
    this.layers = layers.map { GameTileLayer(sheetId, it) }.toTypedArray()
}

fun mockGameMap(
    id: String = "mockMap",
    tileSize: PixelSize = GAME_TILE_SIZE,
    tiles: Array<Array<GameMapTile>> = emptyArray()
) = RawGameMap().apply {
    this.id = id
    this.tileSize = tileSize
    this.tiles = tiles
}

fun mockTileSheetInfo() = TileSheetInfo("mockSheet", 1, GAME_TILE_SIZE, GridSize(1, 1))
*/
