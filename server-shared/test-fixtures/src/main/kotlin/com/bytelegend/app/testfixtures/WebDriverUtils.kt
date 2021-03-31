package com.bytelegend.app.testfixtures

import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.RGBA
import org.junit.jupiter.api.Assertions
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Point
import org.openqa.selenium.WebDriver
import org.openqa.selenium.logging.LogType
import java.awt.Color
import java.nio.file.Paths
import java.util.Base64
import java.util.logging.Level

fun Color.toRGBA() = RGBA(red, green, blue, alpha)

fun Class<*>.getResourceAsFile(name: String) = Paths.get(getResource(name).toURI()).toFile()

fun WebDriver.waitUntil(timeoutMs: Int = 5000, predicate: WebDriver.() -> Boolean) {
    com.bytelegend.app.testfixtures.waitUntil(timeoutMs) { predicate() }
}

fun waitUntil(timeoutMs: Int = 5000, predicate: () -> Boolean) {
    val start = System.currentTimeMillis()

    while (System.currentTimeMillis() - start < timeoutMs) {
        if (predicate()) {
            return
        }
        Thread.sleep(100)
    }
    throw IllegalStateException("Timeout after $timeoutMs ms waiting for condition to be true!")
}

fun WebDriver.assertNoErrorInConsoleLog() {
    manage().logs().get(LogType.BROWSER).all.forEach { log ->
        println("${log.level} ${log.message}")
        if (listOf("/favicon.ico", "/404").none { log.message.contains(it) }) {
            Assertions.assertNotEquals(Level.SEVERE, log.level)
        }
    }
}

fun WebDriver.clearLocalStorage() {
    (this as JavascriptExecutor).executeScript("window.localStorage.clear();")
}

fun WebDriver.getCanvasImageData(canvasId: String): ByteArray {
    val base64 = (this as JavascriptExecutor).executeScript("return document.getElementById('$canvasId').toDataURL('image/png').substring(22)") as String
    return Base64.getDecoder().decode(base64)
}

fun WebDriver.getElementLocation(id: String): PixelCoordinate {
    return findElement(By.id(id)).location.toPixelCoordinate()
}

private fun Point.toPixelCoordinate() = PixelCoordinate(x, y)

fun WebDriver.getElementSize(id: String): PixelSize {
    return findElement(By.id(id)).size.toPixelSize()
}

private fun Dimension.toPixelSize(): PixelSize = PixelSize(width, height)
