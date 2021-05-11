import com.bytelegend.app.client.api.AbstractStaticLocationSprite
import com.bytelegend.app.client.api.GameObjectContainer
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.GameScriptHelpers
import com.bytelegend.app.client.api.HERO_ID
import com.bytelegend.app.client.api.ScriptsBuilder
import com.bytelegend.app.shared.COFFEE
import com.bytelegend.app.shared.HumanReadableCoordinate
import com.bytelegend.app.shared.JAVA_ISLAND
import com.bytelegend.app.shared.JAVA_ISLAND_NEWBIE_VILLAGE_PUB
import com.bytelegend.app.shared.objects.GameMapPoint
import kotlinx.browser.window

const val BEGINNER_GUIDE_FINISHED_STATE = "BeginnerGuideFinished"
const val NEWBIE_VILLAGE_OLD_MAN_GOT_COFFEE = "OldManGotCoffee"
const val NEWBIE_VILLAGE_NOTICEBOARD_MISSION_ID = "remember-brave-people"
const val STAR_BYTELEGEND_MISSION_ID = "star-bytelegend"

val gameRuntime = window.asDynamic().gameRuntime.unsafeCast<GameRuntime>()

fun main() {
    gameRuntime.sceneContainer.getSceneById(JAVA_ISLAND).apply {
        objects {
            mapEntrance {
                destMapId = JAVA_ISLAND_NEWBIE_VILLAGE_PUB
            }

            pubGuard()
            newbieVillageOldMan()
            newbieVillageHead()
            newbieVillageSailor()
            newbieVillageBridgeSoldier()
            invitationBox()
        }
    }
}

