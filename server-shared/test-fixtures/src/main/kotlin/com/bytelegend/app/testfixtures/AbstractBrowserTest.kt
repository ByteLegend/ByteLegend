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
@file:Suppress("HttpUrlsUsage")

package com.bytelegend.app.testfixtures

import com.bytelegend.app.jsonmodel.generated.event.PullRequestGitHubEvent
import com.bytelegend.app.servershared.DefaultJsonMapper
import com.bytelegend.app.servershared.RRBDResourceProvider
import com.bytelegend.app.servershared.codechecker.INTERNAL_API_SECRET_HEADER_NAME
import com.bytelegend.app.shared.entities.PullRequestEventAction
import com.bytelegend.app.shared.entities.mission.ChallengeSpec
import com.bytelegend.app.shared.entities.mission.ChallengeType
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.logging.LogType
import org.openqa.selenium.logging.LoggingPreferences
import org.openqa.selenium.remote.CapabilityType
import org.testcontainers.Testcontainers.exposeHostPorts
import org.testcontainers.containers.BrowserWebDriverContainer
import org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode
import org.testcontainers.containers.DefaultRecordingFileFactory
import org.testcontainers.containers.VncRecordingContainer
import org.testcontainers.junit.jupiter.Testcontainers
import java.io.File
import java.io.InputStream
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Instant
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Supplier
import java.util.logging.Level

fun BrowserWebDriverContainer<Nothing>.safeStop() {
    try {
        stop()
    } catch (e: Throwable) {
        e.printStackTrace()
    }
}

private val counter = AtomicInteger(0)

abstract class AbstractByteLegendIntegrationTest {
    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
    abstract val gameServerPort: Int
    protected val defaultJsonMapper = DefaultJsonMapper(true)
    private val httpClient by lazy {
        HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NEVER)
            .build()
    }

    private fun createMockPullRequestEvent(
        playerId: String,
        challengeSpec: ChallengeSpec,
        prNumber: Int,
        action: PullRequestEventAction
    ) = PullRequestGitHubEvent().apply {
        this.action = action.name.lowercase()
        repository = PullRequestGitHubEvent.Repository()
        repository.owner = PullRequestGitHubEvent.Owner3()
        repository.owner.login = "ByteLegendQuest"
        repository.fullName = challengeSpec.spec.substringAfter("/")

        pullRequest = PullRequestGitHubEvent.PullRequest()
        pullRequest.number = prNumber.toLong()
        pullRequest.htmlUrl = "https://${challengeSpec.spec}/pull/${pullRequest.number}"
        pullRequest.nodeId = "mockNodeId"
        pullRequest.user = PullRequestGitHubEvent.User()
        pullRequest.user.login = playerId.substringAfter("#")

        pullRequest.state = if (action == PullRequestEventAction.OPENED) "open" else "merged"
        pullRequest.merged = action != PullRequestEventAction.OPENED

        pullRequest.head = PullRequestGitHubEvent.Head()
        pullRequest.head.ref = "mockBranch"
        pullRequest.head.sha = "shaOfPullRequest$prNumber"
        pullRequest.head.repo = PullRequestGitHubEvent.Repo()
        pullRequest.head.repo.fork = false
        pullRequest.head.repo.fullName = challengeSpec.spec.substringAfter("github.com/")

        pullRequest.base = PullRequestGitHubEvent.Base()
        pullRequest.base.ref = "main"
    }

    protected fun RRBDResourceProvider.finishChallenge(playerId: String, challengeId: String, problemNumber: Int = 0) {
        val (_, challengeSpec) = getMissionChallengeByChallengeId(challengeId)!!
        require(challengeSpec.type == ChallengeType.PullRequest) { "$challengeId must be PR challenge!" }
        val prOpenEvent: PullRequestGitHubEvent = createMockPullRequestEvent(playerId, challengeSpec, counter.incrementAndGet(), PullRequestEventAction.OPENED)
        sendWebhookFromJsonObject("pull_request", prOpenEvent)

        if (problemNumber != 0) {
            // PullRequestProblems
            val payload = mapOf(
                "repoFullName" to challengeSpec.spec.substringAfter("/"),
                "prAuthor" to playerId.substringAfter("#"),
                "prNumber" to prOpenEvent.pullRequest.number,
                "sha" to prOpenEvent.pullRequest.head.sha,
                "updatedAt" to Instant.now().toString(),
                "problems" to List(problemNumber) {
                    // PullRequestProblem
                    mapOf(
                        "file" to "mockFile",
                        "startLine" to 1,
                        "fatal" to false,
                        "message" to "mockMessage"
                    )
                }
            )
            post(
                "http://localhost:$gameServerPort/internal-api/add-problems", defaultJsonMapper.toJson(payload),
                mapOf("Content-Type" to "application/json", INTERNAL_API_SECRET_HEADER_NAME to "dummy")
            ).assert2XXStatusCode()
        }
        Thread.sleep(100)

        sendWebhookFromJsonObject("pull_request", createMockPullRequestEvent(playerId, challengeSpec, prOpenEvent.pullRequest.number.toInt(), PullRequestEventAction.CLOSED))
    }

    protected fun sendWebhookFromText(event: String, json: String) {
        post(
            "http://localhost:$gameServerPort/github_webhook", json,
            mapOf("Content-Type" to "application/json", "X-GitHub-Event" to event)
        ).assert2XXStatusCode()
    }

    protected fun sendWebhookFromResource(event: String, resource: String, mutator: (String) -> String = { it }) {
        val json = mutator(javaClass.getResourceAsFile(resource).readText())
        sendWebhookFromText(event, json)
    }

    protected fun sendWebhookFromJsonObject(event: String, payload: Any) {
        val json = defaultJsonMapper.toJson(payload)
        post(
            "http://localhost:$gameServerPort/github_webhook", json,
            mapOf("Content-Type" to "application/json", "X-GitHub-Event" to event)
        ).assert2XXStatusCode()
    }

    protected fun sendProblemsFromResource(resource: String) {
        val json = javaClass.getResourceAsFile(resource).readText()
        post(
            "http://localhost:$gameServerPort/internal-api/add-problems", json,
            mapOf("Content-Type" to "application/json", INTERNAL_API_SECRET_HEADER_NAME to "dummy")
        ).assert2XXStatusCode()
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T> sendHttpRequest(
        uri: String,
        headers: Map<String, String> = emptyMap(),
        responseType: Class<T> = String::class.java as Class<T>,
        requestConfiguration: HttpRequest.Builder.() -> Unit,
    ): HttpResponse<T> {
        val request = HttpRequest.newBuilder()
            .apply {
                headers.forEach { (key, value) -> header(key, value) }
            }
            .uri(URI.create(if (uri.startsWith("/")) "http://localhost:$gameServerPort$uri" else uri))
            .apply {
                requestConfiguration(this)
            }
            .build()
        return when (responseType) {
            String::class.java -> {
                httpClient.send(request, HttpResponse.BodyHandlers.ofString()) as HttpResponse<T>
            }
            InputStream::class.java -> {
                httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream()) as HttpResponse<T>
            }
            ByteArray::class.java -> {
                httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray()) as HttpResponse<T>
            }
            else -> {
                throw UnsupportedOperationException()
            }
        }
    }

    protected fun get(uri: String, headers: Map<String, String> = emptyMap()): HttpResponse<String> {
        return sendHttpRequest(uri, headers) { GET() }
    }

    protected fun <T> HttpResponse<String>.bodyAs(klass: Class<T>): T = defaultJsonMapper.fromJson(body(), klass)

    protected fun delete(uri: String, headers: Map<String, String> = emptyMap()): HttpResponse<String> {
        return sendHttpRequest(uri, headers) { DELETE() }
    }

    protected fun options(uri: String, headers: Map<String, String> = emptyMap()): HttpResponse<String> {
        return sendHttpRequest(uri, headers) {
            method("OPTIONS", HttpRequest.BodyPublishers.ofString(""))
        }
    }

    protected fun post(uri: String, body: String, headers: Map<String, String> = emptyMap()): HttpResponse<String> {
        return sendHttpRequest(uri, headers) {
            POST(HttpRequest.BodyPublishers.ofString(body))
        }
    }
}

