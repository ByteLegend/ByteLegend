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
package com.bytelegend.buildsupport

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Instant

/**
 * Upon successful release, update `versions.json` on `master`.
 *
 * Because releasing branch and `master` branch may conflict, we can't use something like
 * `git-auto-commit-action`. We use GitHub API to make sure updating `versions.json` successfully.
 */
open class UpdateVersionsJsonTask : DefaultTask() {
    private val versionsJsonApi = "https://api.github.com/repos/ByteLegend/ByteLegend/contents/versions.json"
    private val githubToken: String by lazy {
        System.getenv("GITHUB_TOKEN") ?: throw IllegalArgumentException("GITHUB_TOKEN not found!")
    }
    private val buildTimestamp: String by lazy {
        project.rootProject.extensions.extraProperties["buildTimestamp"].toString()
    }

    private val httpClient: HttpClient by lazy { HttpClient.newHttpClient() }
    private val currentCommit: String by lazy {
        System.getenv("GITHUB_SHA")
            ?: Runtime.getRuntime().exec("git rev-parse HEAD").let {
                require(it.waitFor() == 0)
                it.inputStream.bufferedReader().readText().trim()
            }
    }

    @TaskAction
    fun run() {
        if (project.hasProperty("updateVersions")) {
            val latestRelease = BuildVersion(currentCommit, buildTimestamp, Instant.now())
            try {
                updateVersionsJsonOnMaster(latestRelease)
            } catch (e: Throwable) {
                println("Retry with error")
                e.printStackTrace()
                updateVersionsJsonOnMaster(latestRelease)
            }
        } else {
            println("Skip updating versions JSON because `updateVersions` property not set.")
            return
        }
    }

    private fun httpRequest(config: HttpRequest.Builder.() -> Unit): String {
        val request = HttpRequest.newBuilder()
            .header("Accept", "application/vnd.github.v3+json")
            .header("Authorization", "token $githubToken")
            .uri(URI.create(versionsJsonApi))
            .apply(config)
            .build()
        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        require(response.statusCode() < 400) {
            "Error requesting $versionsJsonApi:\n${response.body()}"
        }
        return response.body()
    }

    private fun updateVersionsJsonOnMaster(latestRelease: BuildVersion) {
        val (sha, currentVersions) = readLatestVersionsJsonOnMaster()

        val bot = CommitterOrAuthor("ByteLegendBot", "bot@bytelegend.com")
        val payload = PutContentRequest(
            "Release $buildTimestamp",
            base64Encode(toPrettyJson(currentVersions + latestRelease)),
            sha,
            "master",
            bot,
            bot
        )
        httpRequest {
            PUT(HttpRequest.BodyPublishers.ofString(toUglyJson(payload)))
        }
    }

    private fun readLatestVersionsJsonOnMaster(): Pair<String, List<BuildVersion>> {
        val jsonResponse = fromJson(httpRequest {
            GET()
        })
        val sha = jsonResponse.get("sha").textValue()
        val base64EncodedContent = jsonResponse.get("content").textValue().replace("\n", "")
        val content = base64Decode(base64EncodedContent)
        return sha to readBuildVersions(content)
    }
}

// https://docs.github.com/en/rest/reference/repos#create-or-update-file-contents
data class PutContentRequest(
    val message: String,
    val content: String,
    val sha: String,
    val branch: String,
    val committer: CommitterOrAuthor,
    val author: CommitterOrAuthor
)

data class CommitterOrAuthor(
    val name: String,
    val email: String
)
