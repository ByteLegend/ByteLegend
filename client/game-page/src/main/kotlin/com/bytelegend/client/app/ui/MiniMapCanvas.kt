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
@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.ImageResourceData
import com.bytelegend.app.client.api.Timestamp
import com.bytelegend.app.client.ui.bootstrap.BootstrapButton
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.Ratio
import com.bytelegend.app.shared.objects.GameMapRegion
import com.bytelegend.client.app.page.HERO_AVATAR_IMG_ID
import com.bytelegend.client.app.ui.roadmap.RoadmapModal
import kotlinx.js.jso
import kotlinx.browser.localStorage
import react.Fragment
import react.State
import react.create
import react.dom.events.MouseEvent
import react.react
import kotlin.math.PI
import kotlin.math.max

interface MiniMapState : State {
    var cursor: String

    // 0 -> fully minimized
    // 1 -> maximized
    var collapseProgress: Double

    // UP -> The minimap is rising
    // DOWN -> The minimap is collapsing
    // NONE -> minimized or maximized
    var animationDirection: Direction

    var hoveredRegion: GameMapRegion?
}

const val MINIMAP_MOUSE_MOVE_EVENT = "minimap.mousemove"
const val COLLAPSE_ANIMATION_SPEED_PERCENTAGE_PER_SECOND = 800 // 800% per second
const val MINIMAP_AVATAR_SIZE = 32

class MiniMap : AbstractMapCanvas<MiniMapState>() {
    private val miniMapWidth: Int
        get() = max(gameMap.size.width * 2, 200) + 2
    private val miniMapHeight: Int
        get() = max(gameMap.size.height * 2, 200) + 2

    // minimap frame border
    private val miniMapX: Int
        get() = uiContainerCoordinateInGameContainer.x + 8
    private val miniMapY: Int
        get() = uiContainerCoordinateInGameContainer.y + uiContainerSize.height - miniMapHeight - 8

    init {
        state = jso {
            cursor = "grab"
            collapseProgress = if (localStorage.getItem("minimapCollapsed") == "true") 0.0 else 1.0
            animationDirection = Direction.NONE
        }
    }

    private val collapseProgress: Double
        get() = if (mapCoveredByCanvas) 0.0
        else state.collapseProgress

    private var lastMouseDownMapCoordinate: PixelCoordinate? = null
    private var lastMouseDownMiniMapCoordinate: PixelCoordinate? = null

    private fun onMouseDown(mouseEvent: MouseEvent<*, *>) {
        val event = mouseEvent.nativeEvent
        setState { cursor = "grabbing" }

        lastMouseDownMiniMapCoordinate = PixelCoordinate(event.offsetX.toInt(), event.offsetY.toInt())
        lastMouseDownMapCoordinate = canvasCoordinateInMap
    }

    private fun onMouseUp(mouseEvent: MouseEvent<*, *>) {
        val event = mouseEvent.nativeEvent
        if (lastMouseDownMiniMapCoordinate != null) {
            val currentCoordinate = PixelCoordinate(event.offsetX.toInt(), event.offsetY.toInt())
            val targetPixelCoordinate = determineCanvasCoordinateInMapOnMouseMove(currentCoordinate)
            // Align to tile border
            moveTo(targetPixelCoordinate.alignToTileBorder(gameMap.tileSize))
        }
        lastMouseDownMapCoordinate = null
        lastMouseDownMiniMapCoordinate = null
        setState { cursor = "grab" }
    }

    private fun onMouseMove(mouseEvent: MouseEvent<*, *>) {
        val event = mouseEvent.nativeEvent
        val currentCoordinate = PixelCoordinate(event.offsetX.toInt(), event.offsetY.toInt())
        if (lastMouseDownMiniMapCoordinate != null) {
            if (currentCoordinate.x < 0 ||
                currentCoordinate.y < 0 ||
                currentCoordinate.x >= miniMapWidth ||
                currentCoordinate.y >= miniMapHeight
            ) {
                onMouseUp(mouseEvent)
            } else {
                moveTo(determineCanvasCoordinateInMapOnMouseMove(currentCoordinate))
            }
        }
        // No events during dragging because it's a bit expensive to update echarts options
        game.eventBus.emit(MINIMAP_MOUSE_MOVE_EVENT, event)
    }

