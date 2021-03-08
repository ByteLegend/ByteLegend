package com.bytelegend.utils

import com.bytelegend.app.shared.RGBA
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.nio.file.Paths

fun Class<*>.getResourceAsFile(name: String) = Paths.get(getResource(name).toURI()).toFile()

class ImageReaderTest {
    @ParameterizedTest
    @CsvSource(
        value = [
            "/animation-set-0.png, 0, 0, 0, 0, 0, 0",
            "/animation-set-0.png, 32, 0, 0, 0, 0, 0",
            "/animation-set-0.png, 64, 0, 0, 0, 0, 0",
            "/animation-set-0.png, 100, 0, 255, 0, 0, 255"
        ]
    )
    fun `can read png`(file: String, x: Int, y: Int, r: Int, g: Int, b: Int, a: Int) {
        Assertions.assertEquals(RGBA(r, g, b, a), ImageReader().read(javaClass.getResourceAsFile(file))[y][x])
    }
}