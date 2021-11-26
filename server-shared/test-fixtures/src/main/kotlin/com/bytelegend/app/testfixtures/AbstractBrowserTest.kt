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

import com.bytelegend.app.servershared.DefaultJsonMapper
import com.bytelegend.app.servershared.codechecker.INTERNAL_API_SECRET_HEADER_NAME
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
import java.util.function.Supplier
import java.util.logging.Level

fun BrowserWebDriverContainer<Nothing>.safeStop() {
    try {
        stop()
    } catch (e: Throwable) {
        e.printStackTrace()
    }
}

abstract class AbstractByteLegendIntegrationTest {
    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
    abstract val gameServerPort: Int
    private val defaultJsonMapper = DefaultJsonMapper(true)
    private val httpClient by lazy {
        HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build()
    }

    protected fun sendWebhookFromResource(event: String, resource: String, mutator: (String) -> String = { it }) {
        val json = mutator(javaClass.getResourceAsFile(resource).readText())
        post(
            "http://localhost:$gameServerPort/github_webhook", json,
            mapOf("Content-Type" to "application/json", "X-GitHub-Event" to event)
        ).assert2XXStatusCode()
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
        if (responseType == String::class.java) {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString()) as HttpResponse<T>
        } else if (responseType == InputStream::class.java) {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream()) as HttpResponse<T>
        } else if (responseType == ByteArray::class.java) {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray()) as HttpResponse<T>
        } else {
            throw UnsupportedOperationException()
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
