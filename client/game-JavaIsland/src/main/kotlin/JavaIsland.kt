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
import com.bytelegend.app.client.api.AnimationBuilder
import com.bytelegend.app.client.api.AnimationFrame
import com.bytelegend.app.client.api.DynamicSprite
import com.bytelegend.app.client.api.FramePlayingAnimation
import com.bytelegend.app.client.api.GameMission
import com.bytelegend.app.client.api.GameObjectContainer
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.HERO_ID
import com.bytelegend.app.client.api.HasBouncingTitle
import com.bytelegend.app.client.api.ScriptsBuilder
import com.bytelegend.app.client.api.StaticFrame
import com.bytelegend.app.client.misc.uuid
import com.bytelegend.app.shared.COFFEE
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.GIT_ISLAND
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.INVITER_ID_STATE
import com.bytelegend.app.shared.JAVA_ISLAND
import com.bytelegend.app.shared.JAVA_ISLAND_SENIOR_JAVA_CASTLE
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.objects.CoordinateAware
import com.bytelegend.app.shared.objects.GameMapDynamicSprite
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.app.shared.objects.mapEntranceId
import com.bytelegend.app.client.utils.GameScriptHelpers
import com.bytelegend.app.client.utils.animationWithFixedInterval
import com.bytelegend.app.client.utils.configureBookSprite
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.Element
import org.w3c.dom.get
import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.sqrt

const val BEGINNER_GUIDE_FINISHED_STATE = "BeginnerGuideFinished"
const val NEWBIE_VILLAGE_OLD_MAN_GOT_COFFEE = "OldManGotCoffee"
const val NEWBIE_VILLAGE_NOTICEBOARD_MISSION_ID = "remember-brave-people-challenge"
const val STAR_BYTELEGEND_MISSION_ID = "star-bytelegend"
const val STAR_BYTELEGEND_CHALLENGE_ID = "star-bytelegend-challenge"

private const val SHOW_AD_MODAL = "show.ad.modal"

fun main() {
    val gameRuntime = window.asDynamic().gameRuntime.unsafeCast<GameRuntime>()

    gameRuntime.sceneContainer.getSceneById(JAVA_ISLAND).apply {
        objects {
            configureMissionTowers()
            configureStarByteLegendBook()
            configureInstallJavaIDEChest()

            pubGuard()
            newbieVillageOldMan()
            newbieVillageNoticeboard()
            newbieVillageHead()
            newbieVillageSailor()
            newbieVillageBridgeSoldier()
            invitationCodeBox()

            javaCloneRunDoor("clone-and-run-java-project", "clone-and-run-java-project-challenge")
            javaCloneRunDoor("clone-and-switch-branch", "clone-and-switch-branch-challenge")
            firstBugEvil()
            basicStructureStone(
                "JavaBasicStructure",
                "JavaBasicStructure-challenge-text",
                listOf("import-third-party-package", "import-class", "create-a-new-class")
            )
            commentDungeonNoticeboard()
            basicStructureStone(
                "JavaMethodField",
                "JavaMethodField-challenge-text",
                listOf("java-method", "java-method-overloading", "java-field", "java-method-invocation", "java-local-variable-scope", "java-method-recursion")
            )

            billboard()
            // #28a745
            missionCastle("java-todo-webapp", "white", "rgba(40,167,69,0.8)")
            missionCastle("java-blog-platform", "#856404", "yellow")
            // #007bff
            missionCastle("java-distributed-crawler", "white", "rgba(0,123,255,0.8)")
            // #dc3545
            missionCastle("java-e-commerce-website", "white", "rgba(220,53,69,0.8)")
            castleDoor()
            castleNoticeboard()
        }
    }
}

fun GameScene.commentDungeonNoticeboard() = objects {
    dynamicSprite {
        id = "CommentDungeonNoticeboard"
        gridCoordinate = objects.getPointById("CommentDungeonNoticeboard-point")
        onClick = {
            gameRuntime.modalController.showModal(gameRuntime.i("WhatIsDungeonExplanation"), gameRuntime.i("WhatIsDungeon"))
        }
    }
}

