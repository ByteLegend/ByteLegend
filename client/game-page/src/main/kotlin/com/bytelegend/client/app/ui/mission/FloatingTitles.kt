package com.bytelegend.client.app.ui.mission

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.app.shared.objects.GridCoordinateAware
import com.bytelegend.client.app.engine.GAME_ANIMATION_EVENT
import com.bytelegend.client.app.engine.GameMission
import com.bytelegend.client.app.obj.GameMapEntrance
import com.bytelegend.client.app.ui.EntranceRoadSign
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.Layer
import com.bytelegend.client.app.ui.USER_MOUSE_INTERACTION_LAYER_ID
import com.bytelegend.client.app.ui.absoluteDiv
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.browser.document
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onMouseMoveFunction
import kotlinx.html.js.onMouseOutFunction
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.events.MouseEvent
import react.RBuilder
import react.RState
import react.dom.attrs
import react.setState

interface FloatingTitlesState : RState {
    // The title object ids to show
    var objectIds: List<String>?
}

const val FLOATING_TITLE_FPS = 3
const val FLOATING_TITLE_ANIMATION_OFFSET_PX = -2
const val HIGHTLIGHT_TITLES_EVENT = "highlight.titles"
private const val TITLES_CONTAINER_ELEMENT_ID = "titles-container"

/**
 * Displays floating titles on the map, like road signs for map entrance, or mission titles.
 */
class FloatingTitles : GameUIComponent<GameProps, FloatingTitlesState>() {
    private val onAnimation: EventListener<Nothing> = this::onAnimation
    private val onHighlightMissionListener: EventListener<List<String>?> = this::onHighlightTitles

    private val divCoordinate: PixelCoordinate
        get() = canvasCoordinateInGameContainer - canvasCoordinateInMap

    override fun RBuilder.render() {
        absoluteDiv(
            left = divCoordinate.x,
            top = divCoordinate.y,
            width = mapPixelSize.width,
            height = mapPixelSize.height,
            zIndex = Layer.FloatingTitle.zIndex(),
            classes = jsObjectBackedSetOf("user-mouse-interaction-layer")
        ) {
            attrs.id = TITLES_CONTAINER_ELEMENT_ID
            if (state.objectIds == null) {
                activeScene.objects.getByRole<GameObject>(GameObjectRole.HasFloatingTitle).forEach {
                    renderOne(it)
                }
            } else {
                state.objectIds!!.forEach {
                    renderOne(activeScene.objects.getById<GameObject>(it))
                }
            }

            attrs {
                onClickFunction = {
                    document.getElementById(USER_MOUSE_INTERACTION_LAYER_ID)?.dispatchEvent(MouseEvent("click", it.asDynamic()))
                }
                onMouseMoveFunction = {
                    document.getElementById(USER_MOUSE_INTERACTION_LAYER_ID)?.dispatchEvent(MouseEvent("mousemove", it.asDynamic()))
                }
                onMouseOutFunction = {
                    document.getElementById(USER_MOUSE_INTERACTION_LAYER_ID)?.dispatchEvent(MouseEvent("mouseout", it.asDynamic()))
                }
            }
        }
    }

    private fun onHighlightTitles(titleIds: List<String>?) {
        setState { objectIds = titleIds }
    }

    private fun onAnimation(n: Nothing) {
        val animationOffset = ((game.elapsedTimeSinceStart * FLOATING_TITLE_FPS / 1000).toInt() % 2) * FLOATING_TITLE_ANIMATION_OFFSET_PX

        document.getElementById(TITLES_CONTAINER_ELEMENT_ID)?.apply {
            val divStyle = unsafeCast<HTMLDivElement>().style
            divStyle.top = "${divCoordinate.y + animationOffset}px"
            divStyle.left = "${divCoordinate.x}px"
        }
    }

    private fun RBuilder.renderOne(hasFloatingTitle: GameObject) {
        val coordinateInMap = hasFloatingTitle.unsafeCast<GridCoordinateAware>().gridCoordinate * tileSize
        val left = coordinateInMap.x
        val bottom = mapPixelSize.height - coordinateInMap.y
        if (hasFloatingTitle.roles.contains(GameObjectRole.MapEntrance.toString())) {
            val entrance = hasFloatingTitle.unsafeCast<GameMapEntrance>()
            child(EntranceRoadSign::class) {
                attrs.game = game
                attrs.eventBus = game.eventBus
                attrs.left = left + tileSize.width / 2
                attrs.bottom = bottom
                attrs.title = i(entrance.destMapId)
                attrs.entrance = entrance
            }
        } else {
            val mission = hasFloatingTitle.unsafeCast<GameMission>()
            child(MissionTitle::class) {
                attrs.eventBus = game.eventBus
                attrs.left = left + mission.spriteWidthPx / 2
                attrs.bottom = bottom
                attrs.title = i(mission.gameMapMission.title)
                attrs.tileCoordinate = mission.gridCoordinate
                attrs.totalStar = mission.gameMapMission.totalStar
                attrs.currentStar = activeScene.playerChallenges.missionStar(mission.id)
                attrs.mission = mission
            }
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(GAME_ANIMATION_EVENT, onAnimation)
        props.game.eventBus.on(HIGHTLIGHT_TITLES_EVENT, onHighlightMissionListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(GAME_ANIMATION_EVENT, onAnimation)
        props.game.eventBus.remove(HIGHTLIGHT_TITLES_EVENT, onHighlightMissionListener)
    }
}
