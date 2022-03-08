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

import com.bytelegend.app.client.api.DynamicSprite
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.HERO_ID
import com.bytelegend.app.client.utils.GameScriptHelpers
import com.bytelegend.app.client.utils.configureChestOpenByKey
import com.bytelegend.app.shared.GIT_ISLAND
import com.bytelegend.app.shared.JAVA_ISLAND
import kotlinx.browser.window

fun main() {
    val gameRuntime = window.asDynamic().gameRuntime.unsafeCast<GameRuntime>()
    gameRuntime.sceneContainer.getSceneById(GIT_ISLAND).apply {
        dockSailor()
        installGitStone()
        configureTempBlocker()
        // configureBronzeGitMedalMission()
    }
}

fun GameScene.configureTempBlocker() = objects {
    dynamicSprite {
        id = "TempBlocker"
        gridCoordinate = objects.getPointById("TempBlockerPoint")
        sprite = "Stone"
        onClick = {
            scripts {
                speech("TempBlocker", "GitIslandOnlyOpenHere", arrow = false)
            }
        }
    }
}

fun GameScene.installGitStone() = objects {
    configureChestOpenByKey("install-git")
}

fun GameScene.configureBronzeGitMedalMission() {
    val gitCommitPushSprite = objects.getById<DynamicSprite>("git-commit-and-push")
    val helpers = GameScriptHelpers(this)

    helpers.configureAnimation(gitCommitPushSprite, 2)
    helpers.addMissionRepaintCallback(gitCommitPushSprite) {
        helpers.configureAnimation(gitCommitPushSprite, 2)
    }
}

fun GameScene.dockSailor() = objects {
    val helpers = GameScriptHelpers(this@dockSailor)
    npc {
        val sailorId = "DockSailor"
        val startPoint = objects.getPointById("$sailorId-point")
        id = sailorId
        sprite = "$sailorId-sprite"

        onInit = {
            helpers.getCharacter(sailorId).gridCoordinate = startPoint
        }

        onClick = helpers.standardNpcSpeech(
            sailorId,
            {
                scripts {
                    if (gameRuntime.heroPlayer.items.contains("key:GitIsland:install-git")) {
                        speech {
                            speakerId = sailorId
                            contentHtmlId = "YouDidNotUseYourChestKey"
                            arrow = false
                        }
                    } else {
                        speech {
                            speakerId = sailorId
                            contentHtmlId = "WouldYouLikeToJavaIsland"
                            arrow = false
                            showYesNo = true
                            onYes = {
                                scripts {
                                    enterScene(JAVA_ISLAND, {
                                        scripts {
                                            characterEnterVehicleAndMoveToMap(
                                                HERO_ID,
                                                "Boat",
                                                helpers.searchVehiclePath("GitIslandToJavaIsland"),
                                                JAVA_ISLAND
                                            )
                                        }
                                    }, {
                                        scripts {
                                            speech(sailorId, "SorryYouDontHaveEnoughCoin", arrow = false)
                                        }
                                    })
                                }
                            }
                        }
                    }
                }
            }
        ) {
            scripts {
                speech(sailorId, "ICantHearYou", arrow = false)
            }
        }
    }
}
