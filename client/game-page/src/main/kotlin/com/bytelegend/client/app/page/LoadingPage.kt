package com.bytelegend.client.app.page

import com.bytelegend.app.client.api.EventBusAware
import com.bytelegend.client.app.engine.RESOURCE_LOADING_FAILURE_EVENT
import com.bytelegend.client.app.engine.ResourceLoadingFailureEventListener
import common.widget.ProgressBar
import kotlinx.css.Align
import kotlinx.css.Color
import kotlinx.css.Display
import kotlinx.css.JustifyContent
import kotlinx.css.alignItems
import kotlinx.css.backgroundColor
import kotlinx.css.color
import kotlinx.css.display
import kotlinx.css.fontFamily
import kotlinx.css.fontSize
import kotlinx.css.height
import kotlinx.css.justifyContent
import kotlinx.css.px
import kotlinx.css.vh
import kotlinx.css.vw
import kotlinx.css.width
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.br
import react.dom.img
import react.setState
import styled.css
import styled.styledDiv

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
        styledDiv {
            css {
                width = 100.vw
                height = 30.vh
                display = Display.flex
                alignItems = Align.center
                justifyContent = JustifyContent.center
                backgroundColor = Color.black
            }

            img {
                attrs.src = "${SERVER_SIDE_DATA.RRBD}/img/logo/logo.png"
            }
        }
        styledDiv {
            css {
                width = 100.vw
                height = 20.vh
                display = Display.flex
                alignItems = Align.center
                justifyContent = JustifyContent.center
                backgroundColor = Color.black
                color = Color.white
                fontSize = 20.px
                fontFamily = "\"Courier 10 Pitch\", \"Courier New\", Courier, monospace"
            }

            +SERVER_SIDE_DATA.enjoyProgrammingText

//            styledDiv {
//                css {
//                    color = Color.white
//                    height = 10.vh
//                    width = 80.vw
//                }
//                +"GradleLegend"
//            }
        }
    }

    private fun RBuilder.progressBarDiv() {
        styledDiv {
            css {
                width = 100.vw
                height = 10.vh
                display = Display.flex
                alignItems = Align.flexStart
                justifyContent = JustifyContent.center
                backgroundColor = Color.black
            }
            styledDiv {
                css {
                    height = 10.vh
                    width = 80.vw
                }
                child(ProgressBar::class) {
                    attrs.eventBus = props.eventBus
                }
            }
        }

        styledDiv {
            css {
                width = 100.vw
                height = 40.vh
                display = Display.flex
                alignItems = Align.flexStart
                justifyContent = JustifyContent.center
                backgroundColor = Color.black
                color = Color.red
            }

            state.errorMessages.forEach {
                +it
                br {}
            }
        }
    }
}
