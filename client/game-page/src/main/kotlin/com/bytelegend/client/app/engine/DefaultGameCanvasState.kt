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

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.GameCanvasState
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameSceneContainer
import com.bytelegend.app.client.api.Timestamp
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.GameMap
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.GridSize
import com.bytelegend.app.shared.HumanReadableCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.math.adjustCanvasCoordinateIfNecessary
import com.bytelegend.app.shared.math.calculateCanvasCoordinateInMapFromCenterPoint
import com.bytelegend.app.shared.math.limitIn
import com.bytelegend.app.shared.objects.GameMapPoint
import kotlinx.browser.window
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.pow

const val INIT_SCROLL_SPEED_PIXEL_PER_SECOND = 200
const val SCROLL_SPEED_INC_INTERVAL_MS = 200
const val SCROLL_SPEED_INC_COEFFICIENT = 1.001
const val MAX_SCROLL_SPEED_PIXEL_PER_SECOND = 1000

// The UI container needs at least 100px to display
// If this can't be fulfilled, canvas will be used as UI container
const val MIN_UI_CONTAINER_WIDTH = 100

// The fixed gap between game container and UI container
const val GAME_CONTAINER_UI_CONTAINER_GAP = 32

class DefaultGameCanvasState(
    override val di: DI,
    private val gameMap: GameMap,
    initContainerSize: PixelSize
) : GameCanvasState, DIAware, GameMap by gameMap {
    private val sceneContainer: GameSceneContainer by di.instance()
    private val eventBus: EventBus by di.instance()
    private val game: Game by lazy {
        val gameRuntime: GameRuntime by di.instance()
        gameRuntime.unsafeCast<Game>()
    }
    private val isActiveScene: Boolean
        get() = sceneContainer.activeScene?.map?.id == gameMap.id

    private val gameMapGridSize = gameMap.size
    override var gameContainerSize: PixelSize = initContainerSize
        set(newContainerSize) {
            field = newContainerSize
            onResizeGameContainer(newContainerSize)
        }
    override val mapCoveredByCanvas: Boolean
        get() = gameMap.pixelSize.width <= canvasPixelSize.width &&
            gameMap.pixelSize.height <= canvasPixelSize.height

    lateinit var canvasGridSize: GridSize
    lateinit var canvasPixelSize: PixelSize
    lateinit var canvasCoordinateInMap: PixelCoordinate
    lateinit var uiContainerSize: PixelSize
    lateinit var uiContainerCoordinateInGameContainer: PixelCoordinate

    init {
        /**
         * Calculate the initial canvas coordinate in map, based on:
         * The anchor of current URL: bytelegend.com/#A13;
         * The logged-in user's location as center point;
         * If user is anonymous, determine from map predefined location ("XInitCenterPoint")
         */
        val hash = window.location.hash.substringAfter("#")
        val center = when {
            hash.matches("[A-Z]+\\d+".toRegex()) ->
                HumanReadableCoordinate(hash.replace("\\d+".toRegex(), ""), hash.replace("[A-Z]+".toRegex(), "").toInt())
                    .toGridCoordinate()
            !game.heroPlayer.isAnonymous && game.heroPlayer.map == gameMap.id -> GridCoordinate(game.heroPlayer.x, game.heroPlayer.y)
            else -> getDefaultMapCenterPoint()
        }

        onResizeGameContainer(gameContainerSize, center)
    }

    private fun onResizeGameContainer(newContainerSize: PixelSize, initMapCenterPoint: GridCoordinate? = null) {
        logger.debug("Resizing game container: $newContainerSize")
        canvasGridSize = calculateCanvasGridSize(newContainerSize)
        canvasPixelSize = calculateCanvasSize(canvasGridSize)
        canvasCoordinateInMap = adjustCanvasCoordinateIfNecessary(gameMap.pixelSize, tileSize, calculateCanvasCoordinateInMap(initMapCenterPoint), canvasPixelSize)
        canvasCoordinateInGameContainer = calculateCanvasCoordinateInGameContainer(newContainerSize, canvasPixelSize)
        uiContainerSize = calculateUIContainerSize(gameContainerSize, canvasPixelSize)
        uiContainerCoordinateInGameContainer = calculateUIContainerCoordinateInGameContainer(gameContainerSize, uiContainerSize)
        eventBus.emit(GAME_UI_UPDATE_EVENT, Timestamp.now())
        logger.debug("After resizing, canvasCoordinateInMap: $canvasCoordinateInMap canvasCoordinateInGameContainer: $canvasCoordinateInGameContainer")
    }

    private fun calculateUIContainerSize(gameContainerSize: PixelSize, canvasPixelSize: PixelSize): PixelSize {
        val maxCanvasHeight = gameContainerSize.height - 2 * MIN_UI_CONTAINER_WIDTH
        val uiContainerHeight =
            if (gameMap.pixelSize.height > maxCanvasHeight) canvasPixelSize.height
            else gameContainerSize.height - 2 * GAME_CONTAINER_UI_CONTAINER_GAP

        val maxCanvasWidth = gameContainerSize.width - 2 * MIN_UI_CONTAINER_WIDTH
        val uiContainerWidth =
            if (gameMap.pixelSize.width > maxCanvasWidth) canvasPixelSize.width
            else gameContainerSize.width - 2 * GAME_CONTAINER_UI_CONTAINER_GAP

        return PixelSize(uiContainerWidth, uiContainerHeight)
    }

    private fun calculateUIContainerCoordinateInGameContainer(gameContainerSize: PixelSize, uiContainerSize: PixelSize): PixelCoordinate {
        return PixelCoordinate(
            (gameContainerSize.width - uiContainerSize.width) / 2,
            (gameContainerSize.height - uiContainerSize.height) / 2
        )
    }

    private fun getDefaultMapCenterPoint(): GridCoordinate {
        return gameMap.objects.firstOrNull {
            it.id == "${gameMap.id}InitCenterPoint"
        }?.unsafeCast<GameMapPoint>()?.gridCoordinate ?: GridCoordinate(gameMap.size.width / 2, gameMap.size.height / 2)
    }

    private fun calculateCanvasCoordinateInMap(initMapCenterPoint: GridCoordinate?): PixelCoordinate {
        return if (initMapCenterPoint == null) {
            canvasCoordinateInMap
        } else {
            calculateCanvasCoordinateInMapFromCenterPoint(gameMap.tileSize, initMapCenterPoint, canvasPixelSize)
        }
    }

    var canvasCoordinateInGameContainer: PixelCoordinate = calculateCanvasCoordinateInGameContainer(gameContainerSize, canvasPixelSize)

    override fun getCanvasCoordinateInGameContainer() = canvasCoordinateInGameContainer
    override fun getUICoordinateInGameContainer() = uiContainerCoordinateInGameContainer

    override fun getCanvasCoordinateInMap() = canvasCoordinateInMap
    override fun getCanvasGridCoordinateInMap() = canvasCoordinateInMap / tileSize

    override fun getCanvasPixelSize() = canvasPixelSize
    override fun getUIContainerSize() = uiContainerSize

    override fun getCanvasGridSize() = canvasGridSize
    override fun moveTo(coordinate: PixelCoordinate) {
        stopScrolling()
        val oldCanvasCoordinateInMap = canvasCoordinateInMap
        canvasCoordinateInMap = adjustCanvasCoordinateIfNecessary(gameMap.pixelSize, tileSize, coordinate, canvasPixelSize)
        logCanvasCoordinateInMapIfNecessary(oldCanvasCoordinateInMap)
    }

    private fun calculateCanvasGridSize(gameContainerSize: PixelSize): GridSize {
        val width = min((gameContainerSize.width / tileSize.width) - 1, gameMapGridSize.width)
        val height = min((gameContainerSize.height / tileSize.height) - 1, gameMapGridSize.height)
        return GridSize(width, height)
    }

    private var scrollSpeedPixelPerSecond: Int = 0

    /**
     * Upon map.scroll stop event, don't stop immediately, because the viewport must stop at the integer multiple of tile
     * So we set this "expectedDirection" and continue scrolling until we reach the tile border
     */
    private var expectedScrollDirection = Direction.NONE
    private var scrollDirection: Direction = Direction.NONE

    /**
     * Last time when user start scrolling. This time is used for calculating scrolling speed
     */
    private var lastScrollStartTime: Timestamp? = null
    private var lastScrollStartDirection: Direction? = null
    private var lastScrollStartCoordinateInMap: PixelCoordinate? = null

    init {
        // The init coordinate might not be exactly suitable for the map/canvas, so we adjust it again
        gameContainerSize = gameContainerSize
        eventBus.on<Direction>(MAP_SCROLL_EVENT) {
            if (isActiveScene) {
                expectedScrollDirection = it
            }
        }
    }

    /**
     * Upon window.requestAnimationFrame, update the state.
     */
    fun onAnimate() {
        if (!isActiveScene) {
            expectedScrollDirection = Direction.NONE
        }

        val oldCanvasCoordinateInMap = canvasCoordinateInMap

        if (scrollDirection == Direction.NONE) {
            if (expectedScrollDirection != Direction.NONE) {
                // still -> scrolling
                scrollDirection = expectedScrollDirection
                scrollSpeedPixelPerSecond = INIT_SCROLL_SPEED_PIXEL_PER_SECOND
                lastScrollStartTime = Timestamp.now()
                lastScrollStartDirection = expectedScrollDirection
                lastScrollStartCoordinateInMap = canvasCoordinateInMap

                require(atTileBorder(tileSize, canvasCoordinateInMap)) { "Not at tile border: $canvasCoordinateInMap" }
            }
        } else {
            val elapsedMsSinceScrollingStart = Timestamp.now() - lastScrollStartTime!!
            scrollSpeedPixelPerSecond = calculateScrollingSpeed(elapsedMsSinceScrollingStart)
            val distancePixelSinceScrollStart = (elapsedMsSinceScrollingStart * scrollSpeedPixelPerSecond / 1000.0).toInt()

            if (expectedScrollDirection == Direction.NONE) {
                val nextCoordinate = nextCoordinate(scrollDirection, distancePixelSinceScrollStart)

                // scrolling has been stopped, but let's keep moving to tile border then stop
                when {
                    atTileBorder(tileSize, canvasCoordinateInMap) -> {
                        // e.g. hit the border of tile
                        stopScrolling()
                    }
                    sameTile(tileSize, canvasCoordinateInMap, nextCoordinate) -> {
                        canvasCoordinateInMap = nextCoordinate
                    }
                    else -> {
                        // Let's move to the border and stop
                        canvasCoordinateInMap = nextTileBorder(scrollDirection, nextCoordinate, tileSize)
                        stopScrolling()
                    }
                }
            } else {
                if (expectedScrollDirection == scrollDirection) {
                    // scrolling not stop, direction not change, keep scrolling
                    canvasCoordinateInMap = nextCoordinate(scrollDirection, distancePixelSinceScrollStart)
                } else {
                    // direction changed, move to next border and reset lastScrollStartTime and coordinate
                    val nextCoordinate = nextCoordinate(scrollDirection, distancePixelSinceScrollStart)

                    when {
                        atTileBorder(tileSize, canvasCoordinateInMap) -> {
                            // e.g. hit the border of tile
                            changeDirectionOnTheFly()
                        }
                        sameTile(tileSize, canvasCoordinateInMap, nextCoordinate) -> {
                            canvasCoordinateInMap = nextCoordinate
                        }
                        else -> {
                            // Let's move to the border and change direction
                            canvasCoordinateInMap = nextTileBorder(scrollDirection, nextCoordinate, tileSize)
                            changeDirectionOnTheFly()
                        }
                    }
                }
            }
        }

        logCanvasCoordinateInMapIfNecessary(oldCanvasCoordinateInMap)
    }

    private fun logCanvasCoordinateInMapIfNecessary(oldCanvasCoordinateInMap: PixelCoordinate) {
        if (logger.debugEnabled &&
            canvasCoordinateInMap != oldCanvasCoordinateInMap &&
            atTileBorder(gameMap.tileSize, canvasCoordinateInMap)
        ) {
            // Don't remove this log because it's used in browser test to locate the canvas
            BrowserConsoleLogger.debug(
                """canvasCoordinateInMap: ${getCanvasCoordinateInMap().x},${getCanvasCoordinateInMap().y}
canvasPixelSize: ${getCanvasPixelSize().width},${getCanvasPixelSize().height}
canvasCoordinateInGameContainer: ${getCanvasCoordinateInGameContainer().x},${getCanvasCoordinateInGameContainer().y}
uiCoordinateInGameContainer: ${getUICoordinateInGameContainer().x},${getUICoordinateInGameContainer().y}
uiContainerSize: ${getUIContainerSize().width},${getUIContainerSize().height}
"""
            )
        }
    }

    private fun stopScrolling() {
        scrollDirection = Direction.NONE
        lastScrollStartCoordinateInMap = null
        lastScrollStartDirection = null
        lastScrollStartTime = null
        scrollSpeedPixelPerSecond = 0
    }

    private fun changeDirectionOnTheFly() {
        scrollDirection = expectedScrollDirection
        lastScrollStartCoordinateInMap = canvasCoordinateInMap
        lastScrollStartTime = Timestamp.now()
    }

    private fun calculateScrollingSpeed(elapsedMsSinceScrollingStart: Long): Int {
        val speedIncCoefficient = SCROLL_SPEED_INC_COEFFICIENT.pow(1.0 * elapsedMsSinceScrollingStart / SCROLL_SPEED_INC_INTERVAL_MS)
        val ret = (scrollSpeedPixelPerSecond * speedIncCoefficient).toInt()
        return if (ret > MAX_SCROLL_SPEED_PIXEL_PER_SECOND) {
            MAX_SCROLL_SPEED_PIXEL_PER_SECOND
        } else {
            ret
        }
    }

    private fun nextCoordinate(direction: Direction, distancePixel: Int) = when (direction) {
        Direction.UP -> offset(lastScrollStartCoordinateInMap!!, 0, -distancePixel)
        Direction.DOWN -> offset(lastScrollStartCoordinateInMap!!, 0, distancePixel)
        Direction.LEFT -> offset(lastScrollStartCoordinateInMap!!, -distancePixel, 0)
        Direction.RIGHT -> offset(lastScrollStartCoordinateInMap!!, distancePixel, 0)
        Direction.LEFT_UP -> offset(lastScrollStartCoordinateInMap!!, -distancePixel, -distancePixel)
        Direction.RIGHT_UP -> offset(lastScrollStartCoordinateInMap!!, distancePixel, -distancePixel)
        Direction.LEFT_DOWN -> offset(lastScrollStartCoordinateInMap!!, -distancePixel, distancePixel)
        Direction.RIGHT_DOWN -> offset(lastScrollStartCoordinateInMap!!, distancePixel, distancePixel)
        else -> throw Exception("Should not be here")
    }

    private fun offset(coordinate: PixelCoordinate, offsetX: Int, offsetY: Int): PixelCoordinate {
        val mapPixelSize = gameMap.pixelSize
        val x = limitIn(coordinate.x + offsetX, mapPixelSize.width - canvasPixelSize.width)
        val y = limitIn(coordinate.y + offsetY, mapPixelSize.height - canvasPixelSize.height)
        return PixelCoordinate(x, y)
    }

    private fun calculateCanvasSize(gridSize: GridSize) = PixelSize(gridSize.width * tileSize.width, gridSize.height * tileSize.height)

    private fun calculateCanvasCoordinateInGameContainer(gameContainerSize: PixelSize, viewportSize: PixelSize): PixelCoordinate =
        PixelCoordinate((gameContainerSize.width - viewportSize.width) / 2, (gameContainerSize.height - viewportSize.height) / 2)
}

