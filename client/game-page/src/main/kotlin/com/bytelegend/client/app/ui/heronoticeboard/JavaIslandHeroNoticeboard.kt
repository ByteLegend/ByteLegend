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
import com.bytelegend.client.utils.toHeroNoticeboardTilesData
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.entities.MissionModalData
import com.bytelegend.app.shared.entities.mission.ChallengeSpec
import com.bytelegend.app.shared.entities.mission.HeroNoticeboardTile
import com.bytelegend.app.shared.protocol.ChallengeUpdateEventData
import com.bytelegend.app.shared.util.currentTimeMillis
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.jsStyle
import com.bytelegend.client.app.ui.loadingSpinner
import com.bytelegend.client.app.ui.mission.WebEditor
import com.bytelegend.client.app.ui.setState
import com.bytelegend.client.app.ui.unsafeSpan
import com.bytelegend.client.app.web.getText
import csstype.ClassName
import kotlinx.js.jso
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import react.ChildrenBuilder
import react.Component
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.b
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.span
import react.react

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
    var missionModalData: MissionModalData
    var challengeSpec: ChallengeSpec
    var whitelist: List<String>
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
    Component<JavaIslandHeroNoticeboardProps, JavaIslandHeroNoticeboardState>(props) {
    private val onChallengeRepaintListener: EventListener<ChallengeUpdateEventData> = this::onMissionRepaint

    private fun onMissionRepaint(eventData: ChallengeUpdateEventData) {
        // Refresh upon mission finished event
        if (eventData.change.accomplished) {
            onClickPage(state.currentPage)
        }
    }

    init {
        state = jso {
            currentPage = props.totalPage
            tiles = props.initTiles
            jsonIsLoading = false
            imageIsLoading = true
            timestamp = currentTimeMillis()
        }
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
            val json = getText(heroesJsonUrl(number, state.timestamp))
            val heroNoticeboardTilesData = toHeroNoticeboardTilesData(JSON.parse(json))
            setState {
                jsonIsLoading = false
                tiles = heroNoticeboardTilesData.tiles
            }
        }
    }

    override fun render() = Fragment.create {
        BootstrapModalBody {
            h2 {
                jsStyle {
                    textAlign = "center"
                }
                b {
                    +props.game.i("HeroNoticeboard")
                }
            }
            div {
                jsStyle {
                    textAlign = "center"
                }
                unsafeSpan(props.game.i("BravePeopleDedication"))
            }

            div {
                className = ClassName("noticeboard-avatars-div")

                if (state.imageIsLoading || state.jsonIsLoading) {
                    div {
                        className = ClassName("center-of-parent")
                        loadingSpinner()
                    }
                }

                leftButton()
                rightButton()
                avatarImg()
            }
            paginationButtons()
            span {
                jsStyle {
                    margin = "0 auto"
                    display = "table"
                }
                if (state.hoveredTileCoordinate != null) {
                    +"(${state.hoveredTileCoordinate!!.x}, ${state.hoveredTileCoordinate!!.y})"
                } else {
                    className = ClassName("transparent-text")
                    +"Yay! You found an easter egg!"
                }
            }

            child(WebEditor::class.react, jso {
                game = props.game
                whitelist = props.whitelist
                missionModalData = props.missionModalData
                challengeSpec = props.challengeSpec
            })
        }
    }

    private fun ChildrenBuilder.paginationButtons() {
        if (props.totalPage > 1) {
            BootstrapPagination {
                className = "flex-center hero-noticeboard-pagination"
                repeat(props.totalPage) { page ->
                    BootstrapPaginationItem {
                        +(page + 1).toString()
                        active = page + 1 == state.currentPage
                        onClick = {
                            onClickPage(page + 1)
                        }
                    }
                }
            }
        }
    }

    private fun ChildrenBuilder.leftButton() {
        if (state.currentPage > 1) {
            BootstrapButton {
                className = "noticeboard-button noticeboard-left-button"
                +"<"
                onClick = {
                    onClickPage(state.currentPage - 1)
                }
            }
        }
    }

    private fun ChildrenBuilder.rightButton() {
        if (state.currentPage < props.totalPage) {
            BootstrapButton {
                className = "noticeboard-button noticeboard-right-button"
                +">"
                onClick = {
                    onClickPage(state.currentPage + 1)
                }
            }
        }
    }

    private fun findTileByMouseCoordinate(event: react.dom.events.MouseEvent<*, *>): HeroNoticeboardTile? {
        if (state.jsonIsLoading) {
            return null
        }
        val e = event.nativeEvent
        val hoveredTileX = e.offsetX.toInt() / AVATAR_TILE_SIZE
        val hoveredTileY = e.offsetY.toInt() / AVATAR_TILE_SIZE
        for (tile in state.tiles) {
            if (tile.x == hoveredTileX && tile.y == hoveredTileY) {
                return tile
            }
        }
        return null
    }

    private fun toCoordinate(event: react.dom.events.MouseEvent<*, *>): GridCoordinate {
        val e = event.nativeEvent
        return GridCoordinate(
            e.offsetX.toInt() / AVATAR_TILE_SIZE,
            e.offsetY.toInt() / AVATAR_TILE_SIZE
        )
    }

    private fun ChildrenBuilder.avatarImg() {
        div {
            className = ClassName("noticeboard-avatars-img")
            img {
                src = heroesImgUrl(state.currentPage, state.timestamp)
                onLoad = {
                    setState {
                        imageIsLoading = false
                    }
                }
                onClick = {
                    findTileByMouseCoordinate(it)?.apply {
                        window.open("https://github.com/$username", "_blank")
                    }
                }
                onMouseMove = {
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
                onMouseOut = {
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

    private fun ChildrenBuilder.avatarTooltip() {
        child(AvatarTooltip::class.react, jso {
            game = props.game
            joinedAtI18n = props.game.i("JoinedAt")
            tile = state.hoveredTile!!
        })
    }

    override fun componentDidMount() {
        props.game.eventBus.on(missionRepaintEvent("remember-brave-people"), onChallengeRepaintListener)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(missionRepaintEvent("remember-brave-people"), onChallengeRepaintListener)
    }
}
