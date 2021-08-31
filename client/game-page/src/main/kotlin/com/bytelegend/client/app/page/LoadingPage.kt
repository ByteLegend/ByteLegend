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
import common.widget.ProgressBar
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.br
import react.dom.div
import react.dom.img
import react.dom.jsStyle
import react.setState

interface LoadingPageProps : RProps, EventBusAware

interface LoadingPageState : RState {
    var errorMessages: MutableList<String>
}

class LoadingPage : RComponent<LoadingPageProps, LoadingPageState>() {
    private val resourceLoadingFailureListener: ResourceLoadingFailureEventListener = {
        setState {
            errorMessages.add(it.message)
        }
    }

    init {
        game.eventBus.on(RESOURCE_LOADING_FAILURE_EVENT, resourceLoadingFailureListener)
    }

    override fun LoadingPageState.init() {
        errorMessages = mutableListOf()
    }

    override fun RBuilder.render() {
        logoDiv()
        progressBarDiv()
    }

    private fun RBuilder.logoDiv() {
        // Must be inlined styles, because CSS might not be loaded at this point.
        div {
            attrs.jsStyle {
                width = "100vw"
                height = "30vh"
                display = "flex"
                alignItems = "center"
                justifyContent = "center"
                backgroundColor = "black"
            }
            img {
                attrs.src = "${GAME_INIT_DATA.rrbd}/img/logo/logo.png"
            }
        }
        div {
            attrs.jsStyle {
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

    private fun RBuilder.progressBarDiv() {
        div {
            attrs.jsStyle {
                width = "100vw"
                height = "10vh"
                display = "flex"
                alignItems = "flex-start"
                justifyContent = "center"
                backgroundColor = "black"
            }
            div {
                attrs.jsStyle {
                    width = "80vw"
                    height = "10vh"
                }
                child(ProgressBar::class) {
                    attrs.eventBus = props.eventBus
                }
            }
        }

        div {
            attrs.jsStyle {
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
