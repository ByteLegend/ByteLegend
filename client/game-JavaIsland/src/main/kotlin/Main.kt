import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.dsl.GameSceneBuilder
import com.bytelegend.app.shared.JAVA_ISLAND
import com.bytelegend.app.shared.JAVA_ISLAND_NEWBIE_VILLAGE_PUB
import kotlinx.browser.window

val gameRuntime = window.asDynamic().gameRuntime.unsafeCast<GameRuntime>()

fun GameSceneBuilder.noticeboard(objectId: String) {
    noticeboard {
        id = objectId
        coordinatePointId = "${objectId}Point"
    }
}

fun main() {
    gameRuntime.sceneContainer.getSceneById(JAVA_ISLAND).configure {
        mapEntrance {
            destMapId = JAVA_ISLAND_NEWBIE_VILLAGE_PUB
        }

        noticeboard("JavaIslandNewbieVillageNoticeboard1")
        noticeboard("JavaIslandNewbieVillageNoticeboard2")
        noticeboard("JavaIslandNewbieVillageNoticeboard3")
        noticeboard("JavaIslandNewbieVillageNoticeboard4")

//            npc {
//                id = "JavaIslandNewbieVillageOldMan"
//                animationSetIndex = 1
//                onClick = {
//                }
//                onTouch = {
//                    if (it.id == HERO_ID) {
//                    }
//                }
//                onInit = {
//                }
//            }
//            text {
//                coordinates = emptyList()
//            }
//
//            region {
//                center
//                vertices
//                next
//            }
//
//            curve {
//                points
//            }
//            point {
//
//            }
    }
}
