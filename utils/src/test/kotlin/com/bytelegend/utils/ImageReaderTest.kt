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
