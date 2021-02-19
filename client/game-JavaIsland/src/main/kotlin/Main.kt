import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.shared.JAVA_ISLAND
import com.bytelegend.app.shared.JAVA_ISLAND_NEWBIE_VILLAGE_PUB
import kotlinx.browser.window

val gameRuntime = window.asDynamic().gameRuntime.unsafeCast<GameRuntime>()

fun main() {
    gameRuntime.sceneContainer.getSceneById(JAVA_ISLAND).apply {
        objects {
            mapEntrance {
                destMapId = JAVA_ISLAND_NEWBIE_VILLAGE_PUB
            }

            noticeboard {
                id = "JavaIslandNewbieVillageNoticeboard"
                spriteId = "Noticeboard-sprite"
            }

            sprite {
                id = "GitHubNoticeboard"
                spriteId = "GitHubNoticeboard-sprite"
                onClick = {
                    gameRuntime.modalController.showModal("WhyGitHubContentHtml", "WhyGitHubTitle")
                }
                clickable = true
                glow = true
            }

            npc {
                id = "JavaIslandNewbieVillageOldMan"
                spriteId = "JavaIslandNewbieVillageOldMan-sprite"
                onClick = {
                    scripts {
                        disableUserMouse()
                        speech {
                            objectId = "JavaIslandNewbieVillageOldMan"
                            contentHtmlId = "PleaseTakeALookAtThatBook"
                        }
                        enableUserMouse()
                    }
                }
            }

            npc {
                id = "JavaIslandNewbieVillagePubGuard"
                spriteId = "JavaIslandNewbieVillagePubGuard-sprite"
                onClick = {
                    window.alert("Click guard!")
                }
            }
        }
    }
}
