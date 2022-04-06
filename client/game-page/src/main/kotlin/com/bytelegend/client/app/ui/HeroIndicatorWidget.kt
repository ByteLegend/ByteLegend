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
package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.Direction.DOWN
import com.bytelegend.app.shared.Direction.LEFT
import com.bytelegend.app.shared.Direction.RIGHT
import com.bytelegend.app.shared.Direction.UP
import com.bytelegend.app.shared.math.imageBlockOnCanvas
import com.bytelegend.app.shared.math.outOfCanvas
import com.bytelegend.client.app.engine.GAME_CLOCK_100MS_EVENT
import kotlinx.js.jso
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.img
import kotlin.math.PI
import kotlin.math.atan

const val HERO_INDICATOR_DIV_WIDTH = 64
const val HERO_INDICATOR_DIV_HEIGHT = 64
const val HERO_LOGO_WIDTH = 32
const val HERO_LOGO_HEIGHT = 32
const val HERO_LOGO_LEFT = (HERO_INDICATOR_DIV_WIDTH - HERO_LOGO_WIDTH) / 2
const val HERO_LOGO_TOP = (HERO_INDICATOR_DIV_HEIGHT - HERO_LOGO_HEIGHT) / 2
const val HERO_INDICATOR_ANIMATION_FPS = 6
const val ANIMATION_OFFSET = 5
const val HERO_ICON =
    "data:image/png;base64,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"

interface HeroIndicatorWidgetState : State {
    var left: Int?
    var top: Int?
    var triangleRad: Double?
}

class HeroIndicatorWidget : GameUIComponent<GameProps, HeroIndicatorWidgetState>() {
    init {
        state = jso()
    }

    val eventListener: EventListener<Nothing> = {
        onClock()
    }

    override fun shouldComponentUpdate(nextProps: GameProps, nextState: HeroIndicatorWidgetState): Boolean {
        return nextState.left != state.left ||
            nextState.top != state.top ||
            nextState.triangleRad != state.triangleRad
    }

    override fun componentDidMount() {
        game.eventBus.on(GAME_CLOCK_100MS_EVENT, eventListener)
        super.componentDidMount()
    }

    override fun componentWillUnmount() {
        game.eventBus.remove(GAME_CLOCK_100MS_EVENT, eventListener)
        super.componentWillUnmount()
    }

    private fun shouldDisplayHeroIndicator() =
        game.hero != null &&
            // not in current scene, or out of canvas
            (!heroIsInCurrentScene || imageBlockOnCanvas(game.hero!!.pixelCoordinate, canvasCoordinateInMap, tileSize).outOfCanvas(canvasPixelSize))

    private val heroIsInCurrentScene: Boolean
        get() = game._hero!!.gameScene.isActive

