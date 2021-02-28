import com.bytelegend.app.client.api.Character
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScriptHelpers
import com.bytelegend.app.client.api.HERO_ID
import com.bytelegend.app.shared.JAVA_ISLAND
import com.bytelegend.app.shared.JAVA_ISLAND_NEWBIE_VILLAGE_PUB
import com.bytelegend.app.shared.objects.GameMapPoint
import kotlinx.browser.window

val gameRuntime = window.asDynamic().gameRuntime.unsafeCast<GameRuntime>()

fun main() {
    gameRuntime.sceneContainer.getSceneById(JAVA_ISLAND).apply {
        val helpers = GameScriptHelpers(this)
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
                        speech {
                            objectId = "JavaIslandNewbieVillageOldMan"
                            contentHtmlId = "PleaseTakeALookAtThatBook"
                        }

                        starFly("JavaIslandNewbieVillageOldMan")
                    }
                }
            }

            npc {
                val guardId = "JavaIslandNewbieVillagePubGuard"
                id = guardId
                spriteId = "JavaIslandNewbieVillagePubGuard-sprite"
                onInit = {
                    objects.getById<Character>(guardId).gridCoordinate =
                        objects.getById<GameMapPoint>("JavaNewbieVilllagePubEntranceGuardDestination").point
                }

                onClick = helpers.standardNpcSpeech(guardId) {
                    scripts {
                        speech {
                            objectId = guardId
                            contentHtmlId = "StarCondition"
                            args = arrayOf("1", "0")
                            arrow = true
                        }
                        speech {
                            objectId = HERO_ID
                            contentHtmlId = "WhereToFindStar"
                            arrow = true
                        }
                    }
                }
            }
        }
    }
}
