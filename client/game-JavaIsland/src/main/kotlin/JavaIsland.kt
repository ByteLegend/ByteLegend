import com.bytelegend.app.client.api.AbstractStaticLocationSprite
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScriptHelpers
import com.bytelegend.app.client.api.HERO_ID
import com.bytelegend.app.shared.HumanReadableCoordinate
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

//            npc {
//                id = "JavaIslandNewbieVillageOldMan"
//                sprite = "JavaIslandNewbieVillageOldMan-sprite"
//                onInit = {
//
//                }
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
                val guardStartPoint = objects.getById<GameMapPoint>("JavaNewbieVilllagePubEntranceGuard-point").point
                val guardMoveDestPoint = objects.getById<GameMapPoint>("JavaNewbieVilllagePubEntranceGuard-destination").point
                id = guardId
                sprite = "JavaIslandNewbieVillagePubGuard-sprite"
                onInit = {
                    if (playerMissions.missionAccomplished(START_BYTELEGEND_MISSION_ID)) {
                        helpers.getCharacter(guardId).gridCoordinate = guardMoveDestPoint
                    } else {
                        helpers.getCharacter(guardId).gridCoordinate = guardStartPoint
                    }
                }

                onClick = helpers.standardNpcSpeech(guardId) {
                    if (playerMissions.missionAccomplished(START_BYTELEGEND_MISSION_ID)) {
                        if (helpers.getCharacter(guardId).gridCoordinate == guardStartPoint) {
                            // mission accomplished, let's celebrate!
                            scripts {
                                speech(guardId, "NiceJob", arrayOf("1", "0"))
                                characterMove(guardId, guardMoveDestPoint)
                            }
                        } else {
                            scripts {
                                speech(guardId, "NiceDayHub", arrow = false)
                            }
                        }
                    } else {
                        if (helpers.getCharacter(guardId).gridCoordinate == guardStartPoint) {
                            scripts {
                                speech(guardId, "StarCondition", arrayOf("1", "0"))
                                speech(HERO_ID, "WhereToFindStar")
                                speech(
                                    guardId, "IDontKnowTakeALookAtStarBytelegend",
                                    arrayOf(
                                        HumanReadableCoordinate(objects.getById<AbstractStaticLocationSprite>("star-bytelegend").gridCoordinate).toString()
                                    ),
                                    arrow = false
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
