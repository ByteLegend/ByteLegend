// package com.bytelegend.client.app.ui
//
// import com.bytelegend.app.shared.Direction
// import com.bytelegend.app.shared.GridCoordinate
// import com.bytelegend.app.shared.Item
// import com.bytelegend.app.shared.Npc
// import com.bytelegend.app.shared.PixelCoordinate
// import com.bytelegend.app.shared.emeraldBoxId
// import com.bytelegend.app.shared.enemyCoordinate
// import com.bytelegend.app.shared.enemyId
// import com.bytelegend.app.shared.gradleInitMissionId
// import com.bytelegend.app.shared.gradleIslandGuardId
// import com.bytelegend.app.shared.newbieIslandGuardCharacterId
// import com.bytelegend.app.shared.newbieIslandGuardId
// import com.bytelegend.app.shared.newbieVillageOldManId
// import com.bytelegend.app.shared.playerAnswerQuestionMessage
// import com.bytelegend.app.shared.prGradleLegend
// import com.bytelegend.app.shared.solveEmeraldQuestionMissionId
// import com.bytelegend.app.shared.starGradleLegendMissionId
// import com.bytelegend.client.app.engine.MOUSE_CLICK_EVENT
// import com.bytelegend.client.app.event.MouseEventListener
// import common.ui.bootstrap.BootstrapButton
// import common.ui.bootstrap.BootstrapFormControl
// import common.ui.bootstrap.BootstrapPopover
// import common.ui.icons.biGitPullRequest
// import common.ui.icons.giEmerald
// import common.ui.icons.giRoundStar
// import kotlinext.js.js
// import kotlinx.browser.window
// import kotlinx.css.br
// import kotlinx.css.padding
// import kotlinx.css.px
// import kotlinx.html.style
// import react.RBuilder
// import react.RState
// import react.createRef
// import react.dom.a
// import react.dom.br
// import react.dom.div
// import react.dom.li
// import react.dom.span
// import react.dom.ul
// import react.setState
// import styled.css
// import styled.styledDiv
// import kotlin.math.abs
//
// // TODO NpcInteactionController
// interface DialogWidgetsProps : GameProps {
// }
//
// interface DialogWidgetsState : RState {
//    var selectedNpc: Npc?
//    var selectedItem: Item?
//    var submitButtonEnabled: Boolean
// }
//
// fun Npc.getCoordinate() = GridCoordinate(x, y)
// fun Item.getCoordinate() = GridCoordinate(x, y)
//
// class DialogWidgets : GameUIComponent<DialogWidgetsProps, DialogWidgetsState>() {
//    private val answerInput = createRef<dynamic>()
//    override fun DialogWidgetsState.init() {
//        submitButtonEnabled = true
//    }
//
//    private fun nextTo(coordinate: GridCoordinate): Boolean {
//        return false
// //        if (game.hero.anonymous) {
// //            return false
// //        }
// //        return (game.heroSprite!!.coordinateInMap / game.tileSize).let {
// //            (it.x == coordinate.x && abs(it.y - coordinate.y) == 1) ||
// //                (it.y == coordinate.y && abs(it.x - coordinate.x) == 1)
// //        }
//    }
//
//    private val mouseClickOnCanvasEventListener: MouseEventListener = { event ->
//        if (state.selectedNpc?.getCoordinate() == event.mapCoordinate ||
//            !tileIsNpc(event.mapCoordinate) ||
//            !nextTo(event.mapCoordinate)
//        ) {
//            setState {
//                selectedNpc = undefined
//            }
//        } else {
//            setState {
// //                selectedNpc = game.npcs.values.first { it.getCoordinate() == event.mapCoordinate }
// //                val direction = determineDirection(event.mapCoordinate)
// //                if (selectedNpc!!.id == newbieVillageOldManId) {
// //                    if (direction != Direction.NONE) {
// //                        game.npcSprites.getValue(newbieVillageOldManId).direction = direction
// //                    }
// //                } else if (selectedNpc!!.id == newbieIslandGuardId) {
// //                    if (direction != Direction.NONE) {
// //                        game.npcSprites.getValue(newbieIslandGuardId).direction = direction
// //                    }
// //                } else if (selectedNpc!!.id == gradleIslandGuardId) {
// //                    if (direction != Direction.NONE) {
// //                        game.npcSprites.getValue(gradleIslandGuardId).direction = direction
// //                    }
// //                } else if (selectedNpc!!.id == enemyId) {
// //                    if (direction != Direction.NONE) {
// //                        game.npcSprites.getValue(enemyId).direction = direction
// //                    }
// //                }
//            }
//        }
//
//        if (state.selectedItem?.getCoordinate() == event.mapCoordinate ||
//            !tileIsItem(event.mapCoordinate) ||
//            !nextTo(event.mapCoordinate)) {
//            setState {
//                selectedItem = undefined
//            }
//        } else {
//            setState {
// //                selectedItem = game.items.values.first { it.getCoordinate() == event.mapCoordinate }
//            }
//        }
//    }
//
//    private fun determineDirection(npcCoordinate: GridCoordinate): Direction {
//        return Direction.DOWN
// //        return when {
// //            game.hero.anonymous -> Direction.NONE
// //            game.hero.x == npcCoordinate.x && game.hero.y > npcCoordinate.y -> Direction.DOWN
// //            game.hero.x == npcCoordinate.x && game.hero.y < npcCoordinate.y -> Direction.UP
// //            game.hero.y == npcCoordinate.y && game.hero.x < npcCoordinate.x -> Direction.LEFT
// //            game.hero.y == npcCoordinate.y && game.hero.x > npcCoordinate.x -> Direction.RIGHT
// //            else -> Direction.NONE
// //        }
//    }
//
//    override fun componentDidMount() {
//        super.componentDidMount()
//        props.game.eventBus.on(MOUSE_CLICK_EVENT, mouseClickOnCanvasEventListener)
//    }
//
//    override fun componentWillUnmount() {
//        super.componentWillUnmount()
//        props.game.eventBus.remove(MOUSE_CLICK_EVENT, mouseClickOnCanvasEventListener)
//    }
//
//    private fun tileIsNpc(coordinate: GridCoordinate) = false
// //        game.npcs.values.any { it.x == coordinate.x && it.y == coordinate.y }
//
//    private fun tileIsItem(coordinate: GridCoordinate) = false
// //        game.items.values.any { it.x == coordinate.x && it.y == coordinate.y }
//
//    @Suppress("UnsafeCastFromDynamic")
//    override fun RBuilder.render() {
//        if (state.selectedNpc?.id == newbieVillageOldManId) {
//            BootstrapPopover {
//                attrs.id = "tooltip-${state.selectedNpc!!.characterId}"
//                attrs.placement = "top"
//
//                val coordinate = calculateTooltipCoordinate(state.selectedNpc!!.x, state.selectedNpc!!.y)
//
//                attrs.style = js {
//                    position = "absolute"
//                    left = "${coordinate.x - 10}px"
//                    width = "200px"
//                    bottom = "${props.game.containerSize.height - coordinate.y}px"
//                    top = "auto"
//                    zIndex = "${props.layer.zIndex}"
//                    maxWidth = "max-content"
//                }
//
//                div {
// //                    if (game.missionController.missionAccomplished(prGradleLegend) &&
// //                        game.missionController.missionAccomplished(starGradleLegendMissionId)) {
// //                        +"Good luck son! Go chasing the legend of Gradle!"
// //                    } else {
// //                        +"You must accomplish the following missions to proceed."
// //                    }
//                }
//
//                span {
//                    attrs {
//                        style = js {
//                            textAlign = "center"
//                            display = "block"
//                        }
//                    }
//                    giRoundStar {
// //                        attrs.size = 32
// //                        attrs.style =
// //                            if (game.starNumber > 0) js {
// //                                cursor = "pointer"
// //                                color = "yellow"
// //                            } else js {
// //                                cursor = "pointer"
// //                            }
// //                        attrs.onClick = {
// //                            window.open("https://github.com/GradleLegend/GradleLegend", "_blank")
// //                        }
//                    }
//                    biGitPullRequest {
// //                        attrs.size = 32
// //                        attrs.style = if (game.missionController.missionAccomplished("PR_GradleLegend/GradleLegend"))
// //                            js {
// //                                cursor = "pointer"
// //                                color = "red"
// //                            } else
// //                            js {
// //                                cursor = "pointer"
// //                            }
// //                        attrs.onClick = {
// //                            window.open("https://github.com/GradleLegend/GradleLegend", "_blank")
// //                        }
//                    }
//                }
//            }
//        } else if (state.selectedNpc?.id == newbieIslandGuardId) {
// //            BootstrapPopover {
// //                attrs.id = "tooltip-${state.selectedNpc!!.characterId}"
// //                attrs.placement = "top"
// //
// //                val coordinate = calculateTooltipCoordinate(state.selectedNpc!!.x, state.selectedNpc!!.y)
// //
// //                attrs.style = js {
// //                    position = "absolute"
// //                    left = "${coordinate.x - 10}px"
// //                    width = "200px"
// //                    bottom = "${props.game.containerSize.height - coordinate.y}px"
// //                    top = "auto"
// //                    zIndex = "${props.layer.zIndex}"
// //                    maxWidth = "max-content"
// //                }
// //
// //                div {
// //                    if (game.missionController.missionAccomplished(solveEmeraldQuestionMissionId)) {
// //                        +"Good luck son! Go chasing the legend of Gradle!"
// //                    } else {
// //                        +"I want an emerald to yield way to you."
// //                    }
// //                }
// //
// //                span {
// //                    attrs {
// //                        style = js {
// //                            textAlign = "center"
// //                            display = "block"
// //                        }
// //                    }
// //                    giEmerald {
// //                        attrs.size = 32
// //                        if (game.missionController.missionAccomplished(solveEmeraldQuestionMissionId)) {
// //                            attrs.style = js {
// //                                color = "green"
// //                            }
// //                        }
// //                    }
// //                }
// //            }
//        } else if (state.selectedNpc?.id == gradleIslandGuardId) {
// //            BootstrapPopover {
// //                attrs.id = "tooltip-${state.selectedNpc!!.characterId}"
// //                attrs.placement = "top"
// //
// //                val coordinate = calculateTooltipCoordinate(state.selectedNpc!!.x, state.selectedNpc!!.y)
// //
// //                attrs.style = js {
// //                    position = "absolute"
// //                    left = "${coordinate.x - 10}px"
// //                    width = "200px"
// //                    bottom = "${props.game.containerSize.height - coordinate.y}px"
// //                    top = "auto"
// //                    zIndex = "${props.layer.zIndex}"
// //                    maxWidth = "max-content"
// //                }
// //
// //                div {
// //                    if (game.starNumber >= 4) {
// //                        +"Good luck son! Go chasing the legend of Gradle!"
// //                    } else if (game.hero.anonymous) {
// //                        +"I only allow people with at least 4 stars to pass through."
// //                    } else {
// //                        +"I only allow people with at least 4 stars to pass through, you have ${game.starNumber} stars."
// //                    }
// //                }
// //            }
//        } else if (state.selectedNpc?.id == enemyId) {
// //            BootstrapPopover {
// //                attrs.id = "tooltip-${state.selectedNpc!!.characterId}"
// //                attrs.placement = "top"
// //
// //                val coordinate = calculateTooltipCoordinate(state.selectedNpc!!.x, state.selectedNpc!!.y)
// //
// //                attrs.style = js {
// //                    position = "absolute"
// //                    left = "${coordinate.x - 10}px"
// //                    width = "200px"
// //                    bottom = "${props.game.containerSize.height - coordinate.y}px"
// //                    top = "auto"
// //                    zIndex = "${props.layer.zIndex}"
// //                    maxWidth = "max-content"
// //                }
// //
// //                styledDiv {
// //                    css {
// //                        padding = "10px 10px 10px 10px"
// //                    }
// //                    if (game.missionController.missionAccomplished(gradleInitMissionId)) {
// //                        +"Ahhhhhhhhhhhhhhh--------------"
// //                    } else {
// //                        +"Kill me, you will get ⭐⭐⭐"
// //                        br {}
// //                        +"Fight me at "
// //                        a {
// //                            attrs.target = "_blank"
// //                            attrs.href = "https://github.com/GradleLegend/init-gradle-project"
// //                            +"here"
// //                        }
// //                    }
// //                }
// //            }
//        }
//
// //        if (state.selectedItem?.id == emeraldBoxId && !game.missionController.missionAccomplished(solveEmeraldQuestionMissionId)) {
// //            BootstrapPopover {
// //                attrs.id = "tooltip-${state.selectedItem!!.id}"
// //                attrs.placement = "top"
// //
// //                val coordinate = calculateTooltipCoordinate(state.selectedItem!!.x, state.selectedItem!!.y)
// //
// //                attrs.style = js {
// //                    position = "absolute"
// //                    left = "${coordinate.x - 10}px"
// //                    width = "400px"
// //                    bottom = "${props.game.containerSize.height - coordinate.y}px"
// //                    top = "auto"
// //                    zIndex = "${props.layer.zIndex}"
// //                    maxWidth = "max-content"
// //                }
// //
// //                styledDiv {
// //                    +"The key to open this box is the output of `./gradlew -v`"
// //                    css {
// //                        padding = "10px 10px 10px 10px"
// //                    }
// //                    BootstrapFormControl {
// //                        attrs.`as` = "textarea"
// //                        attrs.type = "number"
// //                        attrs.placeholder = "the output of ./gradlew -v"
// //                        attrs.ref = answerInput
// //                    }
// //
// //                    BootstrapButton {
// //                        +"Submit"
// //                        attrs.disabled = !state.submitButtonEnabled || game.hero.anonymous
// //                        attrs.onClick = stateUpdatingEventHandler {
// //                            game.eventBus.emit(playerAnswerQuestionMessage, answerInput.current.value)
// //                            setState {
// //                                submitButtonEnabled = false
// //                            }
// //
// //                            window.setTimeout({
// //                                setState {
// //                                    submitButtonEnabled = true
// //                                }
// //                            }, 500)
// //                        }
// //                    }
// //                    ul {
// //                        game.missionController.missions.filter { it.missionId == solveEmeraldQuestionMissionId }
// //                            .forEach { mission ->
// //                                li {
// //                                    +"${mission.time} ${formatAnswer(mission.playerAnswer)} ${formatStatus(mission.solved)}"
// //                                }
// //                            }
// //                    }
// //                }
// //            }
// //        }
//    }
//
//    fun formatStatus(solved: Boolean) = if (solved) "✅" else "❌"
//
//    fun formatAnswer(str: String) =
//        if (str.length > 10) "${str.substring(0, 5)}...${str.substring(str.length - 5)}"
//        else str
//
//    private fun calculateTooltipCoordinate(mapX: Int, mapY: Int): PixelCoordinate {
//        return PixelCoordinate(0, 0)
// //        val leftTopCornerPixelCoordinateInMap = props.game.canvasCoordinateInMap
// //        val leftTopCornerPixelCoordinateInContainer = props.game.canvasCoordinateInGameContainer
// //        val tileSize = props.game.tileSize
// //        return PixelCoordinate(
// //            mapX * tileSize.width - leftTopCornerPixelCoordinateInMap.x + leftTopCornerPixelCoordinateInContainer.x + game.tileSize.width / 2,
// //            mapY * tileSize.height - leftTopCornerPixelCoordinateInMap.y + leftTopCornerPixelCoordinateInContainer.y
// //        )
//    }
// }
//