    private fun onMouseClick(mouseEvent: MouseEvent<*, *>) {
        val event = mouseEvent.nativeEvent
        // This is the left-top corner, we want the cursor to be at the center of viewport
        val bigMapCoordinate = PixelCoordinate(event.offsetX.toInt(), event.offsetY.toInt()) / mapRatio
        moveTo(bigMapCoordinate.offset(-canvasPixelSize.width / 2, -canvasPixelSize.height / 2).alignToTileBorder(gameMap.tileSize))
    }

    private fun onMouseOutOfCanvas(mouseEvent: MouseEvent<*, *>) {
        onMouseUp(mouseEvent)
    }

    private fun determineCanvasCoordinateInMapOnMouseMove(currentCoordinateInMiniMap: PixelCoordinate): PixelCoordinate {
        val offset = currentCoordinateInMiniMap - lastMouseDownMiniMapCoordinate!!
        return lastMouseDownMapCoordinate!! + offset / mapRatio
    }

    override fun render() = Fragment.create {
        val miniMapZIndex = Layer.MiniMapCanvas.zIndex()

        // top:
        // 1 -> mapY-16
        // 0 -> mapY-16 +mapHeight
        // height:
        // 1 -> miniMapHeight + 32
        // 0 -> 32

        absoluteDiv(
            left = miniMapX - 16,
            top = (miniMapY - 16 + (1 - collapseProgress) * miniMapHeight).toInt(),
            width = miniMapWidth + 32,
            height = (miniMapHeight * collapseProgress).toInt() + 32,
            zIndex = miniMapZIndex + 1
        ) {
            if (!isMinimized()) {
                it.jsStyle {
                    backgroundImage = """url("${game.resolve("/img/ui/minimap.png")}")"""
                    backgroundSize = "100% 100%"
                    backgroundRepeat = "no-repeat"
                    backgroundPosition = "0 0px"
                }
            }

            if (isMaximized()) {
                child(EChartsMinimap::class.react, jso {
                    zIndex = miniMapZIndex + 2
                    width = 200
                    height = 200
                    top = 16
                    left = 16
                    theme = "light"
                    renderer = "svg"
                    this.game = props.game
                })
            }

            mapCanvas {
                onMouseDown = this@MiniMap::onMouseDown
                onMouseUp = this@MiniMap::onMouseUp
                onMouseMove = this@MiniMap::onMouseMove
                onMouseOut = this@MiniMap::onMouseOutOfCanvas
                onBlur = {
                    this@MiniMap.onMouseOutOfCanvas(it.unsafeCast<MouseEvent<*, *>>())
                }
                onClick = this@MiniMap::onMouseClick
                id = "minimap-canvas-layer"
                width = miniMapWidth.toDouble()
                height = miniMapHeight.toDouble()
                jsStyle {
                    if (!isMaximized()) {
                        display = "none"
                    }
                    backgroundSize = "100% 100%"
                    cursor = state.cursor
                    zIndex = miniMapZIndex + 3
                    position = "absolute"
                    top = "16px"
                    left = "16px"
                }
            }

            BootstrapButton {
                size = "sm"
                className = "round-button"
                style = jso<dynamic> {
                    zIndex = miniMapZIndex + 4
                    // 0.0 -> 32
                    // 1.0 -> miniMapWidth
                    left = "${32 + ((miniMapWidth - 32) * collapseProgress).toInt()}px"
                    // 1.0 -> 0
                    // 0.0 -> miniMapHeight - 32
                    //  (miniMapHeight - 32) * (1-state.collapseProgress)
                    top = "0px"
                }
                title = game.i("Maximize")
                onClick = {
                    game.modalController.show { modalProps ->
                        modalProps.className = "roadmap-dialog"
                        child(RoadmapModal::class.react, jso {
                            this.game = props.game
                        })
                    }
                }
                +"↗"
            }

            if (!mapCoveredByCanvas) {
                BootstrapButton {
                    size = "sm"
                    className = "round-button"
                    style = jso<dynamic> {
                        zIndex = miniMapZIndex + 4
                        left = "0"
                        top = "0px"
                        // 1.0 -> ↓ -> 0deg
                        // 0.0 -> ↑ -> -180deg
                        transform = "rotate(${(-180 * (1 - collapseProgress)).toInt()}deg)"
                    }
                    onClick = {
                        if (isMaximized()) {
                            localStorage.setItem("minimapCollapsed", "true")
                            setState {
                                animationDirection = Direction.DOWN
                            }
                        } else if (isMinimized()) {
                            localStorage.setItem("minimapCollapsed", "false")
                            setState {
                                animationDirection = Direction.UP
                            }
                        }
                    }
                    if (isMaximized()) {
                        title = game.i("Collapse")
                    } else if (isMinimized()) {
                        title = game.i("Expand")
                    }
                    +"↓"
                }
            }
        }
    }

