package com.bytelegend.client.app.page

import com.bytelegend.app.client.api.EventBusAware
import com.bytelegend.client.app.engine.RESOURCE_LOADING_FAILURE_EVENT
import com.bytelegend.client.app.engine.ResourceLoadingFailureEventListener
import com.bytelegend.client.app.engine.util.jsObjectBackedSetOf
import common.widget.ProgressBar
import kotlinx.html.classes
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.br
import react.dom.div
import react.dom.img
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
        div {
            attrs.classes = jsObjectBackedSetOf("startup-logo")
            img {
                attrs.src = "${GAME_INIT_DATA.rrbd}/img/logo/logo.png"
            }
        }
        div {
            attrs.classes = jsObjectBackedSetOf("startup-logo-div")
            +GAME_INIT_DATA.enjoyProgrammingText
        }
    }

    private fun RBuilder.progressBarDiv() {
        div {
            attrs.classes = jsObjectBackedSetOf("startup-progress-bar-div")
            div {
                attrs.classes = jsObjectBackedSetOf("startup-progress-bar")
                child(ProgressBar::class) {
                    attrs.eventBus = props.eventBus
                }
            }
        }

        div {
            attrs.classes = jsObjectBackedSetOf("startup-error-message-div ")
            state.errorMessages.forEach {
                +it
                br {}
            }
        }
    }
}
