package com.bytelegend.client.app.ui.mission

import com.bytelegend.app.client.ui.bootstrap.BootstrapButton
import com.bytelegend.app.client.ui.bootstrap.BootstrapListGroup
import com.bytelegend.app.client.ui.bootstrap.BootstrapListGroupItem
import com.bytelegend.app.client.ui.bootstrap.BootstrapPagination
import com.bytelegend.app.client.ui.bootstrap.BootstrapPaginationEllipsis
import com.bytelegend.app.client.ui.bootstrap.BootstrapPaginationFirst
import com.bytelegend.app.client.ui.bootstrap.BootstrapPaginationItem
import com.bytelegend.app.client.ui.bootstrap.BootstrapPaginationLast
import com.bytelegend.app.client.ui.bootstrap.BootstrapPaginationNext
import com.bytelegend.app.client.ui.bootstrap.BootstrapPaginationPrev
import com.bytelegend.app.client.ui.bootstrap.BootstrapSpinner
import com.bytelegend.app.client.ui.bootstrap.BootstrapTabContainer
import com.bytelegend.app.shared.entities.mission.Pagination
import com.bytelegend.app.shared.entities.mission.Tutorial
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.MultiSelect
import com.bytelegend.client.app.ui.Option
import com.bytelegend.client.app.web.getMissionTutorial
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.classes
import react.RBuilder
import react.RState
import react.dom.div
import react.dom.span
import react.setState

interface TutorialTabProps : GameProps {
    var missionId: String
    var initLocales: List<Locale>
    var initTutorials: Pagination<Tutorial>
}

interface TutorialTabState : RState {
    var locales: List<Locale>
    var tutorials: Pagination<Tutorial>
    var activeTutorialIndex: Int

    // The page data is dirty when filters are changed.
    var dirty: Boolean
    var loadingTutorials: Boolean
    var loadingTutorialContent: Boolean
}

class TutorialTab(props: TutorialTabProps) : GameUIComponent<TutorialTabProps, TutorialTabState>(props) {
    override fun TutorialTabState.init(props: TutorialTabProps) {
        tutorials = props.initTutorials
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
            attrs.classes = setOf("mission-modal-tutorial-filter-name")
            +game.i("Language")
        }
        child(MultiSelect::class) {
            attrs.id = "tutorial-locale-filter"
            attrs.allOptions = Locale.values().map { Option(it.name, it.displayName) }
            attrs.initOptions = state.locales.map { Option(it.name, it.displayName) }
            attrs.onSelectComplete = { selectedOptions ->
                val newLocales = selectedOptions.map { Locale.of(it.value) }.let {
                    if (it.contains(Locale.ALL)) listOf(Locale.ALL) else it
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
            attrs.classes = setOf("mission-modal-tutorial-filter-name")
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
            attrs.classes = setOf("mission-modal-tutorial-filter-name")
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
            attrs.classes = setOf("mission-modal-tutorial-tab")
            div {
                attrs.classes = setOf("mission-modal-tutorial-filters")
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
                attrs.classes = setOf("mission-modal-tutorial-content")

                if (state.loadingTutorials) {
                    BootstrapSpinner {
                        attrs.animation = "border"
                        attrs.className = "tutorial-spinner"
                    }
                }

                BootstrapTabContainer {
                    attrs.id = "left-tabs-example"
                    div {
                        attrs.classes = setOf("mission-modal-tutorial-content-left")
                        div {
                            attrs.classes = setOf("mission-modal-tutorial-content-left-tutorials")
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
                        }

                        div {
                            attrs.classes = setOf("mission-modal-tutorial-content-left-pagination")
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
                        attrs.classes = setOf("mission-modal-tutorial-content-right")
                        if (!state.loadingTutorials) {
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

    private fun refresh(pageNumber: Int) {
        var number = pageNumber
        if (pageNumber < 1) {
            number = 1
        }
        if (number > state.tutorials.totalPages) {
            number = state.tutorials.totalPages
        }

        if (number != state.tutorials.pageNumber || state.dirty) {
            setState {
                loadingTutorials = true
            }
            GlobalScope.launch {
                val newData = getMissionTutorial(props.missionId, pageNumber, state.locales)
                setState {
                    tutorials = newData
                    loadingTutorials = false
                }
            }
        }
    }
}
