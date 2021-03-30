import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScriptHelpers
import com.bytelegend.app.client.api.HERO_ID
import com.bytelegend.app.shared.JAVA_ISLAND
import com.bytelegend.app.shared.JAVA_ISLAND_NEWBIE_VILLAGE_PUB
import com.bytelegend.app.shared.START_BYTELEGEND_MISSION_ID
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

            sprite {
                id = "GitHubExplanationBook"
                spriteId = "GitHubExplanationBook-sprite"
                onClick = {
                    scripts {
                    }
                }
            }

//            npc {
//                id = "JavaIslandNewbieVillageOldMan"
//                spriteId = "JavaIslandNewbieVillageOldMan-sprite"
//                onClick = {
//                    scripts {
//                        speech {
//                            objectId = "JavaIslandNewbieVillageOldMan"
//                            contentHtmlId = "PleaseTakeALookAtThatBook"
//                        }
//
//                        starFly("JavaIslandNewbieVillageOldMan")
//                    }
//                }
//            }

            npc {
                val guardId = "JavaIslandNewbieVillagePubGuard"
                val guardMoveDestPoint = objects.getById<GameMapPoint>("JavaNewbieVilllagePubEntranceGuardDestination").point
                id = guardId
                spriteId = "JavaIslandNewbieVillagePubGuard-sprite"
                onInit = {
                    if (missions.missionAccomplished(START_BYTELEGEND_MISSION_ID)) {
                        helpers.getCharacter(guardId).gridCoordinate = guardMoveDestPoint
                    }
                }

                onClick = helpers.standardNpcSpeech(guardId) {
                    when {
                        !missions.missionAccomplished(START_BYTELEGEND_MISSION_ID) -> {
                            scripts {
                                speech(guardId, "StarCondition", arrayOf("1", "0"), true)
                                speech(HERO_ID, "WhereToFindStar", arrow = true)
                            }
                        }
                        helpers.getCharacter(guardId).gridCoordinate == guardMoveDestPoint -> {
                            scripts {
                                speech(guardId, "NiceDayHuh")
                            }
                        }
                        else -> {
//                            scripts {
//                                starFly(HERO_ID, 1)
//                                characterMove(guardId, guardMoveDestPoint)
//                            }
                        }
                    }
                }
            }
        }
    }
}
