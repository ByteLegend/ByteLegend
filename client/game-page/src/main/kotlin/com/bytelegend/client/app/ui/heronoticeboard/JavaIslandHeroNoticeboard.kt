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
@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.bytelegend.client.app.ui.heronoticeboard

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.missionRepaintEvent
import com.bytelegend.app.client.ui.bootstrap.BootstrapButton
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapPagination
import com.bytelegend.app.client.ui.bootstrap.BootstrapPaginationItem
import com.bytelegend.app.client.ui.bootstrap.BootstrapSpinner
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.entities.mission.HeroNoticeboardTile
import com.bytelegend.app.shared.protocol.ChallengeUpdateEventData
import com.bytelegend.app.shared.util.currentTimeMillis
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.unsafeSpan
import com.bytelegend.client.app.web.get
import com.bytelegend.client.utils.jsObjectBackedSetOf
import com.bytelegend.client.utils.toHeroNoticeboardTilesData
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.classes
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onLoadFunction
import kotlinx.html.js.onMouseMoveFunction
import kotlinx.html.js.onMouseOutFunction
import org.w3c.dom.events.Event
import org.w3c.dom.events.MouseEvent
import react.RBuilder
import react.RComponent
import react.State
import react.dom.b
import react.dom.div
import react.dom.h2
import react.dom.img
import react.dom.jsStyle
import react.dom.span
import react.setState

fun heroesJsonUrl(page: Int, timestamp: Long) = "/proxy/heroes-$page.json?timestamp=$timestamp"
fun heroesImgUrl(page: Int, timestamp: Long) = "/proxy/heroes-$page.png?timestamp=$timestamp"

// Don't change these values. They are defined elsewhere:
// https://github.com/ByteLegendQuest/remember-brave-people/blob/master/src/main/java/com/bytelegend/game/Constants.java#L26
const val AVATAR_TILE_SIZE = 30

interface JavaIslandHeroNoticeboardProps : GameProps {
    // When the component first loads, it always returns the "heroes-current.json`,
    // in which `currentPage` is the current "totalPage".
    // After the component loads, player can click the page number to browse different pages,
    // but "totalPage" doesn't change - it's always the "page" number when requesting
    // the mission modal data from backend.
    var initTiles: List<HeroNoticeboardTile>
    var totalPage: Int
}

interface JavaIslandHeroNoticeboardState : State {
    var imageIsLoading: Boolean
    var jsonIsLoading: Boolean

    // "current" or 1/2/3/.../X
    var currentPage: Int
    var tiles: List<HeroNoticeboardTile>
    var hoveredTile: HeroNoticeboardTile?
    var hoveredTileCoordinate: GridCoordinate?
    var timestamp: Long
}

