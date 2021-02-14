@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.ImageResourceData
import com.bytelegend.app.client.api.Timestamp
import com.bytelegend.app.client.api.getImageElement
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.Ratio
import com.bytelegend.app.shared.objects.GameMapRegion
import com.bytelegend.app.shared.objects.GameObjectRole.MapRegion
import com.bytelegend.client.app.page.HERO_AVATAR_IMG_ID
import com.bytelegend.client.app.sprite.disableShadow
import com.bytelegend.client.app.sprite.quadraticCurveTo
import com.bytelegend.client.app.sprite.setShadow
import common.ui.bootstrap.BootstrapButton
import common.ui.bootstrap.BootstrapModalBody
import common.ui.bootstrap.BootstrapModalHeader
import common.ui.bootstrap.BootstrapModalTitle
import kotlinx.html.id
import kotlinx.html.js.onBlurFunction
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onMouseDownFunction
import kotlinx.html.js.onMouseMoveFunction
import kotlinx.html.js.onMouseOutFunction
import kotlinx.html.js.onMouseUpFunction
import org.w3c.dom.CENTER
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.CanvasTextAlign
import org.w3c.dom.CanvasTextBaseline
import org.w3c.dom.MIDDLE
import org.w3c.dom.events.Event
import org.w3c.dom.events.MouseEvent
import react.RBuilder
import react.RState
import react.dom.jsStyle
import react.setState
import kotlin.math.PI

interface MiniMapCanvasState : RState {
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

const val COLLAPSE_ANIMATION_SPEED_PERCENTAGE_PER_SECOND = 800 // 800% per second
const val MINIMAP_AVATAR_SIZE = 16

class MiniMapCanvasLayer : AbstractMapCanvas<MiniMapCanvasState>() {
    private val miniMapWidth: Int by lazy {
        gameMap.size.width * 2 + 2
    }
    private val miniMapHeight: Int by lazy {
        gameMap.size.height * 2 + 2
    }

    // minimap frame border
    private val miniMapX: Int
        get() = uiContainerCoordinateInGameContainer.x + 8
    private val miniMapY: Int
        get() = uiContainerCoordinateInGameContainer.y + uiContainerSize.height - miniMapHeight - 8

    override fun MiniMapCanvasState.init() {
        cursor = "grab"
        collapseProgress = 1.0
        animationDirection = Direction.NONE
    }

    private val collapseProgress: Double
        get() = if (mapCoveredByCanvas) 0.0
        else state.collapseProgress

    private var lastMouseDownMapCoordinate: PixelCoordinate? = null
    private var lastMouseDownMiniMapCoordinate: PixelCoordinate? = null

    private fun onMouseDown(mouseEvent: Event) {
        val event = mouseEvent.asDynamic().nativeEvent as MouseEvent
        setState { cursor = "grabbing" }

        lastMouseDownMiniMapCoordinate = PixelCoordinate(event.offsetX.toInt(), event.offsetY.toInt())
        lastMouseDownMapCoordinate = canvasCoordinateInMap
    }

    private fun onMouseUp(mouseEvent: Event) {
        val event = mouseEvent.asDynamic().nativeEvent as MouseEvent
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

    private fun onMouseMove(mouseEvent: Event) {
        val event = mouseEvent.asDynamic().nativeEvent as MouseEvent
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
        } else {
            getRegions().forEach {
                if (it.center.toMiniMapPixelCoordinate().manhattanDistanceTo(currentCoordinate) < 10) {
                    setState {
                        hoveredRegion = it
                    }
                    return
                }
            }

            setState {
                hoveredRegion = null
            }
        }
    }

    private fun onMouseClick(mouseEvent: Event) {
        val event = mouseEvent.asDynamic().nativeEvent as MouseEvent
        // This is the left-top corner, we want the cursor to be at the center of viewport
        val bigMapCoordinate = PixelCoordinate(event.offsetX.toInt(), event.offsetY.toInt()) / mapRatio
        moveTo(bigMapCoordinate.offset(-canvasPixelSize.width / 2, -canvasPixelSize.height / 2).alignToTileBorder(gameMap.tileSize))
    }

    private fun onMouseOutOfCanvas(mouseEvent: Event) {
        onMouseUp(mouseEvent)
    }

