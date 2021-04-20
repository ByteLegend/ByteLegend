package com.bytelegend.client.app.ui.mission

import BootstrapNavItem
import BootstrapNavLink
import com.bytelegend.app.client.ui.bootstrap.BootstrapNav
import com.bytelegend.app.client.ui.bootstrap.BootstrapPagination
import com.bytelegend.app.client.ui.bootstrap.BootstrapPaginationEllipsis
import com.bytelegend.app.client.ui.bootstrap.BootstrapPaginationFirst
import com.bytelegend.app.client.ui.bootstrap.BootstrapPaginationItem
import com.bytelegend.app.client.ui.bootstrap.BootstrapPaginationPrev
import com.bytelegend.app.client.ui.bootstrap.BootstrapTabContainer
import com.bytelegend.app.client.ui.bootstrap.BootstrapTabContent
import com.bytelegend.app.client.ui.bootstrap.BootstrapTabPane
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.client.app.page.game
import com.bytelegend.client.app.ui.MultiSelect
import com.bytelegend.client.app.ui.Option
import kotlinext.js.jsObject
import kotlinx.html.classes
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.iframe

interface TutorialTabState : RState {
    var selectedLocales: Array<dynamic>
}

class TutorialTab : RComponent<RProps, TutorialTabState>() {
    override fun TutorialTabState.init() {
        selectedLocales = arrayOf(
            jsObject {
                this.key = game.locale.name
                this.label = game.locale.displayName
            }
        )
    }

    private fun RBuilder.localeFilter() {
        // language multiselect
        // order by: multiselect
        //  update time, upvote
        // type: video/article/youtube
        child(MultiSelect::class) {
            attrs.allOptions = Locale.values().map { Option(it.name, it.displayName) }
            attrs.initOptions = listOf(Option(game.locale.name, game.locale.displayName))
            attrs.onSelectComplete = {
                console.log(it)
            }
        }
    }

    private fun RBuilder.orderBy() {
        child(MultiSelect::class) {
            attrs.allOptions = listOf(Option("a", "b"), Option("c", "d"), Option("e", "f"))
            attrs.initOptions = listOf(Option("a", "b"))
            attrs.onSelectComplete = {
                console.log(it)
            }
        }
    }

    private fun RBuilder.typeFilter() {}

    override fun RBuilder.render() {
        div {
            attrs.classes = setOf("mission-modal-tutorial-tab")
            div {
                attrs.classes = setOf("mission-modal-tutorial-filters")
                localeFilter()
                orderBy()
                typeFilter()
            }
            div {
                attrs.classes = setOf("mission-modal-tutorial-content")
                BootstrapTabContainer {
                    attrs.id = "left-tabs-example"
                    attrs.defaultActiveKey = "tab-1"
                    div {
                        attrs.classes = setOf("mission-modal-tutorial-content-left")
                        div {
                            attrs.classes = setOf("mission-modal-tutorial-content-left-tutorials")
                            BootstrapNav {
                                attrs.variant = "pills"
                                attrs.className = "flex-column"

                                repeat(20) {
                                    BootstrapNavItem {
                                        BootstrapNavLink {
                                            attrs.eventKey = "tab-$it"
                                            +"Tab-$it"
                                        }
                                    }
                                }
                            }
                        }

                        div {
                            attrs.classes = setOf("mission-modal-tutorial-content-left-pagination")
                            BootstrapPagination {
                                attrs.size = "sm"
                                BootstrapPaginationFirst {}
                                BootstrapPaginationPrev {}
                                BootstrapPaginationItem { +"1" }
                                BootstrapPaginationEllipsis {}
                            }
                        }
                    }
                    div {
                        attrs.classes = setOf("mission-modal-tutorial-content-right")
                        BootstrapTabContent {
                            repeat(20) {
                                BootstrapTabPane {
                                    attrs.eventKey = "tab-$it"
                                    if (it % 2 == 0) {
                                        iframe {
//                                            ReactPlayer {
                                            attrs.src = "https://www.youtube.com/embed/wbyHZ-EWuwU"
                                            attrs.width = "600"
                                            attrs.height = "400"
                                            attrs.attributes["frameborder"] = "0"
                                            attrs.attributes["allow"] = "accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                                            attrs.attributes["allowfullscreen"] = ""
                                        }
                                    } else {
                                        iframe {
                                            attrs.src = "https://player.bilibili.com/player.html?aid=68067005&bvid=BV1EJ411w7cq&cid=121914021&page=1"
                                            attrs.attributes["scrolling"] = "no"
                                            attrs.attributes["border"] = "0"
                                            attrs.attributes["frameborder"] = "no"
                                            attrs.attributes["framespacing"] = "0"
                                        }
                                    }
                                }
                            }
                        }
                    }
//                    }
                }
            }
        }

//        BootstrapContainer {
//
//            BootstrapRow {
//                BootstrapCol {
//                    attrs.sm = 12
//                    +"pages"
//                }
//            }
//        }
    }
    // <iframe src="//player.bilibili.com/player.html?aid=68067005&bvid=BV1EJ411w7cq&cid=121914021&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
    // <iframe src="//player.bilibili.com/player.html?aid=68067005&bvid=BV1EJ411w7cq&cid=117975491&page=2" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
}
