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
package com.bytelegend.app.testfixtures

import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.RGBA
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Point
import org.openqa.selenium.WebDriver
import java.awt.Color
import java.nio.file.Paths
import java.util.Base64

fun Color.toRGBA() = RGBA(red, green, blue, alpha)

fun Class<*>.getResourceAsFile(name: String) = Paths.get(getResource(name).toURI()).toFile()

fun WebDriver.waitUntil(timeoutMs: Int = 5000, predicate: WebDriver.() -> Boolean) {
    com.bytelegend.app.testfixtures.waitUntil(timeoutMs) { predicate() }
}
fun WebDriver.waitUntil(timeoutMs: Int = 5000, by: By) {
    com.bytelegend.app.testfixtures.waitUntil(timeoutMs) { findElements(by).isNotEmpty() }
}

fun <T> waitUntilFound(timeoutMs: Int = 5000, predicate: () -> T): T {
    var ret: T? = null
    waitUntil(timeoutMs) {
        ret = predicate()
        true
    }
    return ret!!
}

fun waitUntilSucceed(timeoutMs: Int = 5000, action: () -> Unit) {
    waitUntil(timeoutMs) {
        action()
        true
    }
}

fun waitUntil(timeoutMs: Int = 5000, predicate: () -> Boolean) {
    val start = System.currentTimeMillis()

    var exception: Throwable? = null
    while (System.currentTimeMillis() - start < timeoutMs) {
        try {
            if (predicate()) {
                return
            }
        } catch (e: Throwable) {
            exception = e
        }
        Thread.sleep(100)
    }
    throw IllegalStateException("Timeout after $timeoutMs ms waiting for condition to be true!", exception)
}

fun WebDriver.clearLocalStorage() {
    (this as JavascriptExecutor).executeScript("window.localStorage.clear();")
}

@Suppress("UNCHECKED_CAST")
fun <T> WebDriver.executeJavascript(script: String): T {
    return (this as JavascriptExecutor).executeScript(script) as T
}

fun WebDriver.getCanvasImageData(canvasId: String): ByteArray {
    val base64: String = executeJavascript("return document.getElementById('$canvasId').toDataURL('image/png').substring(22)")
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
