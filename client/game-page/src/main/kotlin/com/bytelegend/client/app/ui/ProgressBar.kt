package common.widget

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.ui.bootstrap.BootstrapProgressBar
import com.bytelegend.client.app.engine.RESOURCE_LOADING_FAILURE_EVENT
import com.bytelegend.client.app.engine.RESOURCE_LOADING_SUCCESS_EVENT
import com.bytelegend.client.app.page.game
import kotlinx.browser.window
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.setState

/**
 * A progress bar for displaying loading progress of resources.
 *
 * 1. If nothing happens, the progress bar goes forward 1% per second, and stops at 99%, to give an illusion of loading (which is actually not).
 * 2. Upon ProgressBarUpdate event, update the current progress to target progress, but never go backward.
 */

external interface ProgressBarState : RState {
    var now: Int
}

external interface ProgressBarProps : RProps {
    var eventBus: EventBus
}

// https://react-bootstrap.github.io/components/progress/
enum class ProgressBarVariant {
    SUCCESS,
    INFO,
    WARNING,
    DANGER
}

class ProgressBar : RComponent<ProgressBarProps, ProgressBarState>() {
    var timerId: Int = 0
    private val onProgressBarUpdate: EventListener<Nothing> = {
        val progress = game.resourceLoader.currentProgress()

        // When progress == 100 and RESOURCE_LOADING_SUCCESS_EVENT is emitted,
        // GamePage might respond to that event first and unmount this component,
        // Then this component respond and sees itself already unmounted, complaining:
        //  Can't perform a React state update on an unmounted component. This is a no-op, but it indicates a memory leak in your application
        // We simply do nothing in this case.
        if (progress > state.now && progress != 100) {
            setState {
                now = progress
            }
        }
    }

    private fun onSecondTimer() {
        if (state.now == undefined) {
            setState {
                now = 0
            }
        } else if (state.now < 99) {
            setState {
                now += 1
            }
        }
    }

    override fun ProgressBarState.init() {
        now = 0
    }

    override fun componentWillUnmount() {
        window.clearInterval(timerId)
        props.eventBus.remove(RESOURCE_LOADING_SUCCESS_EVENT, onProgressBarUpdate)
        props.eventBus.remove(RESOURCE_LOADING_FAILURE_EVENT, onProgressBarUpdate)
    }

    override fun componentDidMount() {
        timerId = window.setInterval(this::onSecondTimer, 1000)
        props.eventBus.on(RESOURCE_LOADING_SUCCESS_EVENT, onProgressBarUpdate)
        props.eventBus.on(RESOURCE_LOADING_FAILURE_EVENT, onProgressBarUpdate)
    }

    override fun RBuilder.render() {
        BootstrapProgressBar {
            attrs.now = state.now
            attrs.variant = ProgressBarVariant.WARNING.name.toLowerCase()
            attrs.animated = true
        }
    }
}
