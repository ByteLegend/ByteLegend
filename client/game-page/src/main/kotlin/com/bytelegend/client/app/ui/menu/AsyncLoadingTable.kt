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

package com.bytelegend.client.app.ui.menu

import com.bytelegend.app.client.ui.bootstrap.BootstrapAlert
import com.bytelegend.app.client.ui.bootstrap.BootstrapTable
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.loadingSpinner
import com.bytelegend.client.app.ui.setState
import com.bytelegend.client.app.ui.unsafeSpan
import com.bytelegend.client.app.web.getText
import kotlinext.js.jso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import react.ChildrenBuilder
import react.Component
import react.Fragment
import react.State
import react.create
import react.dom.html.AnchorTarget
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.th
import react.dom.html.ReactHTML.thead
import react.dom.html.ReactHTML.tr

interface AsyncLoadingTableState : State {
    var data: Array<dynamic>
}

abstract class AsyncLoadingTable<S : AsyncLoadingTableState> : Component<GameProps, S>() {
    abstract val url: String
    private var loading = false

    init {
        state = jso()
    }

    override fun render() = Fragment.create {
        textBeforeTable()

        if (state.data == undefined && loading) {
            loadingSpinner()
        } else if (state.data == undefined) {
            GlobalScope.launch {
                val json = getText(url)
                setState {
                    data = JSON.parse(json)
                }
            }
            loading = true
        } else {
            BootstrapTable {
                striped = true
                bordered = true
                hover = true
                thead {
                    tr {
                        tableHeaderBuilder()
                    }
                }
                tbody {
                    state.data.forEach {
                        tr {
                            tableRowBuilder(it)
                        }
                    }
                }
            }
        }
    }

    abstract fun ChildrenBuilder.textBeforeTable()
    abstract fun ChildrenBuilder.tableHeaderBuilder()
    abstract fun ChildrenBuilder.tableRowBuilder(rowData: dynamic)
}

class OpenSourceSoftwareTable : AsyncLoadingTable<AsyncLoadingTableState>() {
    override val url: String
        get() = props.game.resolve("/misc/oss.json")

    override fun ChildrenBuilder.textBeforeTable() {
        p {
            +props.game.i("ThisGameWouldNotExistWithoutOpenSourceSoftware")
        }
    }

    override fun ChildrenBuilder.tableHeaderBuilder() {
        th { +props.game.i("Software") }
        th { +props.game.i("License") }
    }

    override fun ChildrenBuilder.tableRowBuilder(rowData: dynamic) {
        td {
            a {
                href = rowData.url.toString()
                target = AnchorTarget._blank
                +(rowData.creditName.toString())
            }
        }
        td {
            if (rowData.licenseUrl) {
                a {
                    href = rowData.licenseUrl.toString()
                    target = AnchorTarget._blank
                    +(rowData.license.toString())
                }
            } else {
                +(rowData.license.toString())
            }
        }
    }
}

interface GameMaterialTableState : AsyncLoadingTableState {
    var showAlert: Boolean
}

class GameMaterialTable : AsyncLoadingTable<GameMaterialTableState>() {
    override val url: String
        get() = props.game.resolve("/misc/material.json")

    init {
        state = jso { showAlert = true }
    }

    override fun ChildrenBuilder.textBeforeTable() {
        if (state.showAlert) {
            BootstrapAlert {
                show = true
                variant = "success"
                dismissible = "true"
                onClose = {
                    setState { showAlert = false }
                }
                unsafeSpan(props.game.i("ContactUsIfYouThinkUsMisuse"))
            }
        }
        +props.game.i("ThisGameWouldNotExistWithoutArtwork")
    }

    override fun ChildrenBuilder.tableHeaderBuilder() {
        th { +props.game.i("Material") }
        th { +props.game.i("Author") }
        th { +props.game.i("License") }
    }

    override fun ChildrenBuilder.tableRowBuilder(rowData: dynamic) {
        td {
            a {
                href = rowData.url.toString()
                target = AnchorTarget._blank
                +(rowData.name.toString())
            }
        }
        td { +(rowData.artist.toString()) }

        td {
            a {
                href = rowData.licenceUrl.toString()
                target = AnchorTarget._blank
                +(rowData.licence.toString())
            }
        }
    }
}
