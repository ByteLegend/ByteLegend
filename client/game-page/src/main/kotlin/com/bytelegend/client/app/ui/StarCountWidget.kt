package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.GameCanvasState
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.protocol.StarUpdateEventData
import com.bytelegend.client.app.script.effect.starIncrementEffect
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.id
import org.w3c.dom.HTMLDivElement
import react.RBuilder
import react.RState
import react.setState

interface StarCountWidgetProps : GameProps
interface StarCountWidgetState : RState

const val STAR_INCREMENT_EVENT = "star.increment"

// Note this returns (right, top), not (left, top)
fun GameCanvasState.determineStarCountWidgetCoordinateInGameContainerRightTop() = PixelCoordinate(
    getUICoordinateInGameContainer().x,
    getUICoordinateInGameContainer().y + AVATAR_HEIGHT
)

fun GameCanvasState.determineStarCountWidgetCoordinateInGameContainerLeftTop() =
    determineStarCountWidgetCoordinateInGameContainerRightTop().let {
        PixelCoordinate(gameContainerSize.width - it.x, it.y)
    }

class StarCountWidget : GameUIComponent<StarCountWidgetProps, StarCountWidgetState>() {
    lateinit var div: HTMLDivElement

    private val starIncrementEventListener: EventListener<StarUpdateEventData> = this::onStarIncrement

    private fun onStarIncrement(eventData: StarUpdateEventData) {
        GlobalScope.launch {
            starIncrementEffect(
                eventData.change,
                div.getBoundingClientRect().x.toInt(),
                div.getBoundingClientRect().y.toInt(),
                div.getBoundingClientRect().width.toInt(),
                div.getBoundingClientRect().height.toInt(),
            )
            game.heroPlayer.star = eventData.newValue
            setState { }
        }
    }

    override fun RBuilder.render() {
        if (!game.heroPlayer.isAnonymous) {
            absoluteDiv(
                right = gameCanvasState.determineStarCountWidgetCoordinateInGameContainerRightTop().x,
                top = gameCanvasState.determineStarCountWidgetCoordinateInGameContainerRightTop().y,
                zIndex = Layer.StarWidget.zIndex(),
                classes = setOf("map-title-text")
            ) {
                attrs.id = "star-count"
                +"${game.heroPlayer.star}⭐"
                ref {
                    if (it != null) {
                        div = it as HTMLDivElement
                    }
                }
            }
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(STAR_INCREMENT_EVENT, starIncrementEventListener)
    }

    override fun componentWillUnmount() {
        super.componentDidMount()
        props.game.eventBus.remove(STAR_INCREMENT_EVENT, starIncrementEventListener)
    }
}
