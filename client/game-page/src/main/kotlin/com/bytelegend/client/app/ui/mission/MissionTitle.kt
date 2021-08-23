package com.bytelegend.client.app.ui.mission

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.protocol.ChallengeUpdateEventData
import com.bytelegend.client.app.engine.GameMission
import com.bytelegend.client.app.engine.MISSION_REPAINT_EVENT
import com.bytelegend.client.app.page.game
import com.bytelegend.client.app.ui.Layer
import com.bytelegend.client.app.ui.absoluteDiv
import com.bytelegend.client.utils.jsObjectBackedSetOf
import react.RBuilder
import react.dom.div
import react.setState

interface MissionTitleProps : BouncingTitleProps {
    var totalStar: Int
    var currentStar: Int
    var mission: GameMission
}

class MissionTitle : AbstractBouncingTitle<MissionTitleProps>() {
    private val onMissionRepaintListener: EventListener<ChallengeUpdateEventData> = this::onMissionRepaint

    private fun onMissionRepaint(eventData: ChallengeUpdateEventData) {
        // Refresh upon mission update event
        if (eventData.newValue.missionId == props.mission.id) {
            setState {}
        }
    }

    override fun RBuilder.render() {
        renderTitle {
            absoluteDiv(
                zIndex = Layer.BouncingTitle.zIndex() + 3,
                classes = jsObjectBackedSetOf("title-star-answer-box")
            ) {
                div {
                    child(TitleStarCounter::class) {
                        attrs.total = props.totalStar
                        attrs.current = props.currentStar
                    }

                    child(MissionTitleAnswers::class) {
                        attrs.game = game
                        attrs.mission = props.mission
                    }
                }
            }
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.gameScene.gameRuntime.eventBus.on(MISSION_REPAINT_EVENT, onMissionRepaintListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.gameScene.gameRuntime.eventBus.remove(MISSION_REPAINT_EVENT, onMissionRepaintListener)
    }
}
