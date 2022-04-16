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
@file:Suppress("EXPERIMENTAL_API_USAGE", "OPT_IN_USAGE")

package com.bytelegend.client.app.ui.menu

import com.bytelegend.app.client.ui.bootstrap.BootstrapButton
import com.bytelegend.app.client.ui.bootstrap.BootstrapTable
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.loadingSpinner
import com.bytelegend.client.app.ui.setState
import com.bytelegend.client.app.web.getText
import csstype.ClassName
import kotlinx.js.jso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import react.ChildrenBuilder
import react.Component
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.thead
import react.dom.html.ReactHTML.tr

interface AsyncLoadingTableProps : GameProps

interface AsyncLoadingTableState<T> : State {
    var data: Array<T>?
    var currentPage: Int
}

abstract class AsyncLoadingTable<T, P : AsyncLoadingTableProps, S : AsyncLoadingTableState<T>>(private val pagination: Boolean = false) : Component<P, S>() {
    abstract val url: String
    open val isLastPage: Boolean = true
    private var loading = false

    init {
        state = jso()
        state.currentPage = 1
    }

    open suspend fun transformData(data: Array<dynamic>): Array<T> {
        return data
    }

    override fun render() = Fragment.create {
        textBeforeTable()

        if (state.data == undefined && loading) {
            loadingSpinner()
        } else if (state.data == undefined) {
            loading = true
            GlobalScope.launch {
                val transformedData = transformData(JSON.parse(getText(url)))
                loading = false
                setState {
                    data = transformedData
                }
            }
            loadingSpinner()
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
                    state.data?.forEachIndexed { index: Int, row: dynamic ->
                        tr {
                            tableRowBuilder(index, row)
                        }
                    }
                }
            }

            if (pagination) {
                div {
                    className = ClassName("page-button-wrapper flex-center")
                    BootstrapButton {
                        className = "page-button"
                        disabled = (state.currentPage == 1)

                        +"<"
                        onClick = {
                            setState {
                                currentPage -= 1
                                data = undefined
                            }
                        }
                    }
                    BootstrapButton {
                        className = "page-button"
                        disabled = isLastPage
                        +">"
                        onClick = {
                            setState {
                                currentPage += 1
                                data = undefined
                            }
                        }
                    }
                }
            }
        }
    }

    open fun ChildrenBuilder.textBeforeTable() {}
    abstract fun ChildrenBuilder.tableHeaderBuilder()
    abstract fun ChildrenBuilder.tableRowBuilder(index: Int, rowData: T)
}