/**
 * Upon initialization and mission repaint events,
 * update the checkboxes and mission animation/blockers.
 */
fun GameScriptHelpers.updateCheckboxes(blockMissionId: String, blockTextId: String, missionIds: List<String>) {
    val html = gameScene.gameRuntime.i(blockTextId)
    val tmp = document.createElement("div")
    tmp.innerHTML = html

    val accomplishedMissionBefore = tmp.querySelectorAll("li>input[checked]").length
    missionIds.forEach {
        if (gameScene.challengeAnswers.missionAccomplished(it)) {
            val mission = gameScene.objects.getById<GameMission>(it)
            val title = gameScene.gameRuntime.i(mission.gameMapMission.title)
            val list = tmp.querySelectorAll("li")
            for (i in 0 until list.length) {
                val li = list[i]
                if (li?.textContent?.trim() == title) {
                    li.firstChild?.unsafeCast<Element>()?.setAttribute("checked", "")
                    break
                }
            }
        }
    }

    gameScene.gameRuntime.putText(blockTextId, tmp.innerHTML)

    val accomplishedMissionAfter = tmp.querySelectorAll("li>input[checked]").length
    val missionStone = gameScene.objects.getById<GameMission>(blockMissionId)
    if (accomplishedMissionAfter == missionIds.size) {
        missionStone.animation = StaticFrame(1)
    } else {
        missionStone.animation = StaticFrame(0)
    }
    if (accomplishedMissionBefore < missionIds.size && accomplishedMissionAfter == missionIds.size) {
        removeMissionBlocker(missionStone)
    }
}

fun GameScene.basicStructureStone(stoneMissionId: String, stoneTextId: String, missionIds: List<String>) {
    val helpers = GameScriptHelpers(this)
    helpers.updateCheckboxes(stoneMissionId, stoneTextId, missionIds)
    missionIds.forEach {
        val mission = objects.getById<DynamicSprite>(it)
        helpers.addMissionRepaintCallback(mission) {
            helpers.updateCheckboxes(stoneMissionId, stoneTextId, missionIds)
        }
    }
}

fun GameScene.firstBugEvil() = objects {
    val mission = objects.getById<DynamicSprite>("fix-simple-add")
    val helpers = GameScriptHelpers(this@firstBugEvil)
    helpers.configureAnimation(mission, 3)
    if (challengeAnswers.missionAccomplished("fix-simple-add")) {
        helpers.removeMissionBlocker(mission)
    }
    helpers.addMissionRepaintCallback(mission) {
        if (!it.wasAccomplished && it.newValue.accomplished) {
            helpers.removeMissionBlocker(mission)
        }
        helpers.configureAnimation(mission, 3)
    }
}

fun GameScene.javaCloneRunDoor(missionId: String, challengeId: String) {
    val mission = objects.getById<DynamicSprite>(missionId)
    val helpers = GameScriptHelpers(this)
    if (challengeAnswers.challengeAccomplished(challengeId)) {
        helpers.removeMissionBlocker(mission)
        mission.animation = StaticFrame(3)
    } else {
        helpers.addCloseCallbackToMission(mission) {
            if (challengeAnswers.challengeAccomplished(challengeId) &&
                mission.animation.unsafeCast<StaticFrame>().frameIndex != 3
            ) {
                helpers.removeMissionBlocker(mission)
                mission.animation = FramePlayingAnimation(
                    frames = arrayOf(
                        AnimationFrame(0, 1500),
                        AnimationFrame(1, 500),
                        AnimationFrame(2, 500),
                        AnimationFrame(3, 500)
                    ),
                    repetitive = false
                )
                window.setTimeout({
                    mission.animation = StaticFrame(3)
                }, 3000)
            }
        }
    }
}

