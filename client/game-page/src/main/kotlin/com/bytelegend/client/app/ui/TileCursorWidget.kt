package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.client.app.engine.MOUSE_MOVE_EVENT
import com.bytelegend.client.app.engine.MOUSE_OUT_OF_MAP_EVENT
import com.bytelegend.client.app.engine.MouseEventListener
import common.utils.search
import kotlinx.browser.window
import react.RBuilder
import react.RState
import react.setState

interface TileCursorWidgetProps : GameProps
interface TileCursorWidgetState : RState {
    var cursorCoordinateOnMap: GridCoordinate?

    // 0/1
    var animationFrameIndex: Int
}

val ANIMATION_INTERVAL_MS = 300

class TileCursorWidget : GameUIComponent<TileCursorWidgetProps, TileCursorWidgetState>() {
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

    override fun shouldComponentUpdate(nextProps: TileCursorWidgetProps, nextState: TileCursorWidgetState): Boolean {
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
                    coordinateInCanvas.x + 3,
                    coordinateInCanvas.y + 3,
                    tileSize.width,
                    tileSize.height,
                    Layer.CursorWidget.zIndex(),
                    "0.7",
                    extraStyleBuilder = {
                        border = "$borderColor 4px dashed"
                    }
                )
            } else {
                absoluteDiv(
                    coordinateInCanvas.x + 5,
                    coordinateInCanvas.y + 5,
                    tileSize.width - 4,
                    tileSize.height - 4,
                    Layer.CursorWidget.zIndex(),
                    "0.7",
                    extraStyleBuilder = {
                        border = "$borderColor 4px dashed"
                    }
                )
            }
        }
    }

    private fun determineBorderColor(cursorCoordinate: GridCoordinate): String = when {
        game.hero == null -> "white"
        game.activeScene != game._hero!!.gameScene -> "white"
        search(game.activeScene.blockers, game.hero!!.gridCoordinate, cursorCoordinate).isEmpty() -> "red"
        else -> "green"
    }
}
