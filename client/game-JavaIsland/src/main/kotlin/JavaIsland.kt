import com.bytelegend.app.client.api.AbstractStaticLocationSprite
import com.bytelegend.app.client.api.GameObjectContainer
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScriptHelpers
import com.bytelegend.app.client.api.HERO_ID
import com.bytelegend.app.client.api.ScriptsBuilder
import com.bytelegend.app.shared.BEGINNER_GUIDE_UNFINISHED_STATE
import com.bytelegend.app.shared.HumanReadableCoordinate
import com.bytelegend.app.shared.JAVA_COFFEE
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

            npc {
                val oldManId = "JavaIslandNewbieVillageOldMan"
                val oldManStartPoint = objects.getById<GameMapPoint>("$oldManId-point").point
                val oldManDestination = objects.getById<GameMapPoint>("$oldManId-destination").point
                id = oldManId
                sprite = "$oldManId-sprite"
                onInit = {
                    if (gameRuntime.heroPlayer.items.contains(JAVA_COFFEE)) {
                        helpers.getCharacter(oldManId).gridCoordinate = oldManDestination
                    } else {
                        helpers.getCharacter(oldManId).gridCoordinate = oldManStartPoint
                    }
                }
                onClick = helpers.standardNpcSpeech(oldManId) {
                    if (gameRuntime.heroPlayer.items.contains(JAVA_COFFEE)) {
                        if (helpers.getCharacter(oldManId).gridCoordinate == oldManStartPoint) {
                            scripts {
                                speech(oldManId, "CanYouPleaseGrabACoffee", arrow = false)
                            }
                        } else {
                        }
                    } else {
                        scripts {
                            speech(oldManId, "CanYouPleaseGrabACoffee", arrow = false)
                        }
                    }
                }
            }

            npc {
                val guardId = "JavaIslandNewbieVillagePubGuard"
                val guardStartPoint = objects.getById<GameMapPoint>("JavaNewbieVilllagePubEntranceGuard-point").point
                val guardMoveDestPoint = objects.getById<GameMapPoint>("JavaNewbieVilllagePubEntranceGuard-destination").point
                id = guardId
                sprite = "JavaIslandNewbieVillagePubGuard-sprite"
                onInit = {
                    when {
                        states.hasState(BEGINNER_GUIDE_UNFINISHED_STATE) -> {
                            helpers.getCharacter(guardId).gridCoordinate = guardStartPoint
                            scripts {
                                speech(guardId, "DoYouPreferToBeMediocre")
                            }
                        }
                        playerMissions.missionAccomplished(START_BYTELEGEND_MISSION_ID) -> {
                            helpers.getCharacter(guardId).gridCoordinate = guardMoveDestPoint
                        }
                        else -> {
                            helpers.getCharacter(guardId).gridCoordinate = guardStartPoint
                        }
                    }
                }

                onClick = helpers.standardNpcSpeech(guardId) {
                    if (helpers.getCharacter(guardId).gridCoordinate == guardStartPoint) {
                        when {
                            states.hasState(BEGINNER_GUIDE_UNFINISHED_STATE) && playerMissions.missionAccomplished(START_BYTELEGEND_MISSION_ID) -> {
                                // Player star first but hasn't finished beginner guide, show them
                                scripts {
                                    startBeginnerGuide()
                                    speech(guardId, "NiceJob", arrayOf("1", "0"))
                                    characterMove(guardId, guardMoveDestPoint)
                                }
                            }
                            states.hasState(BEGINNER_GUIDE_UNFINISHED_STATE) -> {
                                scripts {
                                    talkAboutFirstStar(guardId, objects)
                                    startBeginnerGuide()
                                }
                            }
                            playerMissions.missionAccomplished(START_BYTELEGEND_MISSION_ID) -> {
                                // mission accomplished, let's celebrate!
                                scripts {
                                    speech(guardId, "NiceJob", arrayOf("1", "0"))
                                    characterMove(guardId, guardMoveDestPoint)
                                }
                            }
                            else -> {
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
                    } else {
                        scripts {
                            speech(guardId, "NiceDayHub", arrow = false)
                        }
                    }
                }
            }
        }
    }
}

fun ScriptsBuilder.talkAboutFirstStar(guardId: String, objects: GameObjectContainer) {
    speech(guardId, "StarCondition", arrayOf("1", "0"))
    speech(HERO_ID, "WhereToFindStar")
    speech(
        guardId, "IDontKnowTakeALookAtStarBytelegend",
        arrayOf(
            HumanReadableCoordinate(objects.getById<AbstractStaticLocationSprite>("star-bytelegend").gridCoordinate).toString()
        )
    )
}
