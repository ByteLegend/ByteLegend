package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.Direction.DOWN
import com.bytelegend.app.shared.Direction.LEFT
import com.bytelegend.app.shared.Direction.RIGHT
import com.bytelegend.app.shared.Direction.UP
import com.bytelegend.app.shared.math.imageBlockOnCanvas
import com.bytelegend.app.shared.math.outOfCanvas
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.client.app.engine.GAME_CLOCK_10HZ_EVENT
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RState
import react.dom.img
import react.dom.jsStyle
import react.setState
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

interface HeroIndicatorWidgetState : RState {
    var left: Int?
    var top: Int?
    var triangleRad: Double?
}

class HeroIndicatorWidget : GameUIComponent<GameProps, HeroIndicatorWidgetState>() {
    val eventListener: EventListener<Nothing> = {
        onClock()
    }

    override fun shouldComponentUpdate(nextProps: GameProps, nextState: HeroIndicatorWidgetState): Boolean {
        return nextState.left != state.left ||
            nextState.top != state.top ||
            nextState.triangleRad != state.triangleRad
    }

    override fun componentDidMount() {
        game.eventBus.on(GAME_CLOCK_10HZ_EVENT, eventListener)
        super.componentDidMount()
    }

    override fun componentWillUnmount() {
        game.eventBus.remove(GAME_CLOCK_10HZ_EVENT, eventListener)
        super.componentWillUnmount()
    }

    private fun shouldDisplayHeroIndicator() =
        game.hero != null &&
            // in current scene, but out of canvas
            game.activeScene.objects.getByIdOrNull<GameObject>(game.hero!!.id) != null &&
            imageBlockOnCanvas(game.hero!!.pixelCoordinate, canvasCoordinateInMap, tileSize).outOfCanvas(canvasPixelSize)

    private fun onClock() {
        if (!shouldDisplayHeroIndicator()) {
            setState {
                left = undefined
                top = undefined
                triangleRad = undefined
            }
            return
        }

        val animationOffset = ((game.currentTimeMillis * HERO_INDICATOR_ANIMATION_FPS / 1000).toInt() % 2) * ANIMATION_OFFSET

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

    override fun RBuilder.render() {
        if (!shouldDisplayHeroIndicator() || state.left == undefined) {
            return
        }
        absoluteDiv(
            left = state.left!!,
            top = state.top!!,
            width = HERO_INDICATOR_DIV_WIDTH,
            height = HERO_INDICATOR_DIV_HEIGHT,
            zIndex = Layer.HeroIndicator.zIndex() + 1
        ) {
            img(src = game.resolve("/img/ui/hero.png")) {
                attrs.jsStyle {
                    position = "absolute"
                    width = "${HERO_LOGO_WIDTH}px"
                    height = "${HERO_LOGO_HEIGHT}px"
                    top = "${HERO_LOGO_TOP}px"
                    left = "${HERO_LOGO_LEFT}px"
                }
            }
            attrs.jsStyle {
                cursor = "pointer"
            }
            attrs.onClickFunction = {
                game.activeScene.canvasState.moveTo(game.hero!!.pixelCoordinate.offset(-canvasPixelSize.width / 2, -canvasPixelSize.height / 2))
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
