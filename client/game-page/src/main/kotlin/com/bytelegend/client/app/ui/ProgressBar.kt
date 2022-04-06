/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package common.widget

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.ui.bootstrap.BootstrapProgressBar
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.setState
import kotlinx.js.jso
import kotlinx.browser.window
import react.Component
import react.ReactNode
import react.State
import react.createElement

/**
 * A progress bar for displaying loading progress of resources.
 *
 * 1. If nothing happens, the progress bar goes forward 1% per second, and stops at 99%, to give an illusion of loading (which is actually not).
 * 2. Upon ProgressBarUpdate event, update the current progress to target progress, but never go backward.
 */

interface ProgressBarState : State {
    var now: Int
}

interface ProgressBarProps : GameProps {
    var eventBus: EventBus
}

// https://react-bootstrap.github.io/components/progress/
enum class ProgressBarVariant {
    SUCCESS,
    INFO,
    WARNING,
    DANGER
}

class ProgressBar : Component<ProgressBarProps, ProgressBarState>() {
    var timerId: Int = 0

    init {
        state = jso { now = 0 }
    }

    private val onProgressBarUpdate: EventListener<Nothing> = {
        val progress = props.game.resourceLoader.currentProgress()

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

    override fun componentWillUnmount() {
        window.clearInterval(timerId)
        props.eventBus.remove(GAME_UI_UPDATE_EVENT, onProgressBarUpdate)
    }

    override fun componentDidMount() {
        timerId = window.setInterval(this::onSecondTimer, 1000)
        props.eventBus.on(GAME_UI_UPDATE_EVENT, onProgressBarUpdate)
    }

    override fun render(): ReactNode {
        return createElement(BootstrapProgressBar, jso {
            now = state.now
            variant = ProgressBarVariant.WARNING.name.lowercase()
            animated = true
        })
    }
}
