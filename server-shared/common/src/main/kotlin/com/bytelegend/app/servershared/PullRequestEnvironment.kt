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

package com.bytelegend.app.servershared

/**
 * Default environment for pull request:
 *
 * - Remote `origin` points to base repo of the PR.
 * - Remote `pr` points to head repo of the PR, i.e.:
 *   - If the PR is from forked repository, it points to that forked repository.
 *   - Otherwise, it points to the same repository as `origin`.
 */
data class PullRequestEnvironment(
    val repoFullName: String,
    val author: String,
    val number: Int,
    val title: String,
    val headSha: String,
    val localBranch: String
) {
    companion object {
        fun fromSystemProperty() = PullRequestEnvironment(
            systemProperty("prRepoFullName"),
            systemProperty("prAuthor"),
            systemProperty("prNumber").toInt(),
            systemProperty("prTitle"),
            systemProperty("prHeadSha"),
            systemProperty("prLocalBranch"),
        )
    }
}

fun systemProperty(name: String) = System.getProperty(name) ?: throw IllegalArgumentException("System property $name not found!")