fun GameScene.configureMissionTowers() {
    val helpers = GameScriptHelpers(this@configureMissionTowers)

    val teslaCoils = mutableListOf<CoordinateAware>()
    objects.getByRole<DynamicSprite>(GameObjectRole.Mission).forEach { mission ->
        if (mission.mapDynamicSprite.id == "MissionTower") {
            helpers.configureAnimation(mission, 2)
            helpers.addMissionRepaintCallback(mission) {
                helpers.configureAnimation(mission, 2)
            }
        }
        if (mission.mapDynamicSprite.id == "TeslaCoil") {
            teslaCoils.add(mission)
            helpers.configureAnimation(mission, 6, 200)
            helpers.addMissionRepaintCallback(mission) {
                helpers.configureAnimation(mission, 6, 200)
            }
        }
    }
}

fun GameScene.configureStarByteLegendBook() {
    objects.getById<DynamicSprite>("star-bytelegend").configureBookSprite()
}

fun GameScene.configureInstallJavaIDEChest() {
    GameScriptHelpers(this).configureChest("install-java-ide")
}

fun GameScene.newbieVillageNoticeboard() = objects {
    val corner = objects.getPointById("NewbieVillageJobNoticeboard")
    val javaCastlePoint = objects.getPointById("SeniorJavaCastleNoticeboard")
    bouncingTitle {
        pixelCoordinate = corner * this@newbieVillageNoticeboard.map.tileSize + PixelCoordinate(16, 0)
        text = gameRuntime.i("SeekForHighPayingJob")
        backgroundColor = "rgba(23,162,184,0.8)"
        onClickFunction = {
            gameRuntime.modalController.showModal(
                gameRuntime.i("NewbieVillageJobNoticeboardContent", javaCastlePoint.toHumanReadableCoordinate().toString()),
                gameRuntime.i("WelcomeToJavaIsland")
            )
        }
        tileCoordinates.add(corner)
    }
}

fun GameScene.castleDoor() = objects {
    objects.getById<GameObject>(mapEntranceId("JavaIsland", "JavaIslandSeniorJavaCastle"))
        .unsafeCast<HasBouncingTitle>().bouncingTitleEnabled = false
    val castleDoorPoint = objects.getPointById("JavaIsland-JavaIslandSeniorJavaCastle-left") - GridCoordinate(0, 1)

    dynamicSprite {
        id = "JavaSeniorCastleDoor-sprite"
        sprite = "CastleDoor"
        gridCoordinate = castleDoorPoint
    }

    bouncingTitle {
        pixelCoordinate = (castleDoorPoint + GridCoordinate(1, 0)) * this@castleDoor.map.tileSize
        text = gameRuntime.i(JAVA_ISLAND_SENIOR_JAVA_CASTLE)
        backgroundColor = "rgba(36,102,233,0.8)"
        onClickFunction = {
            gameRuntime.sceneContainer.loadScene(JAVA_ISLAND_SENIOR_JAVA_CASTLE)
        }
    }
}

fun GameScene.castleNoticeboard() = objects {
    val noticeboard = objects.getPointById("SeniorJavaCastleNoticeboard")
    bouncingTitle {
        pixelCoordinate = noticeboard * this@castleNoticeboard.map.tileSize + PixelCoordinate(16, -16)
        text = gameRuntime.i("ThePathToSeniorJava")
        backgroundColor = "rgba(23,162,184,0.8)"
        onClickFunction = {
            gameRuntime.modalController.showModal(
                gameRuntime.i("ThePathToSeniorJavaContent"),
                text
            )
        }

        tileCoordinates.add(noticeboard)
    }
}

fun GameScene.missionCastle(pointId: String, color: String, backgroundColor: String) = objects {
    val gridCoordinate = objects.getPointById(pointId)
    bouncingTitle {
        pixelCoordinate = (gridCoordinate + GridCoordinate(1, 0)) * this@missionCastle.map.tileSize
        text = gameRuntime.i(pointId)
        this.color = color
        this.backgroundColor = backgroundColor
    }
}

