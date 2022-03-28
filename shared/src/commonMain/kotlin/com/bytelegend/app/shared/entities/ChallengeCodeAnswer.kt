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

data class ChallengeCodeAnswer(
    // e.g. https://github.com/a/b/pull/12345
    val pullRequestHtmlUrl: String?,

    // this is only used when we need to create a new pull request to avoid potential conflicts
    // for example:
    // 1. Repository is version A.
    // 2. Player 1 opens version A and starts working on it.
    // 3. Player 2 opens version A, changes it to version B, submits it, then repository becomes version B.
    // 4. Player 1 submits code (version C) based on version A, creates a pull request.
    //    Now the pull request is comparing version B <-> version C. It should be comparing version A <-> version C because version A is the "fork point" for player 1.
    //    in this case, this baseRef is used for player 1 to create a pull request.
    val baseRef: String?,

    // key: file path, value: changed file
    // e.g.
    // {"src/main/com/bytelegend/Main.java", "package com.bytelegend..."}
    val changes: Map<String, String>
)
