package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.Timestamp
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.GridSize
import com.bytelegend.app.shared.HumanReadableCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import org.w3c.dom.events.Event
import org.w3c.dom.events.MouseEvent

// Event emitted upon window.requestAnimationFrame, usually 60~120 Hz
const val GAME_ANIMATION_EVENT = "game.animation"

/**
 * Clock event for game logic to update. It's usually less frequent than animation event, because:
 * - Most game logic doesn't need 60Hz, this is a waste of CPU.
 * - Animation events **might** be stopped when browser tab becomes inactive.
 * - The clock event is emitted with a stabler frequency.
 */
const val GAME_CLOCK_10HZ_EVENT = "game.clock.10hz"
const val GAME_CLOCK_50HZ_EVENT = "game.clock.50hz"

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

fun Game.toGridCoordinate(mouseEvent: Event): GridCoordinate {
    val event = mouseEvent.asDynamic().nativeEvent as MouseEvent
    return GridCoordinate(event.offsetX.toInt() / activeScene.map.tileSize.width, event.offsetY.toInt() / activeScene.map.tileSize.height)
}

fun Game.toGameMouseEvent(event: Event): GameMouseEvent {
    val mouseEvent = event.asDynamic().nativeEvent as MouseEvent
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
