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
@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.bytelegend.client.app.ui.mission

import com.bytelegend.app.client.ui.bootstrap.BootstrapButton
import com.bytelegend.app.client.ui.bootstrap.BootstrapListGroup
import com.bytelegend.app.client.ui.bootstrap.BootstrapListGroupItem
import com.bytelegend.app.client.ui.bootstrap.BootstrapOverlayTrigger
import com.bytelegend.app.client.ui.bootstrap.BootstrapPagination
import com.bytelegend.app.client.ui.bootstrap.BootstrapPaginationEllipsis
import com.bytelegend.app.client.ui.bootstrap.BootstrapPaginationFirst
import com.bytelegend.app.client.ui.bootstrap.BootstrapPaginationItem
import com.bytelegend.app.client.ui.bootstrap.BootstrapPaginationLast
import com.bytelegend.app.client.ui.bootstrap.BootstrapPaginationNext
import com.bytelegend.app.client.ui.bootstrap.BootstrapPaginationPrev
import com.bytelegend.app.client.ui.bootstrap.BootstrapSpinner
import com.bytelegend.app.client.ui.bootstrap.BootstrapTabContainer
import com.bytelegend.app.client.ui.bootstrap.BootstrapTooltip
import com.bytelegend.app.shared.entities.mission.Pagination
import com.bytelegend.app.shared.entities.mission.Tutorial
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.client.app.page.game
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.MultiSelect
import com.bytelegend.client.app.ui.Option
import com.bytelegend.client.app.web.getMissionTutorial
import com.bytelegend.client.utils.JSArrayBackedList
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinext.js.jsObject
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.classes
import react.PropsWithChildren
import react.RBuilder
import react.State
import react.dom.div
import react.dom.onClick
import react.dom.span
import react.functionComponent
import react.setState

interface TutorialTabProps : GameProps {
    var missionId: String
    var initLocales: List<Locale>
    var initTutorials: Pagination<Tutorial>
}

interface TutorialTabState : State {
    var locales: List<Locale>
    var tutorials: Pagination<Tutorial>
    var activeTutorialIndex: Int

    // TODO dirty is not used now
    // The page data is dirty when filters are changed.
    var dirty: Boolean
    var loadingTutorials: Boolean
    var loadingTutorialContent: Boolean
}

private val tooltipFunctionalComponent = functionComponent<PropsWithChildren> { props ->
    BootstrapTooltip {
        attrs.id = "contribute-tutorials-tooltip"
        attrs.className = props.asDynamic().className
        attrs.style = props.asDynamic().style
        attrs.arrowProps = props.asDynamic().arrowProps
        attrs.placement = props.asDynamic().placement
        attrs.popper = props.asDynamic().popper
        attrs.show = props.asDynamic().show
        attrs.ref = props.asDynamic().ref
        +game.i("ContributeBetterTutorialsTooltip")
    }
}

class TutorialTab(props: TutorialTabProps) : GameUIComponent<TutorialTabProps, TutorialTabState>(props) {
    override fun TutorialTabState.init(props: TutorialTabProps) {
        tutorials = props.initTutorials.resortPage()
        locales = props.initLocales
        activeTutorialIndex = 0
        loadingTutorials = false
        loadingTutorialContent = true
    }

    private fun RBuilder.localeFilter() {
        // language multiselect
        // order by: multiselect
        //  update time, upvote
        // type: video/article/youtube
        span {
            attrs.classes = jsObjectBackedSetOf("mission-modal-tutorial-filter-name")
            +game.i("Language")
        }
        child(MultiSelect::class) {
            attrs.id = "tutorial-locale-filter"
            attrs.allOptions = Locale.values().map { Option(it.name, it.displayName) }
            attrs.initOptions = state.locales.map { Option(it.name, it.displayName) }
            attrs.onSelectComplete = { selectedOptions ->
                val newLocales = selectedOptions.map { Locale.of(it.value) }.toMutableList()
                if (state.locales.size == 1 && state.locales.first() == Locale.ALL && newLocales.size > 1) {
                    newLocales.remove(Locale.ALL)
                }
                setState {
                    dirty = newLocales != state.locales
                    locales = newLocales
                }
                newLocales.map { Option(it.name, it.displayName) }
            }
        }
    }

    private fun RBuilder.orderBy() {
        span {
            attrs.classes = jsObjectBackedSetOf("mission-modal-tutorial-filter-name")
            +game.i("OrderBy")
        }
        child(MultiSelect::class) {
            attrs.configuration = {
                isDisabled = true
            }
            attrs.allOptions = listOf(Option("updateTime", game.i("UpdateTime")), Option("upvote", game.i("Upvote")))
            attrs.initOptions = listOf(Option("updateTime", game.i("UpdateTime")))
        }
    }

    private fun RBuilder.typeFilter() {
        span {
            attrs.classes = jsObjectBackedSetOf("mission-modal-tutorial-filter-name")
            +game.i("Type")
        }
        child(MultiSelect::class) {
            attrs.configuration = {
                isDisabled = true
            }
            attrs.allOptions = listOf()
            attrs.initOptions = listOf()
        }
    }

