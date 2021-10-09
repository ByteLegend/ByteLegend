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

data class ChallengeSpec(
    val id: String,
    val type: ChallengeType,
    val spec: String,
    val star: Int = 0,
    /**
     * A TL;DR description i18n id of this challenge
     */
    val tldr: String = "",
    /**
     * The detail description i18n id of this challenge
     */
    val readme: String = ""
)

enum class ChallengeType(val withGitHubRepo: Boolean) {
    None(false),
    Star(true),
    HeroNoticeboard(true),
    PullRequest(true),
    Question(false),
    TextContent(false)
}
