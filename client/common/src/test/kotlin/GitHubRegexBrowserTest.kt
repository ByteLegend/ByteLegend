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

import com.bytelegend.app.client.misc.githubUrlToRawGithubUserContentUrl
import kotlin.test.Test
import kotlin.test.assertEquals

class GitHubRegexBrowserTest {
    @Test
    fun test() {
        assertEquals("https://raw.githubusercontent.com/gradle/gradle/master/README.md", githubUrlToRawGithubUserContentUrl("https://github.com/gradle/gradle/blob/master/README.md"))
        assertEquals("https://raw.githubusercontent.com/gradle/gradle/6.7.1/README.md", githubUrlToRawGithubUserContentUrl("https://github.com/gradle/gradle/blob/6.7.1/README.md"))
        assertEquals("https://raw.githubusercontent.com/ByteLegendQuest/fix-typo/master/README.md", githubUrlToRawGithubUserContentUrl("https://github.com/ByteLegendQuest/fix-typo/blob/master/README.md"))
    }
}
