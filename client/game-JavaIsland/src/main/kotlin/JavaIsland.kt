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
import com.bytelegend.app.client.api.FlickeringSingleFrameAnimation
import com.bytelegend.app.client.api.GameMission
import com.bytelegend.app.client.api.GameObjectContainer
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.HERO_ID
import com.bytelegend.app.client.api.HasBouncingTitle
import com.bytelegend.app.client.api.ScriptsBuilder
import com.bytelegend.app.client.api.StaticFrame
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.client.api.openMissionModalEvent
import com.bytelegend.app.client.utils.AnimationFrameRange
import com.bytelegend.app.client.utils.GameScriptHelpers
import com.bytelegend.app.client.utils.configureBookSprite
import com.bytelegend.app.client.utils.configureChestOpenByKey
import com.bytelegend.app.client.utils.onMissionItemUsed
import com.bytelegend.app.client.utils.refreshAnimationForItem
import com.bytelegend.app.client.utils.refreshTeslaCoilAnimation
import com.bytelegend.app.client.utils.removeMissionBlocker
import com.bytelegend.app.shared.COFFEE
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.GIT_ISLAND
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.INVITER_ID_STATE
import com.bytelegend.app.shared.JAVA_ISLAND
import com.bytelegend.app.shared.JAVA_ISLAND_SENIOR_JAVA_CASTLE
import com.bytelegend.app.shared.NON_BLOCKER
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.app.shared.objects.mapEntranceId
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.Element
import org.w3c.dom.get

const val BEGINNER_GUIDE_FINISHED_STATE = "BeginnerGuideFinished"
const val NEWBIE_VILLAGE_NOTICEBOARD_MISSION_ID = "remember-brave-people-challenge"
const val STAR_BYTELEGEND_MISSION_ID = "star-bytelegend"
const val FIRST_STAR_MEDAL_ID = "first-star-medal"

private const val SHOW_AD_MODAL = "show.ad.modal"

