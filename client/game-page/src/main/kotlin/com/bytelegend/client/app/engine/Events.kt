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
package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.Timestamp
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.GridSize
import com.bytelegend.app.shared.HumanReadableCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import org.w3c.dom.Element
import react.dom.events.NativeMouseEvent

// Event emitted upon window.requestAnimationFrame, usually 60~120 Hz
const val GAME_ANIMATION_EVENT = "game.animation"

/**
 * Clock event for game logic to update. It's usually less frequent than animation event, because:
 * - Most game logic doesn't need 60Hz, this is a waste of CPU.
 * - Animation events **might** be stopped when browser tab becomes inactive.
 * - The clock event is emitted with a stabler frequency.
 */
const val GAME_CLOCK_100MS_EVENT = "game.clock.100ms"
const val GAME_CLOCK_20MS_EVENT = "game.clock.20ms"
const val GAME_CLOCK_1S_EVENT = "game.clock.1s"
const val GAME_CLOCK_60S_EVENT = "game.clock.60s"

// Most of the UI elements depends on properties of GameCanvasState
// Update all UI elements after resizing, or other "update all UI elements" operations.
const val GAME_UI_UPDATE_EVENT = "game.ui.update"
const val GAME_SCRIPT_NEXT = "game.script.next"

const val MAP_SCROLL_EVENT = "map.scroll"

typealias GameAnimationEventListener = (Timestamp) -> Unit
typealias MouseEventListener = (GameMouseEvent) -> Unit

const val MOUSE_MOVE_EVENT = "mouse.move"
const val MOUSE_CLICK_EVENT = "mouse.click"
const val MOUSE_OUT_OF_MAP_EVENT = "mouse.out.of.map"

data class GameMouseEvent(
    val canvasCoordinate: PixelCoordinate,
    val mapCoordinate: GridCoordinate,
    val humanReadableCoordinate: HumanReadableCoordinate
)

data class GameAnimationEvent(
    val lastAnimationTime: Timestamp,
    val lastAnimationCanvasGridSize: GridSize,
    val lastAnimationCanvasPixelSize: PixelSize,
    val lastAnimationCanvasCoordinateInMap: PixelCoordinate,
)

fun <T : Element, E : NativeMouseEvent> Game.toGridCoordinate(mouseEvent: react.dom.events.MouseEvent<T, E>): GridCoordinate {
    val event = mouseEvent.nativeEvent
    return GridCoordinate(event.offsetX.toInt() / activeScene.map.tileSize.width, event.offsetY.toInt() / activeScene.map.tileSize.height)
}

fun <T : Element, E : NativeMouseEvent> Game.toGameMouseEvent(event: react.dom.events.MouseEvent<T, E>): GameMouseEvent {
    val mouseEvent = event.nativeEvent
    val coordinateInCanvas = PixelCoordinate(mouseEvent.offsetX.toInt(), mouseEvent.offsetY.toInt())
    val gridCoordinateInMap = viewportCoordinateToGridCoordinate(coordinateInCanvas)

    val humanReadableCoordinate = HumanReadableCoordinate(gridCoordinateInMap)
    return GameMouseEvent(coordinateInCanvas, gridCoordinateInMap, humanReadableCoordinate)
}

fun Game.viewportCoordinateToGridCoordinate(coordinateInCanvas: PixelCoordinate): GridCoordinate {
    val canvasState = activeScene.canvasState
    val tileSize = activeScene.map.tileSize
    return GridCoordinate(
        (coordinateInCanvas.x + canvasState.getCanvasCoordinateInMap().x) / tileSize.width,
        (coordinateInCanvas.y + canvasState.getCanvasCoordinateInMap().y) / tileSize.height
    )
}
