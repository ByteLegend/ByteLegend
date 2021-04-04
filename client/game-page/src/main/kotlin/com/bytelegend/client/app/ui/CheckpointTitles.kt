package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.math.outOfCanvas
import com.bytelegend.app.shared.objects.GameMapCheckpoint
import com.bytelegend.app.shared.objects.GameObjectRole.Checkpoint
import com.bytelegend.client.app.engine.GAME_CLOCK_50HZ_EVENT
import com.bytelegend.client.app.engine.MOUSE_MOVE_EVENT
import com.bytelegend.client.app.engine.MOUSE_OUT_OF_MAP_EVENT
import com.bytelegend.client.app.engine.MouseEventListener
import kotlinx.html.js.onMouseMoveFunction
import kotlinx.html.js.onMouseOutFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.jsStyle
import react.setState

interface CheckpointTitlesProps : GameProps
interface CheckpointTitlesState : RState {
    var counter: Int
}

// Used to calculate whether the widget is inside the canvas, it doesn't have to be accurate.
const val ESTIMATE_WIDTH = 100
const val TITLE_HEIGHT = 32

class CheckpointTitles : GameUIComponent<CheckpointTitlesProps, CheckpointTitlesState>() {
    private val on50HzClockListener: EventListener<Nothing> = this::on50HzClock

    // counter increment on 50Hz clock
    override fun CheckpointTitlesState.init() {
        counter = 0
    }

    private fun on50HzClock(n: Nothing) {
        setState { counter += 1 }
    }

    override fun RBuilder.render() {
        activeScene.objects.getByRole<GameMapCheckpoint>(Checkpoint)
            .filter { insideCanvas(it) && it.title.isNotBlank() }
            .forEach {
                child(CheckpointTitle::class) {
                    val coordinateInGameContainer = calculateCoordinateInCanvas(it.point).coordinate + canvasCoordinateInGameContainer
                    attrs.eventBus = game.eventBus
                    attrs.x = coordinateInGameContainer.x
                    attrs.y = coordinateInGameContainer.y
                    attrs.offsetY = if (state.counter % 20 < 10) 0 else -2
                    attrs.title = i(it.title)
                    attrs.tileCoordinate = it.point
                }
            }
    }

    private fun insideCanvas(checkpoint: GameMapCheckpoint): Boolean {
        return !calculateCoordinateInCanvas(checkpoint.point).outOfCanvas(activeScene.canvasState.getCanvasPixelSize())
    }

    private fun calculateCoordinateInCanvas(point: GridCoordinate): PixelBlock {
        val tileSize = activeScene.map.tileSize
        val targetTilePixelCoordinate = point * tileSize
        val widgetLeftTopCornerXInCanvas = targetTilePixelCoordinate.x + tileSize.width / 2 - canvasCoordinateInMap.x
        val widgetLeftTopCornerYInCanvas = targetTilePixelCoordinate.y - TITLE_HEIGHT - canvasCoordinateInMap.y
        return PixelBlock(
            widgetLeftTopCornerXInCanvas, widgetLeftTopCornerYInCanvas, ESTIMATE_WIDTH, TITLE_HEIGHT
        )
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(GAME_CLOCK_50HZ_EVENT, on50HzClockListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(GAME_CLOCK_50HZ_EVENT, on50HzClockListener)
    }
}

interface CheckpointTitleProps : RProps {
    // coordinate in game container
    var x: Int
    var y: Int

    // for animation
    var offsetY: Int
    var title: String
    var tileCoordinate: GridCoordinate
    var eventBus: EventBus
}

interface CheckPointTitleState : RState {
    var hovered: Boolean
}

class CheckpointTitle : RComponent<CheckpointTitleProps, CheckPointTitleState>() {
    private val mouseMoveListener: MouseEventListener = {
        if (it.mapCoordinate == props.tileCoordinate) {
            setState { hovered = true }
        } else {
            setState { hovered = false }
        }
    }
    private val mouseOutOfMapListener: EventListener<Any> = {
        setState {
            hovered = false
        }
    }

    override fun CheckPointTitleState.init() {
        hovered = false
    }

    private fun getOffsetY() = if (state.hovered) 0 else props.offsetY

    override fun RBuilder.render() {
        absoluteSpan(
            left = props.x,
            top = props.y + getOffsetY(),
            height = TITLE_HEIGHT,
            zIndex = Layer.CheckpointTitle.zIndex(),
            classes = setOf("checkpoint-title"),
            content = props.title
        ) {
            attrs.onMouseOutFunction = {
                setState { hovered = false }
            }
            attrs.onMouseMoveFunction = {
                setState { hovered = true }
            }
            if (state.hovered) {
                attrs.jsStyle {
                    boxShadow = "0 0 20px white"
                }
            }
        }

        absoluteDiv(
            left = props.x + 2, // triangle self's width
            top = props.y + TITLE_HEIGHT + getOffsetY(),
            zIndex = Layer.CheckpointTitle.zIndex() + 1,
            classes = setOf("checkpoint-title-triangle-container")
        ) {
            absoluteDiv(
                left = 0,
                top = 0,
                width = 0,
                height = 0,
                classes = setOf("checkpoint-title-triangle")
            )
        }
    }

    override fun shouldComponentUpdate(nextProps: CheckpointTitleProps, nextState: CheckPointTitleState): Boolean {
        return props.x != nextProps.x ||
            props.y != nextProps.y ||
            props.title != nextProps.title ||
            props.offsetY != nextProps.offsetY ||
            state.hovered != nextState.hovered
    }

    override fun componentDidMount() {
        props.eventBus.on(MOUSE_MOVE_EVENT, mouseMoveListener)
        props.eventBus.on(MOUSE_OUT_OF_MAP_EVENT, mouseOutOfMapListener)
    }

    override fun componentWillUnmount() {
        props.eventBus.remove(MOUSE_MOVE_EVENT, mouseMoveListener)
        props.eventBus.remove(MOUSE_OUT_OF_MAP_EVENT, mouseOutOfMapListener)
    }
}