fun <T> HttpResponse<T>.assert2XXStatusCode(): HttpResponse<T> {
    assertTrue(statusCode() in 200..299) {
        "Received ${statusCode()} status code: ${body()}"
    }
    return this
}

fun HttpResponse<String>.assertStatusCode(expectedStatusCode: Int): HttpResponse<String> {
    assertEquals(expectedStatusCode, statusCode())
    return this
}

@Testcontainers
@ExtendWith(JUnit5VncRecorder::class)
abstract class AbstractBrowserTest : AbstractByteLegendIntegrationTest() {
    val browserWebDriverContainers: MutableList<BrowserWebDriverContainer<Nothing>> = ArrayList()
    open val webDriver: WebDriver
        get() = browserWebDriverContainers[0].webDriver

    /**
     * This is the temp directory which won't be cleaned up after test,
     * so that we can see the recording videos.
     */
    val buildTmpDir: File
        get() = File(requireNotNull(System.getProperty("build.tmp.dir")), javaClass.simpleName)

    @BeforeEach
    fun setUpAbstractBrowserTest() {
        exposeHostPorts(gameServerPort)
    }

    @AfterEach
    fun tearDownAbstractBrowserTest() {
        browserWebDriverContainers.forEach { it.safeStop() }
    }

    protected fun createBrowser(
        browserProvider: Supplier<Capabilities>,
        recordingDir: File,
        recordingMode: VncRecordingMode = VncRecordingMode.RECORD_FAILING,
        logLevel: Level = Level.ALL,
    ): BrowserWebDriverContainer<Nothing> = BrowserWebDriverContainer<Nothing>().apply {
        val browserOptions = browserProvider.get()
        if (browserOptions is ChromeOptions) {
            browserOptions.apply {
                setCapability(
                    CapabilityType.LOGGING_PREFS,
                    LoggingPreferences().apply {
                        enable(LogType.BROWSER, logLevel)
                    }
                )
            }
        }
        withCapabilities(browserOptions)
        withRecordingMode(recordingMode, recordingDir.apply { mkdirs() }, VncRecordingContainer.VncRecordingFormat.MP4)
        withRecordingFileFactory(DefaultRecordingFileFactory())
    }
}

fun WebDriver.startGame(port: Int) {
    get("http://host.testcontainers.internal:$port")
    waitUntil(10000) {
        findElements(By.id("background-canvas-layer")).isNotEmpty() &&
            findElements(By.id("objects-canvas-layer")).isNotEmpty() &&
            fadeInLayerDisappeared()
    }
}

fun WebDriver.fadeInLayerDisappeared() = findElements(By.className("fade-in-layer")).isEmpty()