class JavaIslandHeroNoticeboard(props: JavaIslandHeroNoticeboardProps) :
    RComponent<JavaIslandHeroNoticeboardProps, JavaIslandHeroNoticeboardState>(props) {
    private val onChallengeRepaintListener: EventListener<ChallengeUpdateEventData> = this::onMissionRepaint

    private fun onMissionRepaint(eventData: ChallengeUpdateEventData) {
        // Refresh upon mission finished event
        if (eventData.change.accomplished) {
            onClickPage(state.currentPage)
        }
    }

    override fun JavaIslandHeroNoticeboardState.init(props: JavaIslandHeroNoticeboardProps) {
        currentPage = props.totalPage
        tiles = props.initTiles
        jsonIsLoading = false
        imageIsLoading = true
        timestamp = currentTimeMillis()
    }

    private fun onClickPage(number: Int) {
        if (number < 1 || number > props.totalPage) {
            return
        }
        setState {
            imageIsLoading = true
            jsonIsLoading = true
            timestamp = currentTimeMillis()
            currentPage = number
            tiles = emptyList()
        }

        GlobalScope.launch {
            val json = get(heroesJsonUrl(number, state.timestamp))
            val heroNoticeboardTilesData = toHeroNoticeboardTilesData(JSON.parse(json))
            setState {
                jsonIsLoading = false
                tiles = heroNoticeboardTilesData.tiles
            }
        }
    }

    override fun RBuilder.render() {
        BootstrapModalBody {
            h2 {
                attrs.jsStyle.textAlign = "center"
                b {
                    +props.game.i("HeroNoticeboard")
                }
            }
            div {
                attrs.jsStyle.textAlign = "center"
                unsafeSpan(props.game.i("BravePeopleDedication"))
            }

            div {
                attrs.classes = jsObjectBackedSetOf("noticeboard-avatars-div")

                if (state.imageIsLoading || state.jsonIsLoading) {
                    div {
                        attrs.classes = jsObjectBackedSetOf("center-of-parent")
                        BootstrapSpinner {
                            attrs.animation = "border"
                        }
                    }
                }

                leftButton()
                rightButton()
                avatarImg()
            }
            paginationButtons()
            span {
                attrs.jsStyle {
                    margin = "0 auto"
                    display = "table"
                }
                if (state.hoveredTileCoordinate != null) {
                    +"(${state.hoveredTileCoordinate!!.x}, ${state.hoveredTileCoordinate!!.y})"
                } else {
                    attrs.classes = jsObjectBackedSetOf("transparent-text")
                    +"Yay! You found an easter egg!"
                }
            }
        }
    }

    private fun RBuilder.paginationButtons() {
        if (props.totalPage > 1) {
            BootstrapPagination {
                repeat(props.totalPage) { page ->
                    BootstrapPaginationItem {
                        +(page + 1).toString()
                        attrs.active = page + 1 == state.currentPage
                        attrs.onClick = {
                            onClickPage(page + 1)
                        }
                    }
                }
            }
        }
    }

    private fun RBuilder.leftButton() {
        if (state.currentPage > 1) {
            BootstrapButton {
                attrs.className = "noticeboard-button noticeboard-left-button"
                +"<"
                attrs.onClick = {
                    onClickPage(state.currentPage - 1)
                }
            }
        }
    }

    private fun RBuilder.rightButton() {
        if (state.currentPage < props.totalPage) {
            BootstrapButton {
                attrs.className = "noticeboard-button noticeboard-right-button"
                +">"
                attrs.onClick = {
                    onClickPage(state.currentPage + 1)
                }
            }
        }
    }

    private fun findTileByMouseCoordinate(event: Event): HeroNoticeboardTile? {
        if (state.jsonIsLoading) {
            return null
        }
        val e = event.asDynamic().nativeEvent as MouseEvent
        val hoveredTileX = e.offsetX.toInt() / AVATAR_TILE_SIZE
        val hoveredTileY = e.offsetY.toInt() / AVATAR_TILE_SIZE
        for (tile in state.tiles) {
            if (tile.x == hoveredTileX && tile.y == hoveredTileY) {
                return tile
            }
        }
        return null
    }

    private fun toCoordinate(event: Event): GridCoordinate {
        val e = event.asDynamic().nativeEvent as MouseEvent
        return GridCoordinate(
            e.offsetX.toInt() / AVATAR_TILE_SIZE,
            e.offsetY.toInt() / AVATAR_TILE_SIZE
        )
    }

    private fun RBuilder.avatarImg() {
        div {
            attrs.classes = jsObjectBackedSetOf("noticeboard-avatars-img")
            img {
                attrs.src = heroesImgUrl(state.currentPage, state.timestamp)
                attrs.onLoadFunction = {
                    setState {
                        imageIsLoading = false
                    }
                }
                attrs.onClickFunction = {
                    findTileByMouseCoordinate(it)?.apply {
                        window.open("https://github.com/$userid", "_blank")
                    }
                }
                attrs.onMouseMoveFunction = {
                    val coordinate: GridCoordinate = toCoordinate(it)
                    val hoveredTile: HeroNoticeboardTile? = findTileByMouseCoordinate(it)

                    val updateCoordinate = (state.hoveredTileCoordinate != coordinate)
                    val updateTile = (state.hoveredTile?.x != hoveredTile?.x || state.hoveredTile?.y != hoveredTile?.y)
                    setState {
                        if (updateTile) {
                            this.hoveredTile = hoveredTile
                        }
                        if (updateCoordinate) {
                            this.hoveredTileCoordinate = coordinate
                        }
                    }
                }
                attrs.onMouseOutFunction = {
                    setState {
                        hoveredTile = null
                        hoveredTileCoordinate = null
                    }
                }
            }

            if (state.hoveredTile != undefined) {
                avatarTooltip()
            }
        }
    }

    private fun RBuilder.avatarTooltip() {
        child(AvatarTooltip::class) {
            attrs.game = props.game
            attrs.joinedAtI18n = props.game.i("JoinedAt")
            attrs.tile = state.hoveredTile!!
        }
    }

    override fun componentDidMount() {
        props.game.eventBus.on(missionRepaintEvent("remember-brave-people"), onChallengeRepaintListener)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(missionRepaintEvent("remember-brave-people"), onChallengeRepaintListener)
    }
}
