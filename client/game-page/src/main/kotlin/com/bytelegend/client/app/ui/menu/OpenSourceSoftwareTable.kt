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

import react.ChildrenBuilder
import react.dom.html.AnchorTarget
import react.dom.html.ReactHTML

class OpenSourceSoftwareTable : AsyncLoadingTable<dynamic, AsyncLoadingTableProps, AsyncLoadingTableState<dynamic>>() {
    override val url: String
        get() = props.game.resolve("/misc/oss.json")

    override fun ChildrenBuilder.textBeforeTable() {
        ReactHTML.p {
            +props.game.i("ThisGameWouldNotExistWithoutOpenSourceSoftware")
        }
    }

    override fun ChildrenBuilder.tableHeaderBuilder() {
        ReactHTML.th { +props.game.i("Software") }
        ReactHTML.th { +props.game.i("License") }
    }

    override fun ChildrenBuilder.tableRowBuilder(index: Int, rowData: dynamic) {
        ReactHTML.td {
            ReactHTML.a {
                href = rowData.url.toString()
                target = AnchorTarget._blank
                +(rowData.creditName.toString())
            }
        }
        ReactHTML.td {
            if (rowData.licenseUrl) {
                ReactHTML.a {
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
