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
package com.bytelegend.app.servershared.mock

import com.bytelegend.app.shared.entities.Player
import kotlin.random.Random

const val JAVA_MAP_ID = "JavaIsland"

val mockPlayer = Player().apply {
    id = "gh#ByteLegendBot"
    username = "ByteLegendBot"
    nickname = "ByteLegendBot"
    emails.add("bot@bytelegend.com")
    avatarUrl = "https://avatars0.githubusercontent.com/u/76512065?s=60&v=4"
    characterId = Random.nextInt(100) + 1
    map = JAVA_MAP_ID
    x = 6
    y = 90
}

val anonymousPlayer = Player().apply {
    id = "anon#Anonymous"
    username = "Anonymous"
    nickname = "Anonymous"
    map = JAVA_MAP_ID
}
