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
// package com.bytelegend.app.shared.entities
//
// import com.bytelegend.app.shared.entities.MissionType.None
//
// val NON_CHALLENGE_MISSION = MissionSpec(None, 0, "")
//
// data class Mission(
//    val id: String,
//    val title: String,
//    val children: List<Mission> = emptyList(),
//    val tutorials: List<Tutorial> = emptyList(),
//    val discussions: String = "",
//    /**
//     * If true, this mission will be displayed in roadmap
//     * as a "checkpoint".
//     *
//     * Default true.
//     */
//    val checkpoint: Boolean = true,
//    val spec: MissionSpec = NON_CHALLENGE_MISSION,
//    val next: String?
// )
//
// class MissionSpec(
//    val type: MissionType,
//    val star: Int,
//    /**
//     * Type == Star, PR -> repo address
//     * Type == Question -> correct answer regex
//     */
//    val spec: String,
// )
//
// enum class MissionType {
//    // It has no challenges.
//    None,
//
//    // Star a GitHub repository
//    Star,
//
//    RememberBravePeople,
//
//    // Create a PR
//    PR,
//
//    // Answer a question
//    Question
// }
//
// fun Mission.flatten(): List<Mission> {
//    val ret = mutableListOf(this)
//    children.forEach {
//        ret.addAll(it.flatten())
//    }
//    return ret
// }
//