    private fun onClock() {
        if (!shouldDisplayHeroIndicator()) {
            setState {
                left = undefined
                top = undefined
                triangleRad = undefined
            }
            return
        }

        val animationOffset = ((game.elapsedTimeSinceStart * HERO_INDICATOR_ANIMATION_FPS / 1000).toInt() % 2) * ANIMATION_OFFSET

        if (!heroIsInCurrentScene) {
            setState {
                left = 150
                top = 100 + animationOffset
                triangleRad = 1.75 * PI
            }
            return
        }

        val canvasX = canvasCoordinateInMap.x
        val canvasY = canvasCoordinateInMap.y
        val canvasWidth = canvasPixelSize.width
        val canvasHeight = canvasPixelSize.height
        val centerX = canvasX + canvasWidth / 2
        val centerY = canvasY + canvasHeight / 2
        val heroX = game.hero!!.pixelCoordinate.x
        val heroY = game.hero!!.pixelCoordinate.y

        // up arrow 0 rad
        // left arrow -PI/2 (3/2 PI)
        // right arrow PI/2
        // down arrow PI
        @Suppress("NON_EXHAUSTIVE_WHEN")
        when (heroDirectionToCanvas()) {
            UP -> {
                if (heroX == centerX) {
                    setState {
                        left = canvasCoordinateInGameContainer.x + canvasWidth / 2
                        top = canvasCoordinateInGameContainer.y + animationOffset
                        triangleRad = 0.0
                    }
                }
                val tangent = 1.0 * (centerY - heroY) / (heroX - centerX)
                val intersectionPointX = 0.5 * canvasHeight / tangent + centerX
                val left = canvasCoordinateInGameContainer.x + intersectionPointX - canvasX
                val top = canvasCoordinateInGameContainer.y
                val triangleRad = atan(1 / tangent)
                setState {
                    this.left = left.toInt()
                    this.top = top + animationOffset
                    this.triangleRad = triangleRad
                }
            }
            DOWN -> {
                if (heroX == centerX) {
                    setState {
                        left = canvasCoordinateInGameContainer.x + canvasWidth / 2
                        top = canvasCoordinateInGameContainer.y + canvasHeight - HERO_INDICATOR_DIV_HEIGHT - animationOffset
                        triangleRad = PI
                    }
                }

                val tangent = 1.0 * (heroY - centerY) / (heroX - centerX)
                val intersectionPointX = 0.5 * canvasHeight / tangent + centerX
                val left = canvasCoordinateInGameContainer.x + intersectionPointX - canvasX
                val top = canvasCoordinateInGameContainer.y + canvasHeight - HERO_INDICATOR_DIV_HEIGHT
                val triangleRad = PI - atan(1 / tangent)
                setState {
                    this.left = left.toInt()
                    this.top = top - animationOffset
                    this.triangleRad = triangleRad
                }
            }
            LEFT -> {
                if (heroY == centerY) {
                    setState {
                        left = canvasCoordinateInGameContainer.x + animationOffset
                        top = canvasCoordinateInGameContainer.y + canvasHeight / 2
                        triangleRad = 1.5 * PI
                    }
                }

                val cotangent = 1.0 * (centerX - heroX) / (heroY - centerY)
                val intersectionPointY = centerY + 0.5 * canvasWidth / cotangent
                val left = canvasCoordinateInGameContainer.x
                val top = canvasCoordinateInGameContainer.y + intersectionPointY - canvasY
                val triangleRad = 1.5 * PI - atan(1 / cotangent)
                setState {
                    this.left = left + animationOffset
                    this.top = top.toInt()
                    this.triangleRad = triangleRad
                }
            }
            RIGHT -> {
                if (heroY == centerY) {
                    setState {
                        left = canvasCoordinateInGameContainer.x + canvasWidth - HERO_LOGO_WIDTH - animationOffset
                        top = canvasCoordinateInGameContainer.y + canvasHeight / 2
                        triangleRad = 1.5 * PI
                    }
                }
                val cotangent = 1.0 * (heroX - centerX) / (heroY - centerY)
                val intersectionPointY = centerY + 0.5 * canvasWidth / cotangent
                val left = canvasCoordinateInGameContainer.x + canvasWidth - HERO_INDICATOR_DIV_WIDTH
                val top = canvasCoordinateInGameContainer.y + intersectionPointY - canvasY
                val triangleRad = PI / 2 + atan(1 / cotangent)
                setState {
                    this.left = left - animationOffset
                    this.top = top.toInt()
                    this.triangleRad = triangleRad
                }
            }
            else -> throw IllegalStateException("Should not be here")
        }
    }

