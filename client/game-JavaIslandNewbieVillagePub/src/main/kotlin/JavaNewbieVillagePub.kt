import com.bytelegend.app.client.api.Character
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScriptHelpers
import com.bytelegend.app.client.api.HERO_ID
import com.bytelegend.app.shared.JAVA_ISLAND
import com.bytelegend.app.shared.JAVA_ISLAND_NEWBIE_VILLAGE_PUB
import kotlinx.browser.window

val gameRuntime = window.asDynamic().gameRuntime.unsafeCast<GameRuntime>()

fun main() {
    gameRuntime.sceneContainer.getSceneById(JAVA_ISLAND_NEWBIE_VILLAGE_PUB).apply {
        val helpers = GameScriptHelpers(this)
        objects {
            mapEntrance {
                destMapId = JAVA_ISLAND
            }

            npc {
                val girlId = "JavaIslandNewbieVillagePubGirl"
                id = girlId
                sprite = "JavaIslandNewbieVillagePubGirl-sprite"
                onClick = helpers.standardNpcSpeech(girlId) {
                    scripts {
                        speech {
                            objectId = girlId
                            contentHtmlId = "PleaseTakeALookAtThatBook"
                        }
                    }
                }
            }

            npc {
                val engineerId = "JavaIslandNewbieVillagePubTestEngineer"
                id = engineerId
                sprite = "JavaIslandNewbieVillagePubTestEngineer-sprite"
                onClick = helpers.standardNpcSpeech(engineerId) {
                    scripts {
                        speech(HERO_ID, "HiBroCoolOutfit")
                        speech(engineerId, "ImAProgrammer")
                        speech(HERO_ID, "WhatIsProgrammer")
                        speech(engineerId, "ProgrammerNeedsLogic")
                        speech(HERO_ID, "LogicThinker")
                        speech(engineerId, "HardToExplain")
                        speech(HERO_ID, "YesIHaveDog")
                        speech(engineerId, "YouLoveAnimal")
                        speech(HERO_ID, "OfCourse")
                        speech(engineerId, "YouMustLoveYourKids")
                        speech(HERO_ID, "Right")
                        speech(engineerId, "YouHaveAWife")
                        speech(HERO_ID, "YesIHaveAWife")
                        speech(engineerId, "NowItsYourTurnToPractise")
                        speech(HERO_ID, "DoYouHaveADog")
                        speech(engineerId, "NoIDontHaveDog")
                        speech(HERO_ID, "YouAreGay", arrow = false)
                    }
                }
            }

            npc {
                val bartenderId = "JavaIslandNewbieVillagePubBartender"
                id = bartenderId
                sprite = "JavaIslandNewbieVillagePubBartender-sprite"
                onClick = click@{
                    if (gameRuntime.hero == null) {
                        return@click
                    }
                    val hero = gameRuntime.hero!!
                    val bartender = objects.getById<Character>(bartenderId)

                    if (hero.gridCoordinate.x == bartender.gridCoordinate.x && bartender.gridCoordinate.y + 2 == hero.gridCoordinate.y) {
                        hero.direction = helpers.faceDirectionOf(bartender, hero)
                        scripts {
                            speech(bartenderId, "PleaseTakeALookAtThatBook")
                        }
                        return@click
                    }

                    val distance = helpers.distanceOf(HERO_ID, bartenderId)
                    if (distance == 1) {
                        scripts {
                            speech(bartenderId, "GetOut")
                        }
                    } else {
                        scripts {
                            speech(bartenderId, "ICantHearYou")
                        }
                    }
                }
            }
        }
    }
}
