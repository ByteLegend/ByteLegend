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
 * Upon mission completion, update states or items.
 */
data class OnFinishSpec(
    val items: OnFinishItemsChange = OnFinishItemsChange(),
    val states: OnFinishStatesChange = OnFinishStatesChange(),
    /**
     * The achievements to get after finishing the mission
     */
    val achievements: List<String> = emptyList(),
    /**
     * Add coin to player's balance. Must be positive.
     */
    val coin: Int = 0
)

data class OnFinishItemsChange(
    val add: List<String> = emptyList(),
    val remove: List<String> = emptyList()
) {
    @JsonIgnore
    fun isEmpty() = add.isEmpty() && remove.isEmpty()
}

data class OnFinishStatesChange(
    val put: Map<String, String> = emptyMap(),
) {
    @JsonIgnore
    fun isEmpty() = put.isEmpty()
}
