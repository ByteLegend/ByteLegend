package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.client.app.engine.GAME_CLOCK_10HZ
import com.bytelegend.client.app.engine.GAME_CLOCK_10HZ_EVENT
import react.RBuilder
import react.RState
import react.setState

const val SCENE_SWITCH_START_EVENT = "scene.switch.start"

// in 500ms, opacity from 1 to 0
const val FADE_IN_PERIOD_MS = 1000

interface FadeInFadeOutLayerState : RState {
    var opacity: Double
}

class FadeInFadeOutLayer : GameUIComponent<GameProps, FadeInFadeOutLayerState>() {
    private val onSceneSwitchStartEventListener: EventListener<Nothing> = {
        onSceneSwitchStart()
    }
    private val onGameClockEventListener: EventListener<Nothing> = {
        onGameClock()
    }

    override fun FadeInFadeOutLayerState.init() {
        opacity = 0.0
    }

    override fun componentDidMount() {
        game.eventBus.on(SCENE_SWITCH_START_EVENT, onSceneSwitchStartEventListener)
        super.componentDidMount()
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        game.eventBus.remove(SCENE_SWITCH_START_EVENT, onSceneSwitchStartEventListener)
    }

    private fun onSceneSwitchStart() {
        setState {
            opacity = 1.0
        }
        game.eventBus.on(GAME_CLOCK_10HZ_EVENT, onGameClockEventListener)
    }

    private fun onGameClock() {
        setState {
            opacity = state.opacity - 1000.0 / (FADE_IN_PERIOD_MS * GAME_CLOCK_10HZ)
        }
        if (state.opacity <= 0) {
            game.eventBus.remove(GAME_CLOCK_10HZ_EVENT, onGameClockEventListener)
        }
    }

    override fun RBuilder.render() {
        if (state.opacity > 0) {
            absoluteDiv(
                0, 0, gameContainerWidth, gameContainerHeight,
                Layer.FadeInFadeOut.zIndex(),
                state.opacity.toString(),
                extraStyleBuilder = {
                    backgroundColor = "black"
                }
            )
        }
    }
}
