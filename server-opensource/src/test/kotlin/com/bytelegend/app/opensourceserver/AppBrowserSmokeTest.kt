package com.bytelegend.app.opensourceserver

import com.bytelegend.app.servershared.mock.mockPlayer
import com.bytelegend.app.testfixtures.AbstractBrowserTest
import com.bytelegend.app.testfixtures.startGame
import com.bytelegend.app.testfixtures.waitUntil
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeOptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [GameApp::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppBrowserSmokeTest : AbstractBrowserTest() {
    @Autowired
    lateinit var environment: Environment
    override val gameServerPort: Int by lazy {
        environment.getProperty("local.server.port")!!.toInt()
    }

    @BeforeEach
    fun appBrowserSmokeTestBeforeEach() {
        browserWebDriverContainers.add(createBrowser({ ChromeOptions() }, buildTmpDir))
    }

    @Timeout(120)
    @Test
    fun `anonymous access and login`() {
        browserWebDriverContainers[0].start()
        webDriver.startGame(gameServerPort)

        val pageSource = webDriver.pageSource
        assertThat(pageSource, containsString("Helfen Sie uns, die Übersetzungsqualität zu verbessern"))
        assertThat(pageSource, containsString("Ayúdanos a mejorar la calidad de la traducción"))
        assertThat(pageSource, containsString("번역 품질을 개선 할 수 있도록 도와주세요"))
        assertThat(pageSource, containsString("Помогите нам улучшить качество перевода"))
        assertThat(pageSource, containsString("翻訳品質の向上にご協力ください"))

        Thread.sleep(1000) // wait for fade in
        webDriver.findElement(By.id("login-link")).click()

        webDriver.waitUntil(10000) {
            findElements(By.className("avatar-img")).run {
                isNotEmpty() && get(0).getAttribute("src") == mockPlayer.avatarUrl
            }
        }

        webDriver.findElement(By.className("avatar-img")).click()
        for (it in webDriver.findElements(By.className("dropdown-item"))) {
            if (it.text == "Sign out") {
                it.click()
                break
            }
        }
        webDriver.waitUntil(10000) {
            webDriver.findElements(By.id("login-link")).isNotEmpty()
        }
    }
}
