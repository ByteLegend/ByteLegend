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
package com.bytelegend.client.app.page

import com.bytelegend.app.client.api.EventBusAware
import com.bytelegend.client.app.engine.RESOURCE_LOADING_FAILURE_EVENT
import com.bytelegend.client.app.engine.ResourceLoadingFailureEventListener
import com.bytelegend.client.app.ui.jsStyle
import com.bytelegend.client.app.ui.setState
import common.widget.ProgressBar
import kotlinext.js.jso
import react.ChildrenBuilder
import react.Component
import react.Fragment
import react.Props
import react.ReactNode
import react.State
import react.create
import react.dom.html.ReactHTML.br
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import react.react

interface LoadingPageProps : Props, EventBusAware

interface LoadingPageState : State {
    var errorMessages: MutableList<String>
}

class LoadingPage : Component<LoadingPageProps, LoadingPageState>() {
    private val resourceLoadingFailureListener: ResourceLoadingFailureEventListener = {
        setState {
            errorMessages.add(it.message)
        }
    }

    init {
        game.eventBus.on(RESOURCE_LOADING_FAILURE_EVENT, resourceLoadingFailureListener)
        state = jso { errorMessages = mutableListOf() }
    }

    override fun render(): ReactNode {
        return Fragment.create {
            logoDiv()
            progressBarDiv()
        }
    }

    private fun ChildrenBuilder.logoDiv() {
        // Must be inlined styles, because CSS might not be loaded at this point.
        div {
            jsStyle {
                width = "100vw"
                height = "30vh"
                display = "flex"
                alignItems = "center"
                justifyContent = "center"
                backgroundColor = "black"
            }
            img {
                src = "${GAME_INIT_DATA.rrbd}/img/logo/logo.png"
            }
        }
        div {
            jsStyle {
                width = "100vw"
                height = "20vh"
                display = "flex"
                alignItems = "center"
                justifyContent = "center"
                backgroundColor = "black"
                color = "white"
                fontSize = "20px"
                fontFamily = """"Courier 10 Pitch", "Courier New", Courier, monospace"""
            }
            +GAME_INIT_DATA.enjoyProgrammingText
        }
    }

    private fun ChildrenBuilder.progressBarDiv() {
        div {
            jsStyle {
                width = "100vw"
                height = "10vh"
                display = "flex"
                alignItems = "flex-start"
                justifyContent = "center"
                backgroundColor = "black"
            }
            div {
                jsStyle {
                    width = "80vw"
                    height = "10vh"
                }
                child(ProgressBar::class.react, jso {
                    eventBus = props.eventBus
                })
            }
        }

        div {
            jsStyle {
                width = "100vw"
                height = "40vh"
                display = "flex"
                alignItems = "flex-start"
                justifyContent = "center"
                backgroundColor = "black"
                color = "red"
            }
            state.errorMessages.forEach {
                +it
                br {}
            }
        }
    }
}
