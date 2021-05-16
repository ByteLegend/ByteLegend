package com.bytelegend.client.app.ui.noticeboard

import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapSpinner
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.unsafeHtml
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.html.classes
import kotlinx.html.js.onLoadFunction
import kotlinx.html.js.onMouseMoveFunction
import kotlinx.html.js.onMouseOutFunction
import org.w3c.dom.events.MouseEvent
import react.RBuilder
import react.RComponent
import react.RState
import react.dom.div
import react.dom.img
import react.dom.jsStyle
import react.dom.p
import react.setState

const val BRAVE_PEOPLE_ALL_JSON_URL = "https://bytelegend-brave-people.oss-cn-hongkong.aliyuncs.com/brave-people-all.json"
const val BRAVE_PEOPLE_IMG_URL = "https://bytelegend-brave-people.oss-cn-hongkong.aliyuncs.com/brave-people.png"

// Don't change these values. They are defined elsewhere:
// https://github.com/ByteLegendQuest/remember-brave-people/blob/master/src/main/java/com/bytelegend/game/Constants.java#L26
const val AVATAR_TILE_SIZE = 24
const val AVATAR_HORIZONTAL_NUM = 25
const val AVATAR_VERTICAL_NUM = 25
const val AVATAR_DIV_WIDTH = AVATAR_TILE_SIZE * AVATAR_HORIZONTAL_NUM
const val AVATAR_DIV_HEIGHT = AVATAR_TILE_SIZE * AVATAR_VERTICAL_NUM

// https://github.com/ByteLegendQuest/remember-brave-people/blob/master/src/main/java/com/bytelegend/game/SimpleTile.java
// https://github.com/ByteLegendQuest/remember-brave-people/blob/master/src/main/java/com/bytelegend/game/AllInfoTile.java
data class AvatarTile(
    val x: Int,
    val y: Int,
    val color: String,
    val username: String,
    val createdAt: String,
    val changedAt: String
)

interface JavaIslandNewbieVillageNoticeboardState : RState {
    var hoveredTile: AvatarTile?
    var avatarTiles: Array<AvatarTile>?
    var imageDisplay: String
}

class JavaIslandNewbieVillageNoticeboard :
    RComponent<GameProps, JavaIslandNewbieVillageNoticeboardState>() {
    private var loading = false

    override fun UNSAFE_componentWillReceiveProps(nextProps: GameProps) {
        setState { }
    }

    override fun JavaIslandNewbieVillageNoticeboardState.init() {
        imageDisplay = "none"
    }

    private fun imgAndJsonLoaded(): Boolean {
        return state.avatarTiles != undefined && state.imageDisplay == "block"
    }

    override fun RBuilder.render() {
        BootstrapModalBody {
            p {
                attrs.jsStyle.textAlign = "center"
                unsafeHtml(props.game.i("BravePeopleDedication"))
            }
            div {
                attrs.jsStyle {
                    position = "relative"
                    width = "${AVATAR_DIV_WIDTH}px"
                    height = "${AVATAR_DIV_HEIGHT}px"
                    border = "1px solid black"
                    margin = "0 auto"
                    cursor = "pointer"
                }

                if (!imgAndJsonLoaded()) {
                    if (loading) {
                        div {
                            attrs.classes = setOf("flex-center")
                            BootstrapSpinner {
                                attrs.animation = "border"
                            }
                        }
                    } else {
                        GlobalScope.launch {
                            val json = window.fetch(BRAVE_PEOPLE_ALL_JSON_URL)
                                .await()
                                .apply {
                                    if (status < 200 || status > 400) {
                                        throw Exception("Got response status code $status")
                                    }
                                }.text().await()
                            setState {
                                avatarTiles = JSON.parse(json)
                            }
                        }
                        loading = true
                    }
                }
                avatarImg()

                if (state.hoveredTile != undefined) {
                    avatarTooltip()
                }
            }
        }
    }

    private fun RBuilder.avatarImg() {
        img {
            attrs.src = BRAVE_PEOPLE_IMG_URL
            attrs.jsStyle {
                display = state.imageDisplay
            }
            attrs.onLoadFunction = {
                setState {
                    imageDisplay = "block"
                }
            }
            if (imgAndJsonLoaded()) {
                attrs.onMouseMoveFunction = {
                    val event = it.asDynamic().nativeEvent as MouseEvent
                    val hoveredTileX = event.offsetX.toInt() / AVATAR_TILE_SIZE
                    val hoveredTileY = event.offsetY.toInt() / AVATAR_TILE_SIZE
                    var hoveredTile: AvatarTile? = undefined
                    for (tile in state.avatarTiles!!) {
                        if (tile.x == hoveredTileX && tile.y == hoveredTileY) {
                            hoveredTile = tile
                            break
                        }
                    }

                    setState { this.hoveredTile = hoveredTile }
                }
                attrs.onMouseOutFunction = {
                    setState { hoveredTile = undefined }
                }
            }
        }
    }

    private fun RBuilder.avatarTooltip() {
        child(AvatarTooltip::class) {
            attrs.joinedAtI18n = props.game.i("JoinedAt")
            attrs.tile = state.hoveredTile!!
        }
    }
}
