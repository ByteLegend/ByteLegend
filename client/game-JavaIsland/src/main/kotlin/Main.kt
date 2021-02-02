import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.HERO_ID
import com.bytelegend.app.client.api.dsl.gameScene
import com.bytelegend.app.shared.JAVA_ISLAND
import com.bytelegend.app.shared.JAVA_ISLAND_NEWBIE_VILLAGE_PUB
import com.bytelegend.app.shared.objects.GameObjectRole
import kotlinx.browser.window

val gameRuntime = window.asDynamic().gameRuntime.unsafeCast<GameRuntime>()

fun main() {
//    gameRuntime.addMapEntrance(JAVA_ISLAND, JAVA_ISLAND_NEWBIE_VILLAGE_PUB)
//    gameRuntime.addNPC(JAVA_ISLAND, "JavaIslandNewbieVillageOldMan", 1)
//    gameRuntime.addNPC(JAVA_ISLAND, "JavaNewbieVillagePubGuard", 2)
    gameRuntime.gameScene(JAVA_ISLAND) {
        objects {
            mapEntrance {
                destMapId = JAVA_ISLAND_NEWBIE_VILLAGE_PUB
            }
            npc {
                id = "JavaIslandNewbieVillageOldMan"
                animationSetIndex = 1
                onClick = {
                }
                onTouch = {
                    if (it.id == HERO_ID) {
                    }
                }
                onInit = {
                }
            }
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
}
