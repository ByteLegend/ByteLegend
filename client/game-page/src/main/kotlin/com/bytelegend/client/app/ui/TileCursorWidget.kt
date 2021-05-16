package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.engine.MOUSE_MOVE_EVENT
import com.bytelegend.client.app.engine.MOUSE_OUT_OF_MAP_EVENT
import com.bytelegend.client.app.engine.MouseEventListener
import kotlinx.browser.window
import react.RBuilder
import react.RState
import react.setState

interface TileCursorWidgetState : RState {
    var cursorCoordinateOnMap: GridCoordinate?

    // 0/1
    var animationFrameIndex: Int
}

val ANIMATION_INTERVAL_MS = 300

class TileCursorWidget : GameUIComponent<GameProps, TileCursorWidgetState>() {
    private var timerId: Int? = null

    private val mouseMoveListener: MouseEventListener = {
        setState {
            cursorCoordinateOnMap = it.mapCoordinate
        }
    }
    private val mouseOutOfMapListener: EventListener<Any> = {
        setState {
            cursorCoordinateOnMap = undefined
        }
    }

    override fun TileCursorWidgetState.init() {
        animationFrameIndex = 0
    }

    override fun shouldComponentUpdate(nextProps: GameProps, nextState: TileCursorWidgetState): Boolean {
        return state.cursorCoordinateOnMap != nextState.cursorCoordinateOnMap ||
            state.animationFrameIndex != nextState.animationFrameIndex
    }

    override fun componentDidMount() {
        super.componentDidMount()
        timerId = window.setInterval(
            {
                setState {
                    animationFrameIndex = 1 - animationFrameIndex
                }
            },
            ANIMATION_INTERVAL_MS
        )
        props.game.eventBus.on(MOUSE_MOVE_EVENT, mouseMoveListener)
        props.game.eventBus.on(MOUSE_OUT_OF_MAP_EVENT, mouseOutOfMapListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        window.clearInterval(timerId!!)
        props.game.eventBus.remove(MOUSE_MOVE_EVENT, mouseMoveListener)
        props.game.eventBus.remove(MOUSE_OUT_OF_MAP_EVENT, mouseOutOfMapListener)
    }

    override fun RBuilder.render() {
        if (state.cursorCoordinateOnMap != undefined && !state.cursorCoordinateOnMap!!.outOf(mapGridSize)) {
            val coordinateInCanvas = (state.cursorCoordinateOnMap!! * tileSize) - canvasCoordinateInMap + canvasCoordinateInGameContainer
            val borderColor = determineBorderColor(state.cursorCoordinateOnMap!!)

            if (state.animationFrameIndex != 0 || borderColor == "red") {
                absoluteDiv(
                    left = coordinateInCanvas.x + 3,
                    top = coordinateInCanvas.y + 3,
                    width = tileSize.width,
                    height = tileSize.height,
                    zIndex = Layer.CursorWidget.zIndex(),
                    opacity = "0.7",
                    extraStyleBuilder = {
                        border = "$borderColor 4px dashed"
                    }
                )
            } else {
                absoluteDiv(
                    left = coordinateInCanvas.x + 5,
                    top = coordinateInCanvas.y + 5,
                    width = tileSize.width - 4,
                    height = tileSize.height - 4,
                    zIndex = Layer.CursorWidget.zIndex(),
                    opacity = "0.7",
                    extraStyleBuilder = {
                        border = "$borderColor 4px dashed"
                    }
                )
            }
        }
    }

    private fun determineBorderColor(cursorCoordinate: GridCoordinate): String = when {
        game.hero != null && game.hero!!.gridCoordinate == cursorCoordinate -> "white"
        isClickable(cursorCoordinate) -> "#007bff"
        game.hero == null -> "white"
        game.activeScene != game._hero!!.gameScene -> "white"
        game.hero!!.searchPath(cursorCoordinate).isEmpty() -> "red"
        else -> "green"
    }

    private fun isClickable(cursorCoordinate: GridCoordinate) =
        game.activeScene.objects.getByCoordinate(cursorCoordinate).any {
            it.roles.contains(GameObjectRole.Clickable)
        }
}
