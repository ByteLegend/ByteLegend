package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.math.outOfCanvas
import com.bytelegend.app.shared.objects.GameMapMission
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.engine.GAME_CLOCK_50HZ_EVENT
import com.bytelegend.client.app.engine.GameMission
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

const val BEGINNER_GUIDE_FINISHED_STATE = "BeginnerGuideFinished"

interface MissionTitlesProps : GameProps
interface MissionTitlesState : RState {
    var counter: Int

    // Only show the special mission id. For beginner guide
    var highlightedMissionIds: List<String>?
}

// Used to calculate whether the widget is inside the canvas, it doesn't have to be accurate.
const val ESTIMATE_WIDTH = 100
const val TITLE_HEIGHT = 32
const val HIGHTLIGHT_MISSION_EVENT = "highlight.mission"

class MissionTitles : GameUIComponent<MissionTitlesProps, MissionTitlesState>() {
    private val on50HzClockListener: EventListener<Nothing> = this::on50HzClock
    private val onHighlightMissionListener: EventListener<List<String>?> = this::onHighlightMissions

    // counter increment on 50Hz clock
    override fun MissionTitlesState.init() {
        counter = 0
        highlightedMissionIds = null
    }

    private fun onHighlightMissions(missionIds: List<String>?) {
        setState { highlightedMissionIds = missionIds }
    }

    private fun on50HzClock(n: Nothing) {
        setState { counter += 1 }
    }

    override fun RBuilder.render() {
        if (state.highlightedMissionIds != null) {
            state.highlightedMissionIds!!.map { activeScene.objects.getById<GameMission>(it) }
                .forEach { renderOne(it) }
        } else if (game.heroPlayer.isAnonymous || game.heroPlayer.states.containsKey(BEGINNER_GUIDE_FINISHED_STATE)) {
            // If not finished beginner guide, don't show the titles
            activeScene.objects.getByRole<GameMission>(GameObjectRole.Mission)
                .filter { insideCanvas(it.gameMapMission) && it.gameMapMission.title.isNotBlank() }
                .forEach { renderOne(it) }
        }
    }

    private fun RBuilder.renderOne(mission: GameMission) {
        child(MissionTile::class) {
            val coordinateInGameContainer = calculateCoordinateInCanvas(mission.gridCoordinate).coordinate + canvasCoordinateInGameContainer
            attrs.eventBus = game.eventBus
            attrs.left = coordinateInGameContainer.x + 2 // maybe border?
            attrs.bottom = gameContainerHeight - coordinateInGameContainer.y + 8
            attrs.offsetY = if (state.counter % 20 < 10) 0 else -2
            attrs.title = i(mission.gameMapMission.title)
            attrs.tileCoordinate = mission.gridCoordinate
            attrs.totalStar = 5
            attrs.currentStar = 2
        }
    }

    private fun insideCanvas(mission: GameMapMission): Boolean {
        return !calculateCoordinateInCanvas(mission.point).outOfCanvas(activeScene.canvasState.getCanvasPixelSize())
    }

    private fun calculateCoordinateInCanvas(point: GridCoordinate): PixelBlock {
        val tileSize = activeScene.map.tileSize
        val targetTilePixelCoordinate = point * tileSize
        val widgetLeftTopCornerXInCanvas = targetTilePixelCoordinate.x + tileSize.width / 2 - canvasCoordinateInMap.x
        val widgetLeftTopCornerYInCanvas = targetTilePixelCoordinate.y - canvasCoordinateInMap.y
        return PixelBlock(
            widgetLeftTopCornerXInCanvas, widgetLeftTopCornerYInCanvas, ESTIMATE_WIDTH, TITLE_HEIGHT
        )
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(GAME_CLOCK_50HZ_EVENT, on50HzClockListener)
        props.game.eventBus.on(HIGHTLIGHT_MISSION_EVENT, onHighlightMissionListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(GAME_CLOCK_50HZ_EVENT, on50HzClockListener)
        props.game.eventBus.remove(HIGHTLIGHT_MISSION_EVENT, onHighlightMissionListener)
    }
}

interface MissionTitleProps : RProps {
    // coordinate in game container
    var left: Int
    var bottom: Int

    // for animation
    var offsetY: Int
    var title: String
    var tileCoordinate: GridCoordinate
    var eventBus: EventBus

    var totalStar: Int
    var currentStar: Int
}

interface MissionTitleState : RState {
    var hovered: Boolean
}

class MissionTile : RComponent<MissionTitleProps, MissionTitleState>() {
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

    override fun MissionTitleState.init() {
        hovered = false
    }

    private fun getOffsetY() = if (state.hovered) 0 else props.offsetY

    override fun RBuilder.render() {
        absoluteDiv(
            left = props.left,
            bottom = props.bottom + getOffsetY(),
            zIndex = Layer.MissionTitle.zIndex() + if (state.hovered) 1 else 0,
            classes = setOf("checkpoint-title")
        ) {
            unsafeHtml(props.title)
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
            absoluteDiv(
                zIndex = Layer.MissionTitle.zIndex(),
                classes = setOf("checkpoint-title-bottom-border", "checkpoint-title-bottom-border-left")
            )
            absoluteDiv(
                zIndex = Layer.MissionTitle.zIndex(),
                classes = setOf("checkpoint-title-bottom-border", "checkpoint-title-bottom-border-right")
            )

            absoluteDiv(
                zIndex = Layer.MissionTitle.zIndex() + 2,
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

            absoluteDiv(
                zIndex = Layer.MissionTitle.zIndex() + 3,
                classes = setOf("title-star-box")
            ) {
                child(TitleStarCounter::class) {
                    attrs.total = props.totalStar
                    attrs.current = props.currentStar
                }
            }
        }
    }

    override fun shouldComponentUpdate(nextProps: MissionTitleProps, nextState: MissionTitleState): Boolean {
        return props.left != nextProps.left ||
            props.bottom != nextProps.bottom ||
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