    private fun heroDirectionToCanvas(): Direction {
        val canvasWidth = activeScene.canvasState.getCanvasPixelSize().width
        val canvasHeight = activeScene.canvasState.getCanvasPixelSize().height

        val heroCoordinate = game.hero!!.pixelCoordinate
        val canvasCenterCoordinate = activeScene.canvasState.run {
            getCanvasCoordinateInMap().offset(canvasWidth / 2, canvasHeight / 2)
        }

        // Let's first decide which direction is the hero, by comparing
        // the degree of canvas-center-hero-line and canvas-center-canvas-corner-point-line

        // 0 ~ PI/2
        val degreeToRightBottomCorner = atan(1.0 * canvasHeight / canvasWidth)

        var heroDirectionToCanvas = when {
            heroCoordinate.x == canvasCenterCoordinate.x && heroCoordinate.y < canvasCenterCoordinate.y -> UP
            heroCoordinate.x == canvasCenterCoordinate.x && heroCoordinate.y > canvasCenterCoordinate.y -> DOWN
            heroCoordinate.y == canvasCenterCoordinate.y && heroCoordinate.x < canvasCenterCoordinate.x -> LEFT
            heroCoordinate.y == canvasCenterCoordinate.y && heroCoordinate.x > canvasCenterCoordinate.x -> RIGHT
            else -> null
        }

        val degreeToHero = atan(1.0 * (heroCoordinate.y - canvasCenterCoordinate.y) / (heroCoordinate.x - canvasCenterCoordinate.x))
        if (heroDirectionToCanvas == null) {
            heroDirectionToCanvas = when {
                heroCoordinate.x > canvasCenterCoordinate.x && degreeToHero <= degreeToRightBottomCorner && degreeToHero >= -degreeToRightBottomCorner -> RIGHT
                heroCoordinate.x < canvasCenterCoordinate.x && degreeToHero <= degreeToRightBottomCorner && degreeToHero >= -degreeToRightBottomCorner -> LEFT
                heroCoordinate.y < canvasCenterCoordinate.y && degreeToHero <= -degreeToRightBottomCorner -> UP
                heroCoordinate.y > canvasCenterCoordinate.y && degreeToHero <= -degreeToRightBottomCorner -> DOWN
                heroCoordinate.y < canvasCenterCoordinate.y && degreeToHero >= degreeToRightBottomCorner -> UP
                heroCoordinate.y > canvasCenterCoordinate.y && degreeToHero >= degreeToRightBottomCorner -> DOWN
                else -> throw IllegalStateException()
            }
        }
        return heroDirectionToCanvas
    }

    override fun render() = Fragment.create {
        if (!shouldDisplayHeroIndicator() || state.left == undefined) {
            return@create
        }
        absoluteDiv(
            left = state.left!!,
            top = state.top!!,
            className = "hero-indicator",
            width = HERO_INDICATOR_DIV_WIDTH,
            height = HERO_INDICATOR_DIV_HEIGHT,
            zIndex = Layer.HeroIndicator.zIndex() + 1
        ) {
            img {
                src = HERO_ICON
                jsStyle {
                    position = "absolute"
                    this.width = "${HERO_LOGO_WIDTH}px"
                    this.height = "${HERO_LOGO_HEIGHT}px"
                    top = "${HERO_LOGO_TOP}px"
                    left = "${HERO_LOGO_LEFT}px"
                }
            }
            it.jsStyle {
                cursor = "pointer"
                userSelect = "none"
            }
            it.onClick = {
                val heroScene = game._hero!!.gameScene
                if (heroScene.isActive) {
                    game.activeScene.canvasState.moveTo(game.hero!!.pixelCoordinate.offset(-canvasPixelSize.width / 2, -canvasPixelSize.height / 2))
                } else {
                    game.sceneContainer.loadScene(heroScene.map.id)
                }
            }
        }

        absoluteDiv(
            left = state.left!!,
            top = state.top!!,
            width = HERO_INDICATOR_DIV_WIDTH,
            height = HERO_INDICATOR_DIV_HEIGHT,
            zIndex = Layer.HeroIndicator.zIndex(),
            extraStyleBuilder = {
                transform = "rotate(" + state.triangleRad.asDynamic().toFixed(2) + "rad)"
            }
        ) {
            // by default upward triangle
            absoluteDiv(
                left = 24, // Don't ask me why, it's trial and error
                top = -8,
                width = 0,
                height = 0,
                zIndex = Layer.HeroIndicator.zIndex(),
                extraStyleBuilder = {
                    border = "8px solid transparent"
                    borderBottom = "8px solid red"
                }
            )
        }
    }
}
