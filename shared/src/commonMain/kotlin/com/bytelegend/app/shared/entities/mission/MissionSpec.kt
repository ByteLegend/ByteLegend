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
package com.bytelegend.app.shared.entities.mission

import com.bytelegend.app.shared.annotations.JsonIgnore

/**
 * MissionSpec is immutable data structure to store the mission definitions.
 * It's usually stored in GitHub repositories so that it can be contributed by community.
 */
data class MissionSpec(
    /**
     * The id of the mission
     */
    val id: String,
    /**
     * The i18n id of the mission
     */
    val title: String,
    /**
     * The challenge in this mission
     */
    val challenges: List<ChallengeSpec> = emptyList(),
    /**
     * The tutorials in this mission
     */
    val tutorials: List<Tutorial> = emptyList(),
    /**
     * The discussions in this mission
     */
    val discussions: DiscussionsSpec = DEFAULT_DISCUSSIONS_SPEC,
    /**
     * Some information of this mission is not maintained in YAML,
     * but in map JSON. See {@link GameMapMission}.
     */
    val mapMissionSpec: MapMissionSpec? = null,
    /**
     * Special actions to be performed when the mission is accomplished
     */
    val onFinish: ChangeSpec = ChangeSpec(),
    /**
     * Special actions to be performed when the mission is accomplished
     */
    val onItemUsed: Map<String, ChangeSpec> = emptyMap(),

    /**
     * How much does the tutorials cost?
     */
    val tutorialsPrice: Int = 0
) {
    val totalStars
        @JsonIgnore get() = challenges.sumOf { it.star }
}
