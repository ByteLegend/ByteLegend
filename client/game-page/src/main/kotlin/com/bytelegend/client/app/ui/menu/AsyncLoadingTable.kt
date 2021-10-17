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
import com.bytelegend.app.client.ui.bootstrap.BootstrapSpinner
import com.bytelegend.app.client.ui.bootstrap.BootstrapTable
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.unsafeSpan
import com.bytelegend.client.app.web.get
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.TR
import react.RBuilder
import react.RComponent
import react.State
import react.dom.RDOMBuilder
import react.dom.a
import react.dom.p
import react.dom.tbody
import react.dom.td
import react.dom.th
import react.dom.thead
import react.dom.tr
import react.setState

interface AsyncLoadingTableState : State {
    var data: Array<dynamic>
}

abstract class AsyncLoadingTable<S : AsyncLoadingTableState> : RComponent<GameProps, S>() {
    abstract val url: String
    private var loading = false
    override fun RBuilder.render() {
        textBeforeTable()

        if (state.data == undefined && loading) {
            BootstrapSpinner {
                attrs.animation = "border"
            }
        } else if (state.data == undefined) {
            GlobalScope.launch {
                val json = get(url)
                setState {
                    data = JSON.parse(json)
                }
            }
            loading = true
        } else {
            BootstrapTable {
                attrs.striped = true
                attrs.bordered = true
                attrs.hover = true
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

    abstract fun RBuilder.textBeforeTable()
    abstract fun RDOMBuilder<TR>.tableHeaderBuilder()
    abstract fun RDOMBuilder<TR>.tableRowBuilder(rowData: dynamic)
}

class OpenSourceSoftwareTable : AsyncLoadingTable<AsyncLoadingTableState>() {
    override val url: String
        get() = props.game.resolve("/misc/oss.json")

    override fun RBuilder.textBeforeTable() {
        p {
            +props.game.i("ThisGameWouldNotExistWithoutOpenSourceSoftware")
        }
    }

    override fun RDOMBuilder<TR>.tableHeaderBuilder() {
        th { +props.game.i("Software") }
        th { +props.game.i("License") }
    }

    override fun RDOMBuilder<TR>.tableRowBuilder(rowData: dynamic) {
        td {
            a {
                attrs.href = rowData.url.toString()
                attrs.target = "_blank"
                +(rowData.creditName.toString())
            }
        }
        td {
            a {
                attrs.href = rowData.licenseUrl.toString()
                attrs.target = "_blank"
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

    override fun GameMaterialTableState.init() {
        showAlert = true
    }

    override fun RBuilder.textBeforeTable() {
        if (state.showAlert) {
            BootstrapAlert {
                attrs.show = true
                attrs.variant = "success"
                attrs.dismissible = "true"
                attrs.onClose = {
                    setState { showAlert = false }
                }
                unsafeSpan(props.game.i("ContactUsIfYouThinkUsMisuse"))
            }
        }
        +props.game.i("ThisGameWouldNotExistWithoutArtwork")
    }

    override fun RDOMBuilder<TR>.tableHeaderBuilder() {
        th { +props.game.i("Material") }
        th { +props.game.i("Author") }
        th { +props.game.i("License") }
    }

    override fun RDOMBuilder<TR>.tableRowBuilder(rowData: dynamic) {
        td {
            a {
                attrs.href = rowData.url.toString()
                attrs.target = "_blank"
                +(rowData.name.toString())
            }
        }
        td { +(rowData.artist.toString()) }

        td {
            a {
                attrs.href = rowData.licenceUrl.toString()
                attrs.target = "_blank"
                +(rowData.licence.toString())
            }
        }
    }
}
