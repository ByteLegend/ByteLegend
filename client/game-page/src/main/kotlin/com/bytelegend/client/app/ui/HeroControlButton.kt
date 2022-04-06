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
@file:Suppress("UnsafeCastFromDynamic", "EXPERIMENTAL_API_USAGE")

package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.protocol.PlayerSpeechEventData
import com.bytelegend.app.shared.protocol.sentences
import com.bytelegend.client.app.engine.GAME_CLOCK_20MS_EVENT
import com.bytelegend.client.app.engine.MOUSE_CLICK_EVENT
import com.bytelegend.client.app.engine.MOUSE_MOVE_EVENT
import com.bytelegend.client.app.engine.MouseEventListener
import com.bytelegend.client.app.engine.showSpeechBubble
import csstype.ClassName
import kotlinx.js.jso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import react.ChildrenBuilder
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.div
import react.react

interface HeroControlButtonState : State {
    var showPointer: Boolean
    var showPieMenu: Boolean
    var showSpeakMenu: Boolean
}

class HeroControlButton : GameUIComponent<GameProps, HeroControlButtonState>() {
    private val on50HzClockListener: EventListener<Nothing> = {
        setState { }
    }

    init {
        state = jso { }
        state.init()
    }

    fun HeroControlButtonState.init() {
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

    override fun render() = Fragment.create {
        if (game.heroPlayer.isAnonymous || game.hero!!.isMoving()) {
            return@create
        }

        if (state.showPointer) {
            renderPointer()
        } else if (state.showPieMenu) {
            renderPieMenu()
        }
    }

    private fun ChildrenBuilder.renderPointer() {
        val center = game.hero!!.pixelCoordinate - canvasCoordinateInMap + canvasCoordinateInGameContainer + tileSize / 2
        val z = Layer.HeroControlButton.zIndex().toString()
        div {
            id = "hero-control-button"
            jsStyle {
                position = "absolute"
                zIndex = z
                width = "32px"
                height = "32px"
                left = "${center.x}px"
                top = "${center.y}px"
                transform = "translate(-50%, -50%)"
                cursor = "pointer"
            }
            onClick = {
                setState {
                    showPieMenu = true
                    showPointer = false
                }
            }
        }
    }

    private fun ChildrenBuilder.renderPieMenu() {
        val center = game.hero!!.pixelCoordinate - canvasCoordinateInMap + canvasCoordinateInGameContainer + tileSize / 2
        child(PieMenu::class.react, jso {
            centerPoint = center
            zIndex = Layer.HeroControlButton.zIndex()
            items = listOf(
                PieMenuItem(
                    i("Speak"),
                    "pie-menu-speak-icon"
                ) {
                    setState {
                        showSpeakMenu = !showSpeakMenu
                    }
                }
            )
            onClose = {
                setState {
                    showPieMenu = false
                    showSpeakMenu = false
                }
            }
        })

        if (state.showSpeakMenu) {
            val z = Layer.HeroControlButton.zIndex() + 4
            div {
                className = ClassName("dropdown-menu")
                jsStyle {
                    display = "block"
                    position = "absolute"
                    left = "${center.x}px"
                    top = "${center.y - 60}px"
                    zIndex = z.toString()
                }
                sentences.forEach { sentenceId ->
                    div {
                        className = ClassName("dropdown-item")
                        jsStyle {
                            cursor = "pointer"
                        }
                        unsafeSpan(i(sentenceId))
                        onClick = {
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
