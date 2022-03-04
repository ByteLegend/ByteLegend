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

package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.GameMission
import com.bytelegend.app.client.api.GameSceneMatrixElement
import com.bytelegend.app.client.utils.JSArrayBackedList

class DefaultGameSceneMatrixElement(
    override var blocker: Int
) : GameSceneMatrixElement {
    override var mission: GameMission? = null
    override val missionsAround: MutableList<GameMission> = JSArrayBackedList()
}
