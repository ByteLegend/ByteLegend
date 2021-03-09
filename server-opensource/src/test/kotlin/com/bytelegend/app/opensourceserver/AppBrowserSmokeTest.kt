package com.bytelegend.app.opensourceserver

import com.bytelegend.app.browsertest.JUnit5VncRecorder
import com.bytelegend.app.servershared.mock.mockPlayer
import com.bytelegend.app.testfixtures.assertNoErrorInConsoleLog
import com.bytelegend.app.testfixtures.waitUntil
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.logging.LogType
import org.openqa.selenium.logging.LoggingPreferences
import org.openqa.selenium.remote.CapabilityType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.testcontainers.Testcontainers.exposeHostPorts
import org.testcontainers.containers.BrowserWebDriverContainer
import org.testcontainers.containers.DefaultRecordingFileFactory
import org.testcontainers.junit.jupiter.Testcontainers
import java.io.File
import java.util.logging.Level

@Testcontainers
@ExtendWith(value = [JUnit5VncRecorder::class, SpringExtension::class])
@SpringBootTest(classes = [GameApp::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppBrowserSmokeTest {
    @Autowired
    lateinit var environment: Environment
    val browserContainer: BrowserWebDriverContainer<Nothing> = BrowserWebDriverContainer<Nothing>().apply {
        withCapabilities(
            ChromeOptions().apply {
                setCapability(
                    CapabilityType.LOGGING_PREFS,
                    LoggingPreferences().apply {
                        enable(LogType.BROWSER, Level.ALL)
                    }
                )
            }
        )
        withRecordingMode(
            BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL,
            File(System.getProperty("build.tmp.dir"), javaClass.simpleName).apply { mkdirs() }
        )
        withRecordingFileFactory(DefaultRecordingFileFactory())
    }

    private val port: Int
        get() = environment.getProperty("local.server.port")!!.toInt()

    @BeforeEach
    fun setUp() {
        exposeHostPorts(port)
        browserContainer.start()
        browserWebDriverContainers.add(browserContainer)
    }

    @Test
    fun `anonymous access and login`() {
        val webDriver = browserContainer.webDriver
        webDriver.get("http://host.testcontainers.internal:$port")
        webDriver.waitUntil(10000) {
            findElements(By.id("background-canvas-layer")).isNotEmpty() &&
                findElements(By.id("objects-canvas-layer")).isNotEmpty()
        }

        webDriver.assertNoErrorInConsoleLog()

        Thread.sleep(1000) // wait for fade in
        webDriver.findElement(By.id("login-link")).click()

        webDriver.waitUntil(10000) {
            findElements(By.className("avatar-img")).run {
                isNotEmpty() && get(0).getAttribute("src") == mockPlayer.avatarUrl
            }
        }
    }

    companion object {
        @JvmStatic
        val browserWebDriverContainers: MutableList<BrowserWebDriverContainer<Nothing>> = ArrayList()
    }
}