fun GameScene.billboard() = objects {
    val billboard = objects.getPointById("JavaIslandNewbieVillageBillboard")
    bouncingTitle {
        pixelCoordinate = (billboard + GridCoordinate(1, 0)) * this@billboard.map.tileSize
        text = gameRuntime.i("YourAdHere")
        backgroundColor = "rgba(23,162,184,0.8)"
        onClickFunction = {
            gameRuntime.eventBus.emit(SHOW_AD_MODAL, null)
        }
        for (x in 0 until 2) {
            for (y in 0 until 2) {
                tileCoordinates.add(billboard + GridCoordinate(x, y))
            }
        }
    }
}

fun GameScene.thunderCurrentAnimation(startMission: GameMission, endObject: CoordinateAware) {
    scripts("thunder-current-${uuid()}") {
        val sprite = objects.getById<GameMapDynamicSprite>("TeslaCoilAttack")
        startMission.mapDynamicSprite = sprite
        startMission.animation = sprite.animationWithFixedInterval(100, 0, false)
        val frameNumber = sprite.frames[0][0].size

        val attackAnimationDuration = frameNumber * 100L

        compositeAnimation(AnimationBuilder().apply {
            animationId = "ThunderCurrent"
            audioId = "ThunderCurrentAudio"
            frameDurationMs = 100
            initDelayMs = attackAnimationDuration
            onStart = {
                startMission.mapDynamicSprite = objects.getById("TeslaCoil")
                startMission.animation = startMission.mapDynamicSprite.animationWithFixedInterval(200, 0, true)
            }
            onDraw = { canvas, frameIndex ->
                val start = startMission.pixelCoordinate + PixelSize(16, 0)
                val end = endObject.pixelCoordinate + PixelSize(16, 16)

                val startPointOnCanvas = start - canvasState.getCanvasCoordinateInMap()
                val endPointOnCanvas = end - canvasState.getCanvasCoordinateInMap()

                canvas.translate(startPointOnCanvas.x.toDouble(), startPointOnCanvas.y.toDouble())
                canvas.rotate(PI - atan(1.0 * (endPointOnCanvas.x - startPointOnCanvas.x) / (endPointOnCanvas.y - startPointOnCanvas.y)))

                val distance =
                    sqrt(1.0 * (endPointOnCanvas.y - startPointOnCanvas.y) * (endPointOnCanvas.y - startPointOnCanvas.y) + (endPointOnCanvas.x - startPointOnCanvas.x) * (endPointOnCanvas.x - startPointOnCanvas.x))
                val frameSize = gameMapAnimation.frameSize
                canvas.drawImage(
                    image.htmlElement, frameSize.width.toDouble() * frameIndex, 0.0, frameSize.width.toDouble(), frameSize.height.toDouble(),
                    -45.0, 0.0, frameSize.width.toDouble(), distance + 32
                )
            }
        }, AnimationBuilder().apply {
            animationId = "e-shock"
            frameDurationMs = 100
            initDelayMs = attackAnimationDuration + 300
            onDraw = { canvas, frameIndex ->
                val frameSize = gameMapAnimation.frameSize
                val end = endObject.pixelCoordinate - PixelSize(8, 8)
                val endPointOnCanvas = end - canvasState.getCanvasCoordinateInMap()
                canvas.drawImage(
                    image.htmlElement, frameSize.width.toDouble() * frameIndex, 0.0, frameSize.width.toDouble(), frameSize.height.toDouble(),
                    endPointOnCanvas.x.toDouble(), endPointOnCanvas.y.toDouble(), 48.0, 48.0
                )
            }
        })
    }
}

