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
import com.bytelegend.app.client.api.Character
import com.bytelegend.app.client.api.DynamicSprite
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.HERO_ID
import com.bytelegend.app.client.api.HasBouncingTitle
import com.bytelegend.app.client.api.openMissionModalEvent
import com.bytelegend.app.client.utils.GameScriptHelpers
import com.bytelegend.app.client.utils.animationWithFixedInterval
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
        if (gameRuntime.heroPlayer.map == map.id) {
            val dest = installJavaMission.gridCoordinate + GridCoordinate(-1, 1)
            val movePath = gameRuntime.hero!!.searchPath(dest)
            if (movePath.isEmpty()) {
                gameRuntime.eventBus.emit(openMissionModalEvent(id), null)
            } else {
                installJavaMission.animation = installJavaMission.mapDynamicSprite.animationWithFixedInterval(300, 0, 3, false)
                gameRuntime.hero!!.moveAlong(movePath) {
                    gameRuntime.eventBus.emit(openMissionModalEvent(id), null)
                }
            }
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
    if (challengeAnswers.challengeAccomplished(COFFEE_MACHINE_MISSION)) {
        scripts {
            speech(bartenderId, "HaveYouSeenTheOracle", arrow = false)
            speech(HERO_ID, "NoIWillGoToSeeTheOracleRightNow", arrow = false)
        }
    } else {
        scripts {
            speech(HERO_ID, "CanIHaveACoffee", arrow = false)
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
    val heroName = this@pubGirl.gameRuntime.heroPlayer.nickname
    npc {
        val girlId = "JavaIslandNewbieVillagePubGirl"
        val bartenderId = "JavaIslandNewbieVillagePubBartender"
        id = girlId
        sprite = "$girlId-sprite"
        onInit = {
            helpers.getCharacter(girlId).gridCoordinate = objects.getPointById("$girlId-point")
        }
        onClick = helpers.standardNpcSpeech(girlId) {
            if (challengeAnswers.challengeAccomplished(COFFEE_MACHINE_MISSION)) {
                scripts {
                    speech(HERO_ID, "WhyEveryoneIsTalkingAboutEndOfWorld")
                    speech(girlId, "WhatsYourName")
                    speech(HERO_ID, "YesMyNameIs", args = arrayOf(heroName))
                    speech(girlId, "OhMyGod")
                    speech(bartenderId, "StayAwayFromMyDaughter")
                    speech(girlId, "NoHeIsTheOne", args = arrayOf(heroName))
                    speech(HERO_ID, "WhatOracle")
                    speech(bartenderId, "JavaIslandWasBeautifulIsland", args = arrayOf(heroName))
                    speech(girlId, "TodayIsFullMoon")
                    speech(HERO_ID, "HowShallIDo")
                    speech(girlId, "GoToSeeOracle", arrow = false)
                }
            } else {
                scripts {
                    val point = HumanReadableCoordinate(objects.getById<GameObject>("install-java").unsafeCast<CoordinateAware>().gridCoordinate).toString()
                    speech(HERO_ID, "CanIHaveACoffee")
                    speech(girlId, "SeeTheCoffeeMachine", args = arrayOf(point))
                    speech(HERO_ID, "WhatEndOfWorld")
                    speech(girlId, "GoToMakeACoffeeAndIWillTellYou", arrow = false)
                }
            }
        }
    }
}
