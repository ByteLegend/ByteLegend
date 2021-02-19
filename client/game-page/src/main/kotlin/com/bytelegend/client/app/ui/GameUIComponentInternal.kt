package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.ResourceLoader
import com.bytelegend.app.shared.GameMap
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.GridSize
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import com.bytelegend.client.app.engine.Game
import com.bytelegend.client.app.engine.GameControl
import com.bytelegend.client.app.engine.toGameMouseEvent
import com.bytelegend.client.app.engine.toGridCoordinate
import kotlinx.html.DIV
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.RDOMBuilder
import react.setState

interface GameProps : GameAwareProps, Layered

interface GameAwareProps : RProps, GameAware

/**
 * A special component which responds to game.ui.update event and update itself correspondingly.
 */
abstract class GameUIComponent<P : GameAwareProps, S : RState> : RComponent<P, S>() {
    protected val game: Game
        get() = props.game

    protected val gameContainerWidth: Int
        get() = props.game.gameContainerSize.width
    protected val gameContainerHeight: Int
        get() = props.game.gameContainerSize.height
    protected val tileSize: PixelSize
        get() = props.game.activeScene.map.tileSize
    protected val canvasCoordinateInMap: PixelCoordinate
        get() = props.game.activeScene.canvasState.getCanvasCoordinateInMap()
    protected val canvasGridCoordinateInMap: GridCoordinate
        get() = props.game.activeScene.canvasState.getCanvasGridCoordinateInMap()
    protected val uiContainerCoordinateInGameContainer: PixelCoordinate
        get() = props.game.activeScene.canvasState.getUICoordinateInGameContainer()
    protected val uiContainerSize: PixelSize
        get() = props.game.activeScene.canvasState.getUIContainerSize()
    protected val canvasCoordinateInGameContainer: PixelCoordinate
        get() = props.game.activeScene.canvasState.getCanvasCoordinateInGameContainer()
    protected val canvasGridSize: GridSize
        get() = props.game.activeScene.canvasState.getCanvasGridSize()
    protected val canvasPixelSize: PixelSize
        get() = props.game.activeScene.canvasState.getCanvasPixelSize()
    protected val mapGridSize: GridSize
        get() = props.game.activeScene.map.size
    protected val mapPixelSize: PixelSize
        get() = props.game.activeScene.map.pixelSize
    protected val gameMap: GameMap
        get() = props.game.activeScene.map
    protected val resourceLoader: ResourceLoader
        get() = props.game.resourceLoader
    protected val activeScene: GameScene
        get() = props.game.sceneContainer.activeScene!!
    protected val mapCoveredByCanvas: Boolean
        get() = props.game.activeScene.canvasState.mapCoveredByCanvas
    protected val gameControl: GameControl
        get() = props.game.gameControl

    protected fun moveTo(coordinate: PixelCoordinate) {
        props.game.activeScene.canvasState.moveTo(coordinate)
    }

    private val gameUiUpdateEventListener: EventListener<Any> = {
        setState({ it })
    }

    override fun componentDidMount() {
        props.game.eventBus.on(GAME_UI_UPDATE_EVENT, gameUiUpdateEventListener)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(GAME_UI_UPDATE_EVENT, gameUiUpdateEventListener)
    }

    protected fun toGridCoordinate(event: Event) = props.game.toGridCoordinate(event)
    protected fun toGameMouseEvent(event: Event) = props.game.toGameMouseEvent(event)
    protected fun i(textId: String, vararg args: String) = props.game.i(textId, *args)

    protected fun stateUpdatingEventHandler(fn: (Event) -> Unit): (Event) -> Unit {
        return {
            fn(it)
            setState { }
        }
    }

    protected fun gameControlAwareEventHandler(fn: (Event) -> Unit): (Event) -> Unit {
        return {
            if (game.gameControl.userMouseEnabled) {
                fn(it)
            }
        }
    }
}

abstract class LayeredGameUIComponent<P : GameProps, S : RState> : GameUIComponent<P, S>() {
//    fun StyledDOMBuilder<DIV>.fillContainer(layer: Layer) {
//        css {
//            position = Position.absolute
//            top = 0.px
//            left = 0.px
//            width = containerWidth.px
//            height = containerHeight.px
//            zIndex = layer.zIndex
//        }
//    }

    @Suppress("UnsafeCastFromDynamic")
    fun RBuilder.containerFillingDiv(zIndex: Int, classes: Set<String> = emptySet(), block: RDOMBuilder<DIV>.() -> Unit = {}) {
        absoluteDiv(0, 0, gameContainerWidth, gameContainerHeight, zIndex, "1", classes, block = block)
    }
}

interface GameAware {
    var game: Game
}
