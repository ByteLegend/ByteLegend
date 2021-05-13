@file:Suppress("HttpUrlsUsage")

package com.bytelegend.app.testfixtures

import com.bytelegend.app.browsertest.JUnit5VncRecorder
import org.junit.jupiter.api.AfterEach
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
import org.testcontainers.junit.jupiter.Testcontainers
import java.io.File
import java.util.function.Supplier
import java.util.logging.Level

fun BrowserWebDriverContainer<Nothing>.safeStop() {
    try {
        stop()
    } catch (e: Throwable) {
        e.printStackTrace()
    }
}

@Testcontainers
@ExtendWith(JUnit5VncRecorder::class)
abstract class AbstractBrowserTest {
    val browserWebDriverContainers: MutableList<BrowserWebDriverContainer<Nothing>> = ArrayList()
    open val webDriver: WebDriver
        get() = browserWebDriverContainers[0].webDriver

    /**
     * This is the temp directory which won't be cleaned up after test,
     * so that we can see the recording videos.
     */
    val buildTmpDir: File
        get() = File(System.getProperty("build.tmp.dir"), javaClass.simpleName)

    abstract val gameServerPort: Int

    @BeforeEach
    fun setUp() {
        exposeHostPorts(gameServerPort)
    }

    @AfterEach
    fun tearDown() {
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
        withRecordingMode(recordingMode, recordingDir.apply { mkdirs() })
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