    private fun isMinimized() = collapseProgress <= 0.0
    private fun isMaximized() = collapseProgress >= 1.0

    private fun minimizeAnimation(lastAnimationTime: Timestamp) {
        if (state.animationDirection == Direction.NONE) {
            return
        }
        val elapsedSecond = (Timestamp.now() - lastAnimationTime) / 1000.0
        val delta = elapsedSecond * COLLAPSE_ANIMATION_SPEED_PERCENTAGE_PER_SECOND / 100.0
        val nextValue = when (state.animationDirection) {
            Direction.UP -> collapseProgress + delta
            Direction.DOWN -> collapseProgress - delta
            else -> collapseProgress
        }

        when {
            nextValue < 0.0 -> setState {
                animationDirection = Direction.NONE
                collapseProgress = 0.0
            }
            nextValue > 1.0 -> setState {
                animationDirection = Direction.NONE
                collapseProgress = 1.0
            }
            else -> setState {
                collapseProgress = nextValue
            }
        }
    }

    private val mapRatio: Ratio
        get() = Ratio(1.0 * miniMapWidth / mapPixelSize.width, 1.0 * miniMapHeight / mapPixelSize.height)

    override fun onPaint(lastAnimationTime: Timestamp) {
        minimizeAnimation(lastAnimationTime)

        if (!isMaximized()) {
            return
        }

        canvas.clearRect(0.0, 0.0, miniMapWidth.toDouble() + 1, miniMapHeight.toDouble() + 1)

        drawHeroAvatarOnMinimap()
        drawViewportRect()
    }

    private fun GridCoordinate.toMiniMapPixelCoordinate() = this * gameMap.tileSize * mapRatio

    private fun drawHeroAvatarOnMinimap() {
        if (game.hero == null ||
            game._hero?.gameScene?.isActive != true ||
            resourceLoader.getLoadedResourceOrNull<ImageResourceData>(HERO_AVATAR_IMG_ID) == null
        ) {
            return
        }

        val img = resourceLoader.getLoadedResource<ImageResourceData>(HERO_AVATAR_IMG_ID)
        val miniMapCoordinate = game.hero!!.gridCoordinate.toMiniMapPixelCoordinate()

        canvas.save()

        canvas.beginPath()
        canvas.arc(
            miniMapCoordinate.x.toDouble(),
            miniMapCoordinate.y.toDouble(),
            MINIMAP_AVATAR_SIZE / 2.0,
            0.0, PI * 2
        )
        canvas.clip()
        canvas.drawImage(
            HERO_AVATAR_IMG_ID,
            0, 0, img.size.width, img.size.height,
            miniMapCoordinate.x - MINIMAP_AVATAR_SIZE / 2, miniMapCoordinate.y - MINIMAP_AVATAR_SIZE / 2,
            MINIMAP_AVATAR_SIZE, MINIMAP_AVATAR_SIZE
        )
        canvas.restore()
    }

    private fun drawViewportRect() {
        canvas.save()
        // canvasPixelXInMap / mapPixelWidth = viewportXInMiniMap / miniMapWidth
        // canvasPixelYInMap / mapPixelHeight = viewportYInMiniMap / miniMapHeight
        val viewportCoordinateInMiniMap = canvasCoordinateInMap * mapRatio

        // viewportWidth / miniMapWidth = canvasPixelWidth / mapPixelWidth
        // viewportHeight / miniMapHeight = canvasPixelWidth / mapPixelWidth
        val viewportSize = canvasPixelSize * mapRatio

        canvas.beginPath()
        canvas.strokeStyle = "#FF0000"
        canvas.lineWidth = 2.0
        // To avoid border out of canvas
        canvas.rect(
            viewportCoordinateInMiniMap.x + 1.0,
            viewportCoordinateInMiniMap.y + 1.0,
            viewportSize.width - 1.0,
            viewportSize.height - 1.0
        )
        canvas.stroke()
        canvas.restore()
    }
}