fun GameScene.pubGuard() = objects {
    val helpers = GameScriptHelpers(this@pubGuard)

    npc {
        val guardId = "JavaIslandNewbieVillagePubGuard"
        val guardStartPoint = objects.getPointById("JavaNewbieVillagePubEntranceGuard-point")
        val guardMoveDestPoint = objects.getPointById("JavaNewbieVillagePubEntranceGuard-destination")
        id = guardId
        sprite = "JavaIslandNewbieVillagePubGuard-sprite"
        onInit = {
            when {
                !gameRuntime.heroPlayer.isAnonymous && !gameRuntime.heroPlayer.states.containsKey(BEGINNER_GUIDE_FINISHED_STATE) -> {
                    helpers.getCharacter(guardId).gridCoordinate = guardStartPoint
                    scripts {
                        speech(guardId, "DoYouWantToHaveADrink")
                        characterMove(HERO_ID, guardStartPoint + GridCoordinate(0, 1)) {
                            helpers.getCharacter(HERO_ID).direction = Direction.UP
                        }
                        talkAboutFirstStar(guardId, objects)
                        startBeginnerGuide()
                        putState(BEGINNER_GUIDE_FINISHED_STATE)
                    }
                }
                challengeAnswers.challengeAccomplished(STAR_BYTELEGEND_CHALLENGE_ID) -> {
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
                    !gameRuntime.heroPlayer.states.containsKey(BEGINNER_GUIDE_FINISHED_STATE) && challengeAnswers.challengeAccomplished(STAR_BYTELEGEND_CHALLENGE_ID) -> {
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
                    challengeAnswers.challengeAccomplished(STAR_BYTELEGEND_CHALLENGE_ID) -> {
                        // mission accomplished, let's celebrate!
                        scripts {
                            speech(guardId, "NiceJob", arrayOf("1", "0"))
                            characterMove(guardId, guardMoveDestPoint)
                        }
                    }
                    else -> {
                        scripts {
                            talkAboutFirstStar(guardId, objects)
                            startBeginnerGuide()
                        }
                    }
                }
            } else {
                scripts {
                    speech(guardId, "NiceDayHuh", arrow = false)
                }
            }
        }
    }
}

fun GameScene.newbieVillageOldMan() = objects {
    val helpers = GameScriptHelpers(this@newbieVillageOldMan)

    npc {
        val oldManId = "JavaIslandNewbieVillageOldMan"
        val oldManStartPoint = objects.getPointById("$oldManId-point")
        val oldManDestination = objects.getPointById("$oldManId-destination")
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
                        speech(oldManId, "NiceDayHuh", arrow = false)
                    }
                }
                gameRuntime.heroPlayer.items.contains(COFFEE) -> {
                    scripts {
                        speech(oldManId, "ThankYouForYourCoffee")
                        putState(NEWBIE_VILLAGE_OLD_MAN_GOT_COFFEE)
                        // TODO atomic operation
                        removeItem(COFFEE, oldManStartPoint)
                        characterMove(oldManId, oldManDestination) {
                            helpers.getCharacter(oldManId).direction = Direction.DOWN
                        }
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
        val startPoint = objects.getPointById("$villageHeadId-point")
        val destPoint = objects.getPointById("$villageHeadId-destination")
        id = villageHeadId
        sprite = "$villageHeadId-sprite"

        onInit = {
            if (challengeAnswers.challengeAccomplished(NEWBIE_VILLAGE_NOTICEBOARD_MISSION_ID)) {
                helpers.getCharacter(villageHeadId).gridCoordinate = destPoint
            } else {
                helpers.getCharacter(villageHeadId).gridCoordinate = startPoint
            }
        }

        onClick = helpers.standardNpcSpeech(villageHeadId) {
            if (helpers.getCharacter(villageHeadId).gridCoordinate == startPoint) {
                if (challengeAnswers.challengeAccomplished(NEWBIE_VILLAGE_NOTICEBOARD_MISSION_ID)) {
                    scripts {
                        speech(villageHeadId, "OutsideWorldIsDangerousButIHaveToLetYouGo")
                        speech(villageHeadId, "GoodLuckPursueHolyJavaCoffee", arrow = false)
                        characterMove(villageHeadId, destPoint) {
                            helpers.getCharacter(villageHeadId).direction = Direction.DOWN
                        }
                    }
                } else {
                    val noticeboardPoint = objects.getPointById("remember-brave-people").toHumanReadableCoordinate().toString()
                    val javaCastlePoint = objects.getPointById("JavaIsland-JavaIslandSeniorJavaCastle-left").toHumanReadableCoordinate().toString()

                    scripts {
                        speech(villageHeadId, "OutsideWorldIsDangerous")
                        speech(HERO_ID, "ButIHaveToDoSomething")
                        speech(villageHeadId, "YouCanFindHolyJavaCoffee", args = arrayOf(javaCastlePoint))
                        speech(villageHeadId, "HolyJavaCoffeeIsAntidote")
                        speech(villageHeadId, "ButYouHaveToGoThroughJavaIsland")
                        speech(HERO_ID, "WithItICanDate")
                        speech(villageHeadId, "LeaveYourName", args = arrayOf(noticeboardPoint), arrow = false)
                    }
                }
            } else {
                scripts {
                    speech(villageHeadId, "GoodLuckPursueHolyJavaCoffee", arrow = false)
                }
            }
        }
    }
}

fun GameScene.newbieVillageSailor() = objects {
    val helpers = GameScriptHelpers(this@newbieVillageSailor)
    npc {
        val sailorId = "JavaIslandNewbieVillageSailor"
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
                    speech {
                        speakerId = sailorId
                        contentHtmlId = "WouldYouLikeToGitIsland"
                        arrow = false
                        showYesNo = true
                        onYes = {
                            scripts {
                                enterScene(GIT_ISLAND, {
                                    scripts {
                                        characterEnterVehicleAndMoveToMap(HERO_ID, "Boat", helpers.searchVehiclePath("JavaNewbieVillageToGitIsland"), GIT_ISLAND)
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
        ) {
            scripts {
                speech(sailorId, "ICantHearYou", arrow = false)
            }
        }
    }
}

fun GameScene.invitationCodeBox() {
    objects {
        invitationCodeBox(objects.getPointById("InvitationBox-point"))
    }
    if (gameRuntime.heroPlayer.states.containsKey(INVITER_ID_STATE)) {
        objects.getById<DynamicSprite>("invitation-code-box").animation = StaticFrame(3)
    }
}

fun GameScene.newbieVillageBridgeSoldier() = objects {
    val helpers = GameScriptHelpers(this@newbieVillageBridgeSoldier)
    npc {
        val soldierId = "JavaIslandNewbieVillageBridgeSoldier"
        val startPoint = objects.getPointById("$soldierId-point")
        val destPoint = objects.getPointById("$soldierId-destination")
        id = soldierId
        sprite = "$soldierId-sprite"

        val medalId = "bronze-git-medal"

        onInit = {
            if (gameRuntime.heroPlayer.achievements.contains(medalId)) {
                helpers.getCharacter(soldierId).gridCoordinate = destPoint
            } else {
                helpers.getCharacter(soldierId).gridCoordinate = startPoint
            }
        }

        onClick = helpers.standardNpcSpeech(
            soldierId,
            {
                val hasMedal = gameRuntime.heroPlayer.achievements.contains(medalId)

                if (helpers.getCharacter(soldierId).gridCoordinate == startPoint) {
                    if (hasMedal) {
                        scripts {
                            // move to dest point
                            speech(soldierId, "BravePeopleISeeYourBronzeGitMedal", arrow = false)
                            speech(HERO_ID, "IPromise", arrow = false)
                            characterMove(soldierId, destPoint) {
                                helpers.getCharacter(soldierId).direction = Direction.DOWN
                            }
                        }
                    } else {
                        scripts {
                            speech(soldierId, "YouMustGetBronzeGitMedal", arrow = false)
                        }
                    }
                } else {
                    if (hasMedal) {
                        scripts {
                            speech(soldierId, "DidYouForgetYourPromise", arrow = false)
                        }
                    } else {
                        // this should not happen, do nothing
                    }
                }
            }
        ) {
            scripts {
                speech(soldierId, "ICantHearYou", arrow = false)
            }
        }
    }
}

fun ScriptsBuilder.talkAboutFirstStar(guardId: String, objects: GameObjectContainer) {
    speech(guardId, "FirstStarMedalCondition")
    speech(HERO_ID, "WhereToFindStar")
    speech(
        guardId, "IDontKnowTakeALookAtStarBytelegend",
        arrayOf(
            objects.getPointById(STAR_BYTELEGEND_MISSION_ID).toHumanReadableCoordinate().toString()
        )
    )
}
