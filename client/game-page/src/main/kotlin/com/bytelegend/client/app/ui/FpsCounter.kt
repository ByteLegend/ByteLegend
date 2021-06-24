package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.Timestamp
import com.bytelegend.client.app.engine.GAME_ANIMATION_EVENT
import com.bytelegend.client.app.engine.GameAnimationEventListener
import com.bytelegend.client.app.engine.util.jsObjectBackedSetOf
import kotlinx.html.classes
import kotlinx.html.id
import react.RBuilder
import react.RState
import react.dom.jsStyle
import react.dom.span
import react.setState

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
        val z = Layer.MapTitle.zIndex()
        span {
            attrs.id = "fps-counter"
            attrs.classes = jsObjectBackedSetOf("map-title-widget", "map-title-text")
            attrs.jsStyle {
                zIndex = z
            }

            +"${state.fps} fps"
        }
    }
}