    override fun RBuilder.render() {
        div {
            attrs.classes = jsObjectBackedSetOf("mission-modal-tutorial-tab")
            div {
                attrs.classes = jsObjectBackedSetOf("mission-modal-tutorial-filters")
                localeFilter()
                orderBy()
                typeFilter()
                BootstrapButton {
                    attrs.className = "mission-modal-tutorial-filter-refresh-button"
                    +game.i("Refresh")
                    attrs.onClick = {
                        refresh(state.tutorials.pageNumber)
                    }
                }
            }
            div {
                attrs.classes = jsObjectBackedSetOf("mission-modal-tutorial-content")

                if (state.loadingTutorials) {
                    BootstrapSpinner {
                        attrs.animation = "border"
                        attrs.className = "tutorial-spinner"
                    }
                }

                BootstrapTabContainer {
                    attrs.id = "left-tabs-example"
                    div {
                        attrs.classes = jsObjectBackedSetOf("mission-modal-tutorial-content-left")
                        div {
                            attrs.classes = jsObjectBackedSetOf("mission-modal-tutorial-content-left-tutorials")
                            BootstrapListGroup {
                                attrs.className = "flex-column"

                                state.tutorials.items.forEachIndexed { index, item ->
                                    BootstrapListGroupItem {
                                        if (state.activeTutorialIndex == index) {
                                            attrs.variant = "primary"
                                        }
                                        attrs.className = "mission-modal-tutorial-item"
                                        +item.title
                                        attrs.onClick = {
                                            setState {
                                                activeTutorialIndex = index
                                            }
                                        }
                                    }
                                }
                            }

                            div {
                                attrs.classes = jsObjectBackedSetOf("mission-modal-tutorial-add-div")
                                BootstrapOverlayTrigger {
                                    attrs.placement = "top"
                                    attrs.delay = jsObject<dynamic> {
                                        show = 250
                                        hide = 400
                                    }
                                    attrs.overlay = tooltipFunctionalComponent
                                    BootstrapButton {
                                        attrs.className = "mission-modal-tutorial-add-button"
                                        +"+ ${game.i("ContributeBetterTutorials")}"
                                        attrs.onClick = {
                                            window.open(buildMissionYamlEditGitHubUrl(), "_blank")
                                        }
                                    }
                                }

                                div {
                                    attrs.classes = jsObjectBackedSetOf("mission-modal-tutorial-add-help-icon")
                                    attrs.onClick = {
                                        val url = if (game.locale == Locale.ZH_HANS)
                                            "https://github.com/ByteLegend/ByteLegend/blob/master/docs/zh_hans/CONTRIBUTING.md#%E8%B4%A1%E7%8C%AE%E6%9B%B4%E5%A5%BD%E7%9A%84%E6%95%99%E7%A8%8B"
                                        else
                                            "https://github.com/ByteLegend/ByteLegend/blob/master/docs/en/CONTRIBUTING.md#contribute-better-tutorials"
                                        window.open(url, "_blank")
                                    }
                                }
                            }
                        }

                        div {
                            attrs.classes = jsObjectBackedSetOf("mission-modal-tutorial-content-left-pagination")
                            BootstrapPagination {
                                attrs.size = "sm"
                                BootstrapPaginationFirst {
                                    attrs.onClick = { refresh(1) }
                                }
                                BootstrapPaginationPrev {
                                    attrs.onClick = { refresh(state.tutorials.pageNumber - 1) }
                                }
                                for (page in 1..3) {
                                    if (page <= state.tutorials.totalPages) {
                                        BootstrapPaginationItem {
                                            +page.toString()
                                            if (page == state.tutorials.pageNumber) {
                                                attrs.active = true
                                            }
                                        }
                                    }
                                }
                                if (state.tutorials.totalPages > 3) {
                                    BootstrapPaginationEllipsis {}
                                }

                                BootstrapPaginationNext {
                                    attrs.onClick = { refresh(state.tutorials.pageNumber + 1) }
                                }
                                BootstrapPaginationLast {
                                    attrs.onClick = { refresh(state.tutorials.totalPages) }
                                }
                            }
                        }
                    }
                    div {
                        attrs.classes = jsObjectBackedSetOf("mission-modal-tutorial-content-right")
                        if (!state.loadingTutorials && state.tutorials.items.isNotEmpty()) {
                            child(TutorialContent::class) {
                                attrs.game = game
                                attrs.tutorial = state.tutorials.items[state.activeTutorialIndex]
                            }
                        }
                    }
                }
            }
        }
    }

    private fun buildMissionYamlEditGitHubUrl(): String {
        return "https://github.com/ByteLegend/game-data/edit/master/${game.activeScene.map.id}/missions/${props.missionId}.yml"
    }

    private fun refresh(pageNumber: Int) {
        var number = pageNumber
        if (pageNumber < 1) {
            number = 1
        }
        if (number > state.tutorials.totalPages) {
            number = state.tutorials.totalPages
        }

        setState {
            loadingTutorials = true
        }
        GlobalScope.launch {
            val newData = getMissionTutorial(props.missionId, number, state.locales)
            setState {
                tutorials = newData.resortPage()
                loadingTutorials = false
            }
        }
    }

    private fun Pagination<Tutorial>.resortPage(): Pagination<Tutorial> {
        val groupByCurrentLanguage = items.groupBy { it.languages.contains(game.locale) }
        val result = JSArrayBackedList<Tutorial>()
        result.addAll(groupByCurrentLanguage[true] ?: emptyList())
        result.addAll(groupByCurrentLanguage[false] ?: emptyList())
        return Pagination(
            result,
            totalPages,
            pageNumber,
            pageSize
        )
    }
}