fun main() {
    val gameRuntime = window.asDynamic().gameRuntime.unsafeCast<GameRuntime>()

    gameRuntime.sceneContainer.getSceneById(JAVA_ISLAND).apply {
        objects {
            configureMissionTowers()
            configureStarByteLegendBook()
            configureInstallJavaIDEChest()

            pubGuard()
            newbieVillageOracle()
            newbieVillageNoticeboard()
            newbieVillageHead()
            newbieVillageSailor()
            newbieVillageBridgeSoldier()
            invitationCodeBox()

            javaCloneRunDoor("clone-and-run-java-project")
            javaCloneRunDoor("clone-and-switch-branch")
            firstBugEvil()
            basicStructureStone(
                "JavaBasicStructure",
                "JavaBasicStructure-challenge-text",
                GridCoordinate(0, 1),
                listOf("import-third-party-package", "import-class", "create-a-new-class")
            )
            commentDungeonNoticeboard()
            basicStructureStone(
                "JavaMethodField",
                "JavaMethodField-challenge-text",
                GridCoordinate(-1, 0),
                listOf(
                    "java-method-recursion",
                    "java-local-variable-scope",
                    "java-method-invocation",
                    "java-field",
                    "java-method-overloading",
                    "java-method"
                )
            )
            basicStructureStone(
                "JavaClassObjectBlocker",
                "JavaClassObjectBlocker-challenge-text",
                GridCoordinate(-1, 0),
                listOf(
                    "java-reference-and-value",
                    "java-npe",
                    "java-constructor",
                    "java-instance-method",
                    "java-instance-field",
                    "java-class-instantiation",
                )
            )

            basicStructureStone(
                "JavaDataStructureBlocker",
                "JavaDataStructureBlocker-challenge-text",
                GridCoordinate(-1, 0),
                listOf(
                    "java-data-representation-in-computer",
                    "java-type-conversion",
                    "java-box-unbox",
                    "java-array-basics"
                )
            )

            basicStructureStone(
                "JavaOperatorsBlocker",
                "JavaOperatorsBlocker-challenge-text",
                GridCoordinate(0, 1),
                listOf(
                    "java-operator-precedence",
                    "java-basic-operators",
                    "java-ternary-operator",
                    "java-logical-operators",
                    "java-increment-operator",
                    "java-bitwise-operators"
                )
            )

            basicStructureStone(
                "JavaControlFlowBlocker",
                "JavaControlFlow-challenge-text",
                GridCoordinate(1, 0),
                listOf(
                    "java-if-else",
                    "java-loop",
                    "java-loop-break-continue",
                    "java-switch"
                )
            )

            configureChestOpenByKey("java-loop-senior")
            configureChestOpenByKey("java-pattern-matching-switch")

            unfinishedPartBlocker()

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

fun GameScene.unfinishedPartBlocker() = objects {
    dynamicSprite {
        id = "UnfinishedPartBlocker"
        gridCoordinate = objects.getPointById("UnfinishedPartBlocker-point")
        onClick = {
            scripts {
                speech("UnfinishedPartBlocker", "UnfinishedTitle", arrow = false)
            }
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

fun htmlToText(html: String): String {
    return document.createElement("div").let {
        it.innerHTML = html
        it.textContent?.trim() ?: ""
    }
}

/**
 * Upon initialization and mission repaint events,
 * update the checkboxes and mission animation/blockers.
 */
fun GameScene.updateCheckboxes(blockMissionId: String, blockTextId: String, missionIds: List<String>) {
    val html = gameRuntime.i(blockTextId)
    val tmp = document.createElement("div")
    tmp.innerHTML = html

    val accomplishedMissionBefore = tmp.querySelectorAll("li>input[checked]").length
    missionIds.forEach {
        if (missionItemUsed(it)) {
            val mission = objects.getById<GameMission>(it)
            val title = htmlToText(gameRuntime.i(mission.gameMapMission.title))
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

    gameRuntime.putText(blockTextId, tmp.innerHTML)

    val accomplishedMissionAfter = tmp.querySelectorAll("li>input[checked]").length
    val missionStone = objects.getById<GameMission>(blockMissionId)
    if (accomplishedMissionAfter == missionIds.size) {
        missionStone.animation = StaticFrame(1)
    } else {
        missionStone.animation = StaticFrame(0)
    }
    if (accomplishedMissionBefore < missionIds.size && accomplishedMissionAfter == missionIds.size) {
        removeMissionBlocker(missionStone)
    }
}

fun GameScene.hasItem(itemId: String) = gameRuntime.heroPlayer.items.contains(itemId)
fun GameScene.itemUsed(itemId: String) = gameRuntime.heroPlayer.usedItems.contains(itemId)
fun GameScene.missionItemUsed(missionId: String) = gameRuntime.heroPlayer.usedItems.any {
    it.endsWith(":${map.id}:$missionId")
}

fun GameScene.findUndestroyedTower(missionIds: List<String>): GameMission? {
    return missionIds.firstOrNull { !missionItemUsed(it) }?.let { objects.getById(it) }
}

/**
 * if the stone is already destroyed, show the mission modal.
 * if the stone is not destroyed and we have finished all missions,
 *    Move to the point below stone, show the animation
 *    (if the point is not reachable, show the mission modal)
 * else if hero is at the point below the mission, tesla coil attack, show the mission modal
 * else show the mission modal.
 *
 */
fun GameScene.basicStructureStone(stoneMissionId: String, stoneTextId: String, attackPointOffset: GridCoordinate, missionIds: List<String>) {
    updateCheckboxes(stoneMissionId, stoneTextId, missionIds)

    val stoneMission = objects.getById<GameMission>(stoneMissionId)
    val attackPoint = stoneMission.gridCoordinate + attackPointOffset

    objects {
        dynamicSprite {
            id = "attack-point-of-$stoneMissionId"
            gridCoordinate = attackPoint
            onTouch = {
                findUndestroyedTower(missionIds)?.let {
                    teslaCoilAttackAnimation(it, gameRuntime.hero!!)
                }
            }
        }
    }

    val openModal: UnitFunction = {
        updateCheckboxes(stoneMissionId, stoneTextId, missionIds)
        gameRuntime.eventBus.emit(openMissionModalEvent(stoneMissionId), null)
    }
    stoneMission.onClickFunction = {
        val undestroyedTower = findUndestroyedTower(missionIds)
        if (blockers[stoneMission.gridCoordinate.y][stoneMission.gridCoordinate.x] >= NON_BLOCKER) {
            // clicked via bouncing title
            openModal()
        } else if (undestroyedTower == null) {
            // all towers are destroyed
            // move to the point, show destroyed animation
            val movePath = gameRuntime.hero!!.searchPath(attackPoint)
            if (movePath.isEmpty()) {
                openModal()
            } else {
                gameRuntime.hero!!.moveAlong(movePath) {
                    stoneMission.animation = FlickeringSingleFrameAnimation(0, 200)

                    window.setTimeout({
                        stoneMission.animation = StaticFrame(1)
                        openModal()
                    }, 2000)
                }
            }
        } else if (gameRuntime.heroPlayer.gridCoordinate.manhattanDistanceTo(stoneMission.gridCoordinate) < 2) {
            // attack
            teslaCoilAttackAnimation(undestroyedTower, gameRuntime.hero!!)
            window.setTimeout({ openModal() }, 2000)
        } else {
            openModal()
        }
    }
}

fun GameScene.firstBugEvil() = objects {
    val mission = objects.getById<GameMission>("fix-simple-add")

    refreshAnimationForItem(mission, "dagger:JavaIsland:fix-simple-add", 3, AnimationFrameRange(0, 3), null, 500)
    onMissionItemUsed(mission.id) {
        killEvil(mission)
        mission.animation = FlickeringSingleFrameAnimation(0, 200)

        window.setTimeout({
            refreshAnimationForItem(mission, "dagger:JavaIsland:fix-simple-add", 3, AnimationFrameRange(0, 3), null, 500)
        }, 3000)
    }
}

fun GameScene.javaCloneRunDoor(missionId: String) {
    val mission = objects.getById<DynamicSprite>(missionId)
    val itemId = "door-key:${map.id}:$missionId"
    refreshAnimationForItem(mission, itemId, 3, AnimationFrameRange(0, 0), AnimationFrameRange(0, 3))
    onMissionItemUsed(mission.id) {
        refreshAnimationForItem(mission, itemId, 3, AnimationFrameRange(0, 0), AnimationFrameRange(0, 3))
    }
}

fun GameScene.configureMissionTowers() {
    val helpers = GameScriptHelpers(this@configureMissionTowers)

    objects.getByRole<GameMission>(GameObjectRole.Mission).forEach { mission ->
        if (mission.mapDynamicSprite.id == "MissionTower") {
            helpers.configureAnimation(mission, 2)
            helpers.addMissionRepaintCallback(mission) {
                helpers.configureAnimation(mission, 2)
            }
        }
        if (mission.mapDynamicSprite.id == "TeslaCoil") {
            refreshTeslaCoilAnimation(mission)
            onMissionItemUsed(mission.id) { item: String ->
                when {
                    item.startsWith("iron-sword") -> ironSwordAttack(mission)
                    item.startsWith("silver-sword") -> silverSwordAttack(mission)
                    item.startsWith("gold-sword") -> goldSwordAttack(mission)
                }
            }
        }
    }
}

fun GameScene.configureStarByteLegendBook() {
    objects.getById<DynamicSprite>("star-bytelegend").configureBookSprite()
}

fun GameScene.configureInstallJavaIDEChest() {
    configureChestOpenByKey("install-java-ide")
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
                        talkAboutFirstStar(guardId, gameRuntime.i("first-star-medal-name"), objects)
                        startBeginnerGuide()
                        putState(BEGINNER_GUIDE_FINISHED_STATE)
                    }
                }
                gameRuntime.heroPlayer.achievements.contains(FIRST_STAR_MEDAL_ID) -> {
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
                    !gameRuntime.heroPlayer.states.containsKey(BEGINNER_GUIDE_FINISHED_STATE) && gameRuntime.heroPlayer.achievements.contains(FIRST_STAR_MEDAL_ID) -> {
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
                            talkAboutFirstStar(guardId, gameRuntime.i("first-star-medal-name"), objects)
                            startBeginnerGuide()
                            putState(BEGINNER_GUIDE_FINISHED_STATE)
                        }
                    }
                    gameRuntime.heroPlayer.achievements.contains(FIRST_STAR_MEDAL_ID) -> {
                        // mission accomplished, let's celebrate!
                        scripts {
                            speech(guardId, "NiceJob", arrayOf("1", "0"))
                            characterMove(guardId, guardMoveDestPoint)
                        }
                    }
                    else -> {
                        scripts {
                            talkAboutFirstStar(guardId, gameRuntime.i("first-star-medal-name"), objects)
                            startBeginnerGuide()
                        }
                    }
                }
            } else {
                scripts {
                    speech(guardId, "NiceDayButEndOfTheWorld", arrow = false)
                }
            }
        }
    }
}

fun GameScene.newbieVillageOracle() = objects {
    val helpers = GameScriptHelpers(this@newbieVillageOracle)
    val heroName = gameRuntime.heroPlayer.nickname
    val javaCastlePoint = objects.getPointById("JavaIsland-JavaIslandSeniorJavaCastle-left").toHumanReadableCoordinate().toString()

    npc {
        val oldManId = "JavaIslandNewbieVillageOldMan"
        val oldManStartPoint = objects.getPointById("$oldManId-point")
        val oldManDestination = objects.getPointById("$oldManId-destination")
        id = oldManId
        sprite = "$oldManId-sprite"
        onInit = {
            if (itemUsed(COFFEE)) {
                helpers.getCharacter(oldManId).gridCoordinate = oldManDestination
            } else {
                helpers.getCharacter(oldManId).gridCoordinate = oldManStartPoint
            }
        }
        onClick = helpers.standardNpcSpeech(oldManId) {
            when {
                itemUsed(COFFEE) -> {
                    scripts {
                        speech(oldManId, "RememberDestroyTowers", arrow = false, args = arrayOf(javaCastlePoint))
                    }
                }
                hasItem(COFFEE) -> {
                    scripts {
                        useItem(COFFEE, oldManStartPoint)
                        speech(oldManId, "ThankYouForYourCoffee")
                        speech(HERO_ID, "MyNameIs", args = arrayOf(heroName))
                        speech(oldManId, "YouAreFinallyHere", args = arrayOf(heroName))
                        speech(HERO_ID, "HowToEliminateBugs")
                        speech(oldManId, "BugsAreScoutOfFleet")
                        speech(oldManId, "DestroyBugTowers", args = arrayOf(heroName))
                        speech(HERO_ID, "SoIOnlyNeedToDestroyTowers")
                        speech(oldManId, "BugsInCastle", args = arrayOf(javaCastlePoint))
                        speech(HERO_ID, "IWillGoNow")
                        speech(oldManId, "GoodLuckFateOfWorldInYourHands", arrow = false)
                        characterMove(oldManId, oldManDestination) {
                            helpers.getCharacter(oldManId).direction = Direction.DOWN
                        }
                    }
                }
                else -> {
                    scripts {
                        speech(oldManId, "CanYouGetMeACoffee", arrow = false)
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
                        speech(villageHeadId, "GoodLuckFateOfWorldInYourHands", arrow = false)
                        characterMove(villageHeadId, destPoint) {
                            helpers.getCharacter(villageHeadId).direction = Direction.DOWN
                        }
                    }
                } else {
                    val noticeboardPoint = objects.getPointById("remember-brave-people").toHumanReadableCoordinate().toString()

                    scripts {
                        speech(villageHeadId, "OutsideWorldIsDangerous")
                        speech(HERO_ID, "WouldTheBugsDisappear")
                        speech(villageHeadId, "LeaveYourName", args = arrayOf(noticeboardPoint), arrow = false)
                    }
                }
            } else {
                scripts {
                    speech(villageHeadId, "GoodLuckFateOfWorldInYourHands", arrow = false)
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
                if (!gameRuntime.heroPlayer.states.containsKey(INVITER_ID_STATE)) {
                    gameRuntime.toastController.addToast(
                        gameRuntime.i("MoreGold"),
                        gameRuntime.i("ThereIsMoreGold", objects.getPointById("InvitationBox-point").toHumanReadableCoordinate().toString()),
                        5000
                    )
                }
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

        val amuletId = "bronze-git-medal"

        onInit = {
            if (gameRuntime.heroPlayer.achievements.contains(amuletId)) {
                helpers.getCharacter(soldierId).gridCoordinate = destPoint
            } else {
                helpers.getCharacter(soldierId).gridCoordinate = startPoint
            }
        }

        onClick = helpers.standardNpcSpeech(
            soldierId,
            {
                val hasAmulet = gameRuntime.heroPlayer.achievements.contains(amuletId)

                if (helpers.getCharacter(soldierId).gridCoordinate == startPoint) {
                    if (hasAmulet) {
                        scripts {
                            // move to dest point
                            speech(soldierId, "BravePeopleISeeYourGitAmulet", arrow = false)
                            speech(HERO_ID, "IPromise", arrow = false)
                            characterMove(soldierId, destPoint) {
                                helpers.getCharacter(soldierId).direction = Direction.DOWN
                            }
                        }
                    } else {
                        scripts {
                            speech(soldierId, "YouMustGetGitAmulet")
                            speech(soldierId, "WhereToGetGitAmulet")
                            speech(soldierId, "GitAmuletOnGitIsland", arrow = false)
                        }
                    }
                } else {
                    if (hasAmulet) {
                        scripts {
                            speech(soldierId, "BravePeopleISeeYourGitAmulet", arrow = false)
                            speech(HERO_ID, "IPromise", arrow = false)
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

fun ScriptsBuilder.talkAboutFirstStar(guardId: String, medalName: String, objects: GameObjectContainer) {
    speech(guardId, "FirstStarMedalCondition", arrayOf(medalName))
    speech(HERO_ID, "WhereToFindStar")
    speech(
        guardId, "TakeALookAtStarBytelegend",
        arrayOf(
            objects.getPointById(STAR_BYTELEGEND_MISSION_ID).toHumanReadableCoordinate().toString()
        )
    )
}