private fun PixelSize.normalizeX(x: Int) = (x / width) * width
private fun PixelSize.normalizeY(x: Int) = (x / height) * height

private fun nextTileBorder(direction: Direction, nextCoordinate: PixelCoordinate, tileSize: PixelSize) = when (direction) {
    /*
     -- nextCoordinate ----
     -- border ------------
     -- currentCoordinate--
     */
    Direction.UP -> PixelCoordinate(tileSize.normalizeX(nextCoordinate.x), ceil(1.0 * nextCoordinate.y / tileSize.height).toInt() * tileSize.height)
    /*
     -- currentCoordinate -
     -- border ------------
     -- nextCoordinate ----
     */
    Direction.DOWN -> PixelCoordinate(tileSize.normalizeX(nextCoordinate.x), floor(1.0 * nextCoordinate.y / tileSize.height).toInt() * tileSize.height)
    Direction.LEFT -> PixelCoordinate(ceil(1.0 * nextCoordinate.x / tileSize.width).toInt() * tileSize.width, tileSize.normalizeY(nextCoordinate.y))
    /*
            |                   |           |
     currentCoordinate        border     nextCoordinate
     */
    Direction.RIGHT -> PixelCoordinate(floor(1.0 * nextCoordinate.x / tileSize.width).toInt() * tileSize.width, tileSize.normalizeY(nextCoordinate.y))
    Direction.LEFT_UP -> PixelCoordinate(ceil(1.0 * nextCoordinate.x / tileSize.width).toInt() * tileSize.width, ceil(1.0 * nextCoordinate.y / tileSize.height).toInt() * tileSize.height)
    Direction.RIGHT_UP -> PixelCoordinate(floor(1.0 * nextCoordinate.x / tileSize.width).toInt() * tileSize.width, ceil(1.0 * nextCoordinate.y / tileSize.height).toInt() * tileSize.height)
    Direction.LEFT_DOWN -> PixelCoordinate(ceil(1.0 * nextCoordinate.x / tileSize.width).toInt() * tileSize.width, floor(1.0 * nextCoordinate.y / tileSize.height).toInt() * tileSize.height)
    Direction.RIGHT_DOWN -> PixelCoordinate(floor(1.0 * nextCoordinate.x / tileSize.width).toInt() * tileSize.width, floor(1.0 * nextCoordinate.y / tileSize.height).toInt() * tileSize.height)
    else -> throw Exception("Should not be here")
}.apply {
    require(atTileBorder(tileSize, this)) { "Not at tile border: $this" }
}

private fun sameTile(tileSize: PixelSize, coordinate1: PixelCoordinate, coordinate2: PixelCoordinate) =
    coordinate1.x / tileSize.width == coordinate2.x / tileSize.width && coordinate1.y / tileSize.height == coordinate2.y / tileSize.height

fun atTileBorder(tileSize: PixelSize, coordinate: PixelCoordinate) = coordinate.x % tileSize.width == 0 && coordinate.y % tileSize.height == 0
