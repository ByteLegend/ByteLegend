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

package com.bytelegend.app.client.misc

private val GITHUB_BLOB_URL_PATTERN = "https://github.com/([\\w_-]+)/([\\w_-]+)/blob/([\\w_.-]+)/(.*)".toRegex()

// https://github.com/gradle/gradle/blob/master/README.md -> https://raw.githubusercontent.com/gradle/gradle/master/README.md
fun githubUrlToRawGithubUserContentUrl(url: String): String {
    if (url.startsWith("https://github.com")) {
        val result = GITHUB_BLOB_URL_PATTERN.matchEntire(url) ?: return url
        return "https://raw.githubusercontent.com/${result.groupValues[1]}/${result.groupValues[2]}/${result.groupValues[3]}/${result.groupValues[4]}"
    } else {
        return url
    }
}