    private fun determineCanvasCoordinateInMapOnMouseMove(currentCoordinateInMiniMap: PixelCoordinate): PixelCoordinate {
        val offset = currentCoordinateInMiniMap - lastMouseDownMiniMapCoordinate!!
        return lastMouseDownMapCoordinate!! + offset / mapRatio
    }

    override fun RBuilder.render() {
        val miniMapZIndex = Layer.MiniMapCanvas.zIndex()

        // top:
        // 1 -> mapY-16
        // 0 -> mapY-16 +mapHeight
        // height:
        // 1 -> miniMapHeight + 32
        // 0 -> 32

        absoluteDiv(
            miniMapX - 16,
            (miniMapY - 16 + (1 - collapseProgress) * miniMapHeight).toInt(),
            miniMapWidth + 32,
            (miniMapHeight * collapseProgress).toInt() + 32,
            miniMapZIndex + 1
        ) {
            if (!isMinimized()) {
                attrs.jsStyle {
                    backgroundImage = """url("${game.resolve("/img/ui/minimap.png")}")"""
                    backgroundSize = "100% 100%"
                    backgroundRepeat = "no-repeat"
                    backgroundPosition = "0 0px"
                }
            }

            mapCanvas {
                attrs {
                    onMouseDownFunction = this@MiniMapCanvasLayer::onMouseDown
                    onMouseUpFunction = this@MiniMapCanvasLayer::onMouseUp
                    onMouseMoveFunction = this@MiniMapCanvasLayer::onMouseMove
                    onMouseOutFunction = this@MiniMapCanvasLayer::onMouseOutOfCanvas
                    onBlurFunction = this@MiniMapCanvasLayer::onMouseOutOfCanvas
                    onClickFunction = this@MiniMapCanvasLayer::onMouseClick
                    id = "minimap-canvas-layer"
                    width = miniMapWidth.toString()
                    height = miniMapHeight.toString()
                    jsStyle {
                        if (!isMaximized()) {
                            display = "none"
                        }
                        cursor = state.cursor
                        zIndex = miniMapZIndex
                        position = "absolute"
                        top = "16px"
                        left = "16px"
                    }
                }
            }

            BootstrapButton {
                attrs.size = "sm"
                attrs.style = kotlinext.js.js {
                    zIndex = miniMapZIndex + 1
                    borderRadius = "50% 50%"
                    position = "absolute"
                    // 0.0 -> 32
                    // 1.0 -> miniMapWidth
                    left = "${32 + ((miniMapWidth - 32) * collapseProgress).toInt()}px"
                    // 1.0 -> 0
                    // 0.0 -> miniMapHeight - 32
                    //  (miniMapHeight - 32) * (1-state.collapseProgress)
                    top = "0px"
                    boxShadow = "0 0 0 0.1rem rgba(255,255,255,.5)"
                }
                attrs.title = game.i("Maximize")
                attrs.onClick = {
                    game.modalController.show {
                        BootstrapModalHeader {
                            attrs.closeButton = true
                            BootstrapModalTitle {
                                +game.i("MyAchievement")
                            }
                        }

                        BootstrapModalBody {
                            +"TODO"
                        }
                    }
                }
                +"↗"
            }

            if (!mapCoveredByCanvas) {
                BootstrapButton {
                    attrs.size = "sm"
                    attrs.style = kotlinext.js.js {
                        zIndex = miniMapZIndex + 2
                        borderRadius = "50% 50%"
                        position = "absolute"
                        left = "0"
                        top = "0px"
                        // 1.0 -> ↓ -> 0deg
                        // 0.0 -> ↑ -> -180deg
                        transform = "rotate(${(-180 * (1 - collapseProgress)).toInt()}deg)"
                        boxShadow = "0 0 0 0.1rem rgba(255,255,255,.5)"
                    }
                    attrs.onClick = {
                        if (isMaximized()) {
                            setState {
                                animationDirection = Direction.DOWN
                            }
                        } else if (isMinimized()) {
                            setState {
                                animationDirection = Direction.UP
                            }
                        }
                    }
                    if (isMaximized()) {
                        attrs.title = game.i("Collapse")
                    } else if (isMinimized()) {
                        attrs.title = game.i("Expand")
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

        drawRegions()
        drawPathsToRegions()
        drawRegionCenterPoints()

        drawHeroAvatarOnMinimap()
        drawViewportRect()

        drawHoveredRegionName()
    }

    private fun GridCoordinate.toMiniMapPixelCoordinate() = this * gameMap.tileSize * mapRatio

    private fun getRegions(): List<GameMapRegion> = activeScene.objects.getByRole(MapRegion)

    private fun drawRegionCenterPoints() {
        canvas.save()

        getRegions().forEach {
            val coordinateOnMiniMap = it.center.toMiniMapPixelCoordinate()
            if (it.id == state.hoveredRegion?.id) {
                drawPoint(coordinateOnMiniMap, "white", "red")
            } else {
                drawPoint(coordinateOnMiniMap, "white", "white")
            }
        }
        canvas.restore()
    }

    private fun drawHeroAvatarOnMinimap() {
        if (game.hero == null || resourceLoader.getLoadedResourceOrNull<ImageResourceData>(HERO_AVATAR_IMG_ID) == null) {
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

    private fun drawHoveredRegionName() {
        if (state.hoveredRegion != undefined) {
            canvas.save()
            val region = state.hoveredRegion!!
            val text = game.i("${region.id}Name")
            val coordinateOnMiniMap = region.center.toMiniMapPixelCoordinate()

            canvas.fillStyle = "white"
            canvas.setShadow("black", 0, 0, 2)
            canvas.font = "bold 16px sans-serif"
            canvas.textBaseline = CanvasTextBaseline.Companion.MIDDLE
            canvas.textAlign = CanvasTextAlign.Companion.CENTER

            // Avoid text out of canvas
            val x = miniMapWidth / 2.0

            val y = if (coordinateOnMiniMap.y < miniMapHeight - 20) {
                // Not hidden by mouse cursor
                coordinateOnMiniMap.y.toDouble() - 20
            } else {
                miniMapHeight - 20.0
            }
            canvas.fillText(text, x, y)
            canvas.restore()
        }
    }

    private fun drawPoint(coordinateOnMiniMap: PixelCoordinate, fillColor: String, shadowColor: String?) {
        canvas.fillStyle = fillColor
        if (shadowColor != null) {
            canvas.setShadow(shadowColor, 0, 0, 5)
        }
        canvas.beginPath()
        canvas.arc(
            coordinateOnMiniMap.x.toDouble(), coordinateOnMiniMap.y.toDouble(),
            3.0, 0.0, PI * 2, false
        )
        canvas.fill()
    }

    private fun drawRegions() {
        canvas.setLineDash(emptyArray())
        canvas.disableShadow()
        canvas.lineWidth = 0.0

        getRegions().forEach {
            val vertices = it.vertices.map { it.toMiniMapPixelCoordinate() } + it.vertices[0].toMiniMapPixelCoordinate()
            canvas.drawRegion(vertices)
        }
    }

    fun CanvasRenderingContext2D.drawRegion(curvePoints: List<PixelCoordinate>) {
        require(curvePoints.size > 2)
        save()

        beginPath()
        moveTo(curvePoints[0].x.toDouble(), curvePoints[1].y.toDouble())

        for (i in 1 until curvePoints.size - 2) {
            val xc = (curvePoints[i].x + curvePoints[i + 1].x) / 2
            val yc = (curvePoints[i].y + curvePoints[i + 1].y) / 2
            quadraticCurveTo(curvePoints[i].x, curvePoints[i].y, xc, yc)
        }
        quadraticCurveTo(
            curvePoints[curvePoints.size - 2].x, curvePoints[curvePoints.size - 2].y,
            curvePoints[curvePoints.size - 1].x, curvePoints[curvePoints.size - 1].y
        )
        clip()
        drawImage(getImageElement("texture"), 0.0, 0.0, miniMapWidth.toDouble(), miniMapHeight.toDouble())
        restore()
    }

    private fun drawPathsToRegions() {
        canvas.save()
        canvas.setLineDash(arrayOf(3.0, 3.0))
        canvas.lineWidth = 2.0
        canvas.disableShadow()

        getRegions().forEach {
            val coordinateOnMiniMap = it.center.toMiniMapPixelCoordinate()
            canvas.beginPath()
            canvas.moveTo(coordinateOnMiniMap.x.toDouble(), coordinateOnMiniMap.y.toDouble())
            it.nextRegions.forEach {
                val destCoordinateOnMiniMap = it.center.toMiniMapPixelCoordinate()

                canvas.lineTo(destCoordinateOnMiniMap.x.toDouble(), destCoordinateOnMiniMap.y.toDouble())
                canvas.stroke()
            }
        }
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
