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
@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.protocol.PlayerSpeechEventData
import com.bytelegend.app.shared.protocol.sentences
import com.bytelegend.client.app.engine.GAME_CLOCK_20MS_EVENT
import com.bytelegend.client.app.engine.MOUSE_CLICK_EVENT
import com.bytelegend.client.app.engine.MOUSE_MOVE_EVENT
import com.bytelegend.client.app.engine.MouseEventListener
import com.bytelegend.client.app.engine.showSpeechBubble
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.classes
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RState
import react.dom.div
import react.dom.jsStyle
import react.setState

interface HeroControlButtonState : RState {
    var showPointer: Boolean
    var showPieMenu: Boolean
    var showSpeakMenu: Boolean
}

class HeroControlButton : GameUIComponent<GameProps, HeroControlButtonState>() {
    private val on50HzClockListener: EventListener<Nothing> = {
        setState { }
    }

    override fun HeroControlButtonState.init() {
        showPointer = false
        showPieMenu = false
        showSpeakMenu = false
    }

    private val mouseMoveListener: MouseEventListener = {
        setState {
            showPointer = it.mapCoordinate == game.hero?.gridCoordinate
        }
    }

    private val mouseClickListener: MouseEventListener = {
        setState {
            init()
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(MOUSE_MOVE_EVENT, mouseMoveListener)
        props.game.eventBus.on(MOUSE_CLICK_EVENT, mouseClickListener)
        props.game.eventBus.on(GAME_CLOCK_20MS_EVENT, on50HzClockListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(MOUSE_MOVE_EVENT, mouseMoveListener)
        props.game.eventBus.remove(MOUSE_CLICK_EVENT, mouseClickListener)
        props.game.eventBus.remove(GAME_CLOCK_20MS_EVENT, on50HzClockListener)
    }

    override fun RBuilder.render() {
        if (game.heroPlayer.isAnonymous || game.hero!!.isMoving()) {
            return
        }

        if (state.showPointer) {
            renderPointer()
        } else if (state.showPieMenu) {
            renderPieMenu()
        }
    }

    private fun RBuilder.renderPointer() {
        val center = game.hero!!.pixelCoordinate - canvasCoordinateInMap + canvasCoordinateInGameContainer + tileSize / 2
        val z = Layer.HeroControlButton.zIndex().toString()
        div {
            attrs.id = "hero-control-button"
            attrs.jsStyle {
                position = "absolute"
                zIndex = z
                width = "32px"
                height = "32px"
                left = "${center.x}px"
                top = "${center.y}px"
                transform = "translate(-50%, -50%)"
                cursor = "pointer"
            }
            attrs.onClickFunction = {
                setState {
                    showPieMenu = true
                    showPointer = false
                }
            }
        }
    }

    private fun RBuilder.renderPieMenu() {
        val center = game.hero!!.pixelCoordinate - canvasCoordinateInMap + canvasCoordinateInGameContainer + tileSize / 2
        child(PieMenu::class) {
            attrs.centerPoint = center
            attrs.zIndex = Layer.HeroControlButton.zIndex()
            attrs.items = listOf(
                PieMenuItem(
                    i("Speak"),
                    "pie-menu-speak-icon"
                ) {
                    setState {
                        showSpeakMenu = !showSpeakMenu
                    }
                }
            )
            attrs.onClose = {
                setState {
                    showPieMenu = false
                    showSpeakMenu = false
                }
            }
        }

        if (state.showSpeakMenu) {
            val z = Layer.HeroControlButton.zIndex() + 4
            div {
                attrs.classes = jsObjectBackedSetOf("dropdown-menu")
                attrs.jsStyle {
                    display = "block"
                    position = "absolute"
                    left = "${center.x}px"
                    top = "${center.y - 60}px"
                    zIndex = z.toString()
                }
                sentences.forEach { sentenceId ->
                    div {
                        attrs.classes = jsObjectBackedSetOf("dropdown-item")
                        attrs.jsStyle {
                            cursor = "pointer"
                        }
                        unsafeSpan(i(sentenceId))
                        attrs.onClickFunction = {
                            GlobalScope.launch {
                                game.webSocketClient.speak(sentenceId)
                            }
                            activeScene.showSpeechBubble(game.heroPlayer, PlayerSpeechEventData(game.heroPlayer.id, sentenceId))
                            setState {
                                init()
                            }
                        }
                    }
                }
            }
        }
    }
}
