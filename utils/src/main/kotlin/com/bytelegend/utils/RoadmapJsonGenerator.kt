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
package com.bytelegend.utils

data class RoadmapJsonData(
    val nodes: List<RoadmapMissionNode>,
    val links: List<RoadmapMissionLink>,
    val categories: List<RoadmapMissionRegion>
)

data class RoadmapMissionNode(
    val id: String,
    val name: String,
    val symbolSize: Number,
    val x: Int,
    val y: Int,
    val value: Int,
    val category: Int
)

data class RoadmapMissionLink(
    val source: String,
    val target: String,
    // https://github.com/apache/echarts/issues/4062
    val label: Map<String, Any> = mapOf("show" to true)
)

data class RoadmapMissionRegion(
    val name: String
)
