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

package com.bytelegend.client.app.ui.menu

import com.bytelegend.app.client.ui.bootstrap.BootstrapAlert
import com.bytelegend.client.app.ui.setState
import com.bytelegend.client.app.ui.unsafeSpan
import kotlinx.js.jso
import react.ChildrenBuilder
import react.dom.html.AnchorTarget
import react.dom.html.ReactHTML

interface GameMaterialTableState : AsyncLoadingTableState<dynamic> {
    var showAlert: Boolean
}

class GameMaterialTable : AsyncLoadingTable<dynamic, AsyncLoadingTableProps, GameMaterialTableState>() {
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
        ReactHTML.th { +props.game.i("Material") }
        ReactHTML.th { +props.game.i("Author") }
        ReactHTML.th { +props.game.i("License") }
    }

    override fun ChildrenBuilder.tableRowBuilder(index: Int, rowData: dynamic) {
        ReactHTML.td {
            ReactHTML.a {
                href = rowData.url.toString()
                target = AnchorTarget._blank
                +(rowData.name.toString())
            }
        }
        ReactHTML.td { +(rowData.artist.toString()) }

        ReactHTML.td {
            ReactHTML.a {
                href = rowData.licenceUrl.toString()
                target = AnchorTarget._blank
                +(rowData.licence.toString())
            }
        }
    }
}
