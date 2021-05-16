package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.Timestamp
import com.bytelegend.client.app.engine.GAME_ANIMATION_EVENT
import com.bytelegend.client.app.engine.GameAnimationEventListener
import kotlinx.css.zIndex
import kotlinx.html.classes
import kotlinx.html.id
import react.RBuilder
import react.RState
import react.setState
import styled.css
import styled.styledSpan

interface FpsCounterState : RState {
    var fps: Int
}

/**
 * Display current FPS. Update upon "window.animate" event.
 */
class FpsCounter : GameUIComponent<GameProps, FpsCounterState>() {
    // Don't update the FPS counter too frequently
    private var lastComponentUpdateTime = Timestamp.now()
    private var framesSinceLastComponentUpdate: Int = 0
    private val fpsUpdateEventListener: GameAnimationEventListener = {
        framesSinceLastComponentUpdate++
        val now = Timestamp.now()
        if (now - lastComponentUpdateTime > 500) {
            val currentFps = (1000.0 * framesSinceLastComponentUpdate / (now - lastComponentUpdateTime)).toInt()
            setState {
                fps = currentFps
            }
            lastComponentUpdateTime = now
            framesSinceLastComponentUpdate = 0
        }
    }

    override fun FpsCounterState.init() {
        fps = 0
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(GAME_ANIMATION_EVENT, fpsUpdateEventListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(GAME_ANIMATION_EVENT, fpsUpdateEventListener)
    }

    @Suppress("UnsafeCastFromDynamic")
    override fun RBuilder.render() {
        styledSpan {
            attrs.id = "fps-counter"
            attrs.classes = setOf("map-title-widget", "map-title-text")
            css {
                zIndex = Layer.MapTitle.zIndex()
            }

            +"${state.fps} fps"
//            +"${((1000.0 * framesSinceLastComponentUpdate) / (TimeStamp.now() - lastComponentUpdateTime)).toInt()} fps"
        }
    }
}
