@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.bytelegend.client.app.ui.noticeboard

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapSpinner
import com.bytelegend.app.shared.protocol.ChallengeUpdateEventData
import com.bytelegend.app.shared.util.currentTimeMillis
import com.bytelegend.client.app.engine.MISSION_REPAINT_EVENT
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.unsafeSpan
import com.bytelegend.client.utils.jsObjectBackedSetOf
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
import react.dom.b
import react.dom.div
import react.dom.h2
import react.dom.img
import react.dom.jsStyle
import react.dom.p
import react.setState

fun bravePeopleJsonUrl(timestamp: Long) = "https://bytelegend-brave-people.oss-cn-hongkong.aliyuncs.com/brave-people-all.json?timestamp=$timestamp"
fun bravePeopleImgUrl(timestamp: Long) = "https://bytelegend-brave-people.oss-cn-hongkong.aliyuncs.com/brave-people.png?timestamp=$timestamp"

// Don't change these values. They are defined elsewhere:
// https://github.com/ByteLegendQuest/remember-brave-people/blob/master/src/main/java/com/bytelegend/game/Constants.java#L26
const val AVATAR_TILE_SIZE = 30

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
    var timestamp: Long
}

class JavaIslandNewbieVillageNoticeboard :
    RComponent<GameProps, JavaIslandNewbieVillageNoticeboardState>() {
    private var loading = false
    private val onChallengeRepaintListener: EventListener<ChallengeUpdateEventData> = this::onMissionRepaint

    private fun onMissionRepaint(eventData: ChallengeUpdateEventData) {
        // Refresh upon mission finished event
        if (eventData.change.accomplished && eventData.newValue.challengeId == "remember-brave-people") {
            setState {
                init()
            }
        }
    }

    override fun JavaIslandNewbieVillageNoticeboardState.init() {
        avatarTiles = undefined
        imageDisplay = "none"
        timestamp = currentTimeMillis()
    }

    private fun imgAndJsonLoaded(): Boolean {
        return state.avatarTiles != undefined && state.imageDisplay == "block"
    }

    override fun RBuilder.render() {
        BootstrapModalBody {
            h2 {
                attrs.jsStyle.textAlign = "center"
                b {
                    +props.game.i("BravePeopleBoard")
                }
            }
            p {
                attrs.jsStyle.textAlign = "center"
                unsafeSpan(props.game.i("BravePeopleDedication"))
            }
            div {
                attrs.classes = jsObjectBackedSetOf("noticeboard-avatars-div")

                if (!imgAndJsonLoaded()) {
                    if (loading) {
                        div {
                            attrs.classes = jsObjectBackedSetOf("flex-center")
                            BootstrapSpinner {
                                attrs.animation = "border"
                            }
                        }
                    } else {
                        GlobalScope.launch {
                            val json = window.fetch(bravePeopleJsonUrl(state.timestamp))
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
            attrs.classes = jsObjectBackedSetOf("noticeboard-avatars-img")
            attrs.src = bravePeopleImgUrl(state.timestamp)
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

    override fun componentDidMount() {
        props.game.eventBus.on(MISSION_REPAINT_EVENT, onChallengeRepaintListener)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(MISSION_REPAINT_EVENT, onChallengeRepaintListener)
    }
}