fun GameScene.pubGuard() = objects {
    val helpers = GameScriptHelpers(this@pubGuard)

    npc {
        val guardId = "JavaIslandNewbieVillagePubGuard"
        val guardStartPoint = objects.getById<GameMapPoint>("JavaNewbieVillagePubEntranceGuard-point").point
        val guardMoveDestPoint = objects.getById<GameMapPoint>("JavaNewbieVillagePubEntranceGuard-destination").point
        id = guardId
        sprite = "JavaIslandNewbieVillagePubGuard-sprite"
        onInit = {
            when {
                !gameRuntime.heroPlayer.isAnonymous && !gameRuntime.heroPlayer.states.containsKey(BEGINNER_GUIDE_FINISHED_STATE) -> {
                    helpers.getCharacter(guardId).gridCoordinate = guardStartPoint
                    scripts {
                        speech(guardId, "DoYouPreferToBeMediocre")
                    }
                }
                playerMissions.missionAccomplished(STAR_BYTELEGEND_MISSION_ID) -> {
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
                    !gameRuntime.heroPlayer.states.containsKey(BEGINNER_GUIDE_FINISHED_STATE) && playerMissions.missionAccomplished(STAR_BYTELEGEND_MISSION_ID) -> {
                        // Player star first but hasn't finished beginner guide, show them
                        scripts {
                            startBeginnerGuide()
                            putState(BEGINNER_GUIDE_FINISHED_STATE)
                            speech(guardId, "NiceJob", arrayOf("1", "0"))
                            characterMove(guardId, guardMoveDestPoint)
                        }
                    }
                    !gameRuntime.heroPlayer.states.containsKey(BEGINNER_GUIDE_FINISHED_STATE) -> {
                        scripts {
                            talkAboutFirstStar(guardId, objects)
                            startBeginnerGuide()
                            putState(BEGINNER_GUIDE_FINISHED_STATE)
                        }
                    }
                    playerMissions.missionAccomplished(STAR_BYTELEGEND_MISSION_ID) -> {
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
                                    HumanReadableCoordinate(objects.getById<AbstractStaticLocationSprite>(STAR_BYTELEGEND_MISSION_ID).gridCoordinate).toString()
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

fun GameScene.newbieVillageOldMan() = objects {
    val helpers = GameScriptHelpers(this@newbieVillageOldMan)

    npc {
        val oldManId = "JavaIslandNewbieVillageOldMan"
        val oldManStartPoint = objects.getById<GameMapPoint>("$oldManId-point").point
        val oldManDestination = objects.getById<GameMapPoint>("$oldManId-destination").point
        id = oldManId
        sprite = "$oldManId-sprite"
        onInit = {
            if (gameRuntime.heroPlayer.states.containsKey(NEWBIE_VILLAGE_OLD_MAN_GOT_COFFEE)) {
                helpers.getCharacter(oldManId).gridCoordinate = oldManDestination
            } else {
                helpers.getCharacter(oldManId).gridCoordinate = oldManStartPoint
            }
        }
        onClick = helpers.standardNpcSpeech(oldManId) {
            when {
                gameRuntime.heroPlayer.states.containsKey(NEWBIE_VILLAGE_OLD_MAN_GOT_COFFEE) -> {
                    scripts {
                        speech(oldManId, "NiceDayHub", arrow = false)
                    }
                }
                gameRuntime.heroPlayer.items.contains(COFFEE) -> {
                    scripts {
                        speech(oldManId, "ThankYouForYourCoffee")
                        putState(NEWBIE_VILLAGE_OLD_MAN_GOT_COFFEE)
                        // TODO non-atomic operation
                        removeItem(COFFEE)
                        characterMove(oldManId, oldManDestination)
                    }
                }
                else -> {
                    scripts {
                        speech(oldManId, "CanYouPleaseGrabACoffee", arrow = false)
                    }
                }
            }
        }
    }
}

fun GameScene.newbieVillageHead() = objects {
    val helpers = GameScriptHelpers(this@newbieVillageHead)
    npc {
        val villageHeadId = "JavaIslandNewbieVillageHead"
        val startPoint = objects.getById<GameMapPoint>("$villageHeadId-point").point
        val destPoint = objects.getById<GameMapPoint>("$villageHeadId-destination").point
        id = villageHeadId
        sprite = "$villageHeadId-sprite"

        onInit = {
            if (playerMissions.missionAccomplished(NEWBIE_VILLAGE_NOTICEBOARD_MISSION_ID)) {
                helpers.getCharacter(villageHeadId).gridCoordinate = startPoint
            } else {
                helpers.getCharacter(villageHeadId).gridCoordinate = destPoint
            }
        }

        onClick = helpers.standardNpcSpeech(villageHeadId) {
            if (helpers.getCharacter(villageHeadId).gridCoordinate == startPoint) {
                if (playerMissions.missionAccomplished(NEWBIE_VILLAGE_NOTICEBOARD_MISSION_ID)) {
                    scripts {
                        speech(villageHeadId, "OutsideWorldIsDangerousButIHaveToLetYouGo")
                        speech(villageHeadId, "GoodLuck", arrow = false)
                        characterMove(villageHeadId, destPoint)
                    }
                } else {
                    val noticeboardPoint = objects.getById<GameMapPoint>(NEWBIE_VILLAGE_NOTICEBOARD_MISSION_ID).point

                    scripts {
                        speech(villageHeadId, "OutsideWorldIsDangerous")
                        speech(HERO_ID, "ButIHaveTo")
                        speech(villageHeadId, "LeaveYourName", args = arrayOf(HumanReadableCoordinate(noticeboardPoint).toString()), arrow = false)
                    }
                }
            } else {
                scripts {
                    speech(villageHeadId, "GoodLuck", arrow = false)
                }
            }
        }
    }
}

fun GameScene.newbieVillageSailor() = objects {
    val helpers = GameScriptHelpers(this@newbieVillageSailor)
    npc {
        val sailorId = "JavaIslandNewbieVillageSailor"
        val startPoint = objects.getById<GameMapPoint>("$sailorId-point").point
        id = sailorId
        sprite = "$sailorId-sprite"

        onInit = {
            helpers.getCharacter(sailorId).gridCoordinate = startPoint
        }

        onClick = helpers.standardNpcSpeech(
            sailorId,
            {
                scripts {
                    speech(sailorId, "ImSupposedToTakeYouToGitIsland", arrow = false)
                }
            }
        ) {
            scripts {
                speech(sailorId, "ImSupposedToTakeYouToGitIsland", arrow = false)
            }
        }
    }
}

fun GameScene.invitationBox() {}
fun GameScene.newbieVillageBridgeSoldier() = objects {
    val helpers = GameScriptHelpers(this@newbieVillageBridgeSoldier)
    npc {
        val soldierId = "JavaIslandNewbieVillageBridgeSoldier"
        val startPoint = objects.getById<GameMapPoint>("$soldierId-point").point
        val destPoint = objects.getById<GameMapPoint>("$soldierId-destination").point
        id = soldierId
        sprite = "$soldierId-sprite"

        onClick = helpers.standardNpcSpeech(
            soldierId,
            {
                scripts {
                    speech(soldierId, "ImSupposedToDoSomething", arrow = false)
                }
            }
        ) {
            scripts {
                speech(soldierId, "ImSupposedToDoSomething", arrow = false)
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
