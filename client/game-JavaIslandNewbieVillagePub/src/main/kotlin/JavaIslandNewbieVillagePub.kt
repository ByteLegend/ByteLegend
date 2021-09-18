/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.bytelegend.app.client.api.AnimationFrame
import com.bytelegend.app.client.api.Character
import com.bytelegend.app.client.api.DynamicSprite
import com.bytelegend.app.client.api.FramePlayingAnimation
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.GameScriptHelpers
import com.bytelegend.app.client.api.HERO_ID
import com.bytelegend.app.client.api.HasBouncingTitle
import com.bytelegend.app.client.api.openMissionModalEvent
import com.bytelegend.app.shared.COFFEE_MACHINE_MISSION
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.HumanReadableCoordinate
import com.bytelegend.app.shared.JAVA_ISLAND_NEWBIE_VILLAGE_PUB
import com.bytelegend.app.shared.objects.CoordinateAware
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.mapEntranceId
import kotlinx.browser.window

val gameRuntime = window.asDynamic().gameRuntime.unsafeCast<GameRuntime>()

fun main() {
    gameRuntime.sceneContainer.getSceneById(JAVA_ISLAND_NEWBIE_VILLAGE_PUB).apply {
        objects {
            pubEngineer()
            pubGirl()
            pubBartender()

            configureCoffeeMachine()
            objects.getById<GameObject>(mapEntranceId("JavaIslandNewbieVillagePub", "JavaIsland"))
                .unsafeCast<HasBouncingTitle>().bouncingTitleEnabled = false
        }
    }
}

fun GameScene.configureCoffeeMachine() {
    val id = "install-java"
    val installJavaMission = objects.getById<DynamicSprite>(id)
    installJavaMission.onClickFunction = {
        if (gameRuntime.heroPlayer.map == map.id &&
            GameScriptHelpers(this).distanceOf(HERO_ID, id) <= 2
        ) {
            installJavaMission.animation = FramePlayingAnimation(
                frames = arrayOf(
                    AnimationFrame(0, 300),
                    AnimationFrame(1, 300),
                    AnimationFrame(2, 300),
                ),
                repeating = false
            )
            window.setTimeout({
                gameRuntime.eventBus.emit(openMissionModalEvent(id), null)
            }, 1000)
        } else {
            gameRuntime.eventBus.emit(openMissionModalEvent(id), null)
        }
    }
}

fun GameScene.pubBartender() = objects {
    val helpers = GameScriptHelpers(this@pubBartender)
    npc {
        val bartenderId = "JavaIslandNewbieVillagePubBartender"
        val bartenderPoint = objects.getPointById("$bartenderId-point")
        id = bartenderId
        sprite = "$bartenderId-sprite"
        onInit = {
            helpers.getCharacter(bartenderId).gridCoordinate = bartenderPoint
        }
        onClick = {
            if (gameRuntime.hero != null && objects.getByIdOrNull<Character>(HERO_ID) != null) {
                val dest = bartenderPoint + GridCoordinate(0, 3)
                val hero = objects.getById<Character>(HERO_ID)
                if (hero.gridCoordinate == dest) {
                    // Don't need to move
                    talkAboutCoffeeWithBartender(hero, bartenderId)
                }
                val movePath = hero.searchPath(dest)
                if (movePath.isNotEmpty()) {
                    hero.moveAlong(movePath) {
                        talkAboutCoffeeWithBartender(hero, bartenderId)
                    }
                }
            }
        }
    }
}

fun GameScene.talkAboutCoffeeWithBartender(hero: Character, bartenderId: String) {
    hero.direction = Direction.UP
    if (playerChallenges.challengeAccomplished(COFFEE_MACHINE_MISSION)) {
        scripts {
            speech(bartenderId, "DidYouEliminateAllBugs", arrow = false)
            speech(HERO_ID, "NoIWillDoRightNow", arrow = false)
        }
    } else {
        scripts {
            speech(HERO_ID, "DoYouHaveCoffee", arrow = false)
            speech(bartenderId, "WeDontSellCoffee", arrow = false)
        }
    }
}

fun GameScene.pubEngineer() = objects {
    val helpers = GameScriptHelpers(this@pubEngineer)
    npc {
        val engineerId = "JavaIslandNewbieVillagePubEngineer"
        id = engineerId
        sprite = "$engineerId-sprite"
        onInit = {
            helpers.getCharacter(engineerId).gridCoordinate = objects.getPointById("$engineerId-point")
        }
        onClick = helpers.standardNpcSpeech(engineerId) {
            scripts {
                speech(HERO_ID, "HiBroYouAreCool")
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
}

fun GameScene.pubGirl() = objects {
    val helpers = GameScriptHelpers(this@pubGirl)
    npc {
        val girlId = "JavaIslandNewbieVillagePubGirl"
        val bartenderId = "JavaIslandNewbieVillagePubBartender"
        id = girlId
        sprite = "$girlId-sprite"
        onInit = {
            helpers.getCharacter(girlId).gridCoordinate = objects.getPointById("$girlId-point")
        }
        onClick = helpers.standardNpcSpeech(girlId) {
            if (playerChallenges.challengeAccomplished(COFFEE_MACHINE_MISSION)) {
                scripts {
                    speech(HERO_ID, "WillYouGoOutWithMe")
                    speech(bartenderId, "StayAwayFromMyDaughter")
                    speech(girlId, "SorryMyFatherDoesntAllow")
                    speech(HERO_ID, "Why")
                    speech(girlId, "HugeBugsOnJavaIsland")
                    speech(HERO_ID, "Bugs")
                    speech(girlId, "ThatsWhyWeDontGoOutAfterDark")
                    speech(HERO_ID, "IWillEliminateAllBugs")
                    speech(girlId, "HmmmYes", arrow = false)
                }
            } else {
                scripts {
                    val point = HumanReadableCoordinate(objects.getById<GameObject>("install-java").unsafeCast<CoordinateAware>().gridCoordinate).toString()
                    speech(girlId, "SeeTheCoffeeMachine", args = arrayOf(point), arrow = false)
                }
            }
        }
    }
}
