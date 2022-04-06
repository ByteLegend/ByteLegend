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
package com.bytelegend.client.app.ui.mission

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.HasBouncingTitle
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.engine.GAME_ANIMATION_EVENT
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.Layer
import com.bytelegend.client.app.ui.absoluteDiv
import com.bytelegend.client.app.ui.setState
import kotlinx.js.jso
import kotlinx.browser.document
import org.w3c.dom.HTMLDivElement
import react.ChildrenBuilder
import react.Fragment
import react.State
import react.create

interface BouncingTitlesState : State {
    // The title object ids to show
    var objectIds: List<String>?
}

const val FLOATING_TITLE_FPS = 3
const val FLOATING_TITLE_ANIMATION_OFFSET_PX = -2
const val HIGHTLIGHT_TITLES_EVENT = "highlight.titles"
private const val TITLES_CONTAINER_ELEMENT_ID = "titles-container"

/**
 * Displays bouncing titles on the map, like road signs for map entrance, or mission titles.
 */
class BouncingTitles : GameUIComponent<GameProps, BouncingTitlesState>() {
    init {
        state = jso { }
    }

    private val onAnimation: EventListener<Nothing> = this::onAnimation
    private val onHighlightMissionListener: EventListener<List<String>?> = this::onHighlightTitles

    private val divCoordinate: PixelCoordinate
        get() = canvasCoordinateInGameContainer - canvasCoordinateInMap

    override fun render() = Fragment.create {
        absoluteDiv(
            left = divCoordinate.x,
            top = divCoordinate.y,
            width = mapPixelSize.width,
            height = mapPixelSize.height,
            zIndex = Layer.BouncingTitle.zIndex(),
            className = "bouncing-title-layer"
        ) {
            it.id = TITLES_CONTAINER_ELEMENT_ID
            if (state.objectIds == null) {
                activeScene.objects.getByRole<GameObject>(GameObjectRole.HasBouncingTitle).forEach {
                    renderOne(it)
                }
            } else {
                state.objectIds!!.forEach {
                    renderOne(activeScene.objects.getById(it))
                }
            }
        }
    }

    private fun onHighlightTitles(titleIds: List<String>?) {
        setState { objectIds = titleIds }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onAnimation(n: Nothing) {
        val animationOffset = ((game.elapsedTimeSinceStart * FLOATING_TITLE_FPS / 1000).toInt() % 2) * FLOATING_TITLE_ANIMATION_OFFSET_PX

        document.getElementById(TITLES_CONTAINER_ELEMENT_ID)?.apply {
            val divStyle = unsafeCast<HTMLDivElement>().style
            divStyle.top = "${divCoordinate.y + animationOffset}px"
            divStyle.left = "${divCoordinate.x}px"
        }
    }

    private fun ChildrenBuilder.renderOne(hasBouncingTitle: GameObject) {
        val obj = hasBouncingTitle.unsafeCast<HasBouncingTitle>()
        if (obj.bouncingTitleEnabled) {
            obj.renderBouncingTitle(this)
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(GAME_ANIMATION_EVENT, onAnimation)
        props.game.eventBus.on(HIGHTLIGHT_TITLES_EVENT, onHighlightMissionListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(GAME_ANIMATION_EVENT, onAnimation)
        props.game.eventBus.remove(HIGHTLIGHT_TITLES_EVENT, onHighlightMissionListener)
    }
}
