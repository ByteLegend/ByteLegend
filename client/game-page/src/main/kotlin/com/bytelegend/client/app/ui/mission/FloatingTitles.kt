package com.bytelegend.client.app.ui.mission

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.math.outOfCanvas
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.app.shared.objects.GridCoordinateAware
import com.bytelegend.client.app.engine.GAME_CLOCK_50HZ_EVENT
import com.bytelegend.client.app.engine.GameMission
import com.bytelegend.client.app.obj.GameMapEntrance
import com.bytelegend.client.app.ui.EntranceRoadSign
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import react.RBuilder
import react.RState
import react.setState

const val BEGINNER_GUIDE_FINISHED_STATE = "BeginnerGuideFinished"

interface FloatingTitlesState : RState {
    var counter: Int

    // Only show the special mission id. For beginner guide
    var highlightedObjectIds: List<String>?
}

// Used to calculate whether the widget is inside the canvas, it doesn't have to be accurate.
const val ESTIMATE_WIDTH = 100
const val TITLE_HEIGHT = 32
const val HIGHTLIGHT_MISSION_EVENT = "highlight.mission"

/**
 * Displays floating titles on the map, like road signs for map entrance, or mission titles.
 */
class FloatingTitles : GameUIComponent<GameProps, FloatingTitlesState>() {
    private val on50HzClockListener: EventListener<Nothing> = this::on50HzClock
    private val onHighlightMissionListener: EventListener<List<String>?> = this::onHighlightMissions

    // counter increment on 50Hz clock
    override fun FloatingTitlesState.init() {
        counter = 0
        highlightedObjectIds = null
    }

    private fun onHighlightMissions(missionIds: List<String>?) {
        setState { highlightedObjectIds = missionIds }
    }

    private fun on50HzClock(n: Nothing) {
        setState { counter += 1 }
    }

    override fun RBuilder.render() {
        if (state.highlightedObjectIds != null) {
            state.highlightedObjectIds!!.map { activeScene.objects.getById<GameObject>(it) }.forEach { renderOne(it) }
        } else if (game.heroPlayer.isAnonymous || game.heroPlayer.states.containsKey(BEGINNER_GUIDE_FINISHED_STATE)) {
            // If not finished beginner guide, don't show the titles
            activeScene.objects.getByRole<GameObject>(GameObjectRole.HasFloatingTitle)
                .filter { insideCanvas(it) }
                .forEach { renderOne(it) }
        }
    }

    private fun RBuilder.renderOne(hasFloatingTitle: GameObject) {
        val gridCoordinate = hasFloatingTitle.unsafeCast<GridCoordinateAware>().gridCoordinate
        val coordinateInGameContainer = calculateCoordinateInCanvas(gridCoordinate).coordinate + canvasCoordinateInGameContainer
        val left = coordinateInGameContainer.x // maybe border?
        val bottom = gameContainerHeight - coordinateInGameContainer.y + 8
        val offsetY = if (state.counter % 20 < 10) 0 else -2

        if (hasFloatingTitle.roles.contains(GameObjectRole.MapEntrance.toString())) {
            val entrance = hasFloatingTitle.unsafeCast<GameMapEntrance>()
            child(EntranceRoadSign::class) {
                attrs.game = game
                attrs.eventBus = game.eventBus
                attrs.left = left
                attrs.bottom = bottom
                attrs.offsetY = offsetY
                attrs.title = i(entrance.destMapId)
                attrs.entrance = entrance
            }
        } else {
            val mission = hasFloatingTitle.unsafeCast<GameMission>()
            child(MissionTitle::class) {
                attrs.eventBus = game.eventBus
                attrs.left = left
                attrs.bottom = bottom
                attrs.offsetY = offsetY
                attrs.title = i(mission.gameMapMission.title)
                attrs.tileCoordinate = mission.gridCoordinate
                attrs.totalStar = mission.gameMapMission.totalStar
                attrs.currentStar = activeScene.playerChallenges.missionStar(mission.id)
                attrs.mission = mission
            }
        }
    }

    private fun insideCanvas(obj: GameObject): Boolean {
        return !calculateCoordinateInCanvas(obj.unsafeCast<GridCoordinateAware>().gridCoordinate).outOfCanvas(activeScene.canvasState.getCanvasPixelSize())
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
