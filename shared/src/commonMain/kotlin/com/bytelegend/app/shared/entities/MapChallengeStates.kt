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

package com.bytelegend.app.shared.entities

import com.bytelegend.app.shared.annotations.DynamoDbBean

/**
 * Records the challenge states on a specific map:
 *
 * - Challenge stars/accomplished.
 */
@DynamoDbBean
open class MapChallengeStates {
    /**
     * {
     *   "challenge1": {
     *       "accomplished": true,
     *       "star": 3
     *   },
     *   "challenge2": {
     *       "accomplished": true,
     *       "star": 0
     *   }
     * }
     */
    open var challenges: MutableMap<String, AccomplishmentState> = mutableMapOf()
}

@DynamoDbBean
class AccomplishmentState(
    var accomplished: Boolean = false,
    var star: Int = 0
)

fun ChallengeAnswers.toAccomplishmentState() = AccomplishmentState(accomplished, star)
