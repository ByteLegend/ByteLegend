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

import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.PixelSize
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileFilter
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    val inputDir = File(args[0])
    val outputDir = File(args[1])

    inputDir.listFiles(FileFilter { it.name.endsWith(".png") }).forEach {
        cutTo(it, outputDir)
    }
}

fun cutTo(largeAnimationSheetPng: File, outputDir: File) {
    val DEFAULT_TILE_WIDTH = 32
    val DEFAULT_TILE_HEIGHT = 32
    val DEFAULT_SHEET_WIDTH = 32 * 3
    val DEFAULT_SHEET_HEIGHT = 32 * 4

    val imageReader = ImageReader()
    val inputImage = imageReader.getImage(largeAnimationSheetPng)
    val pixelSize = PixelSize(inputImage.width, inputImage.height)
    val gridSize = pixelSize / PixelSize(DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT)

    require(gridSize.width % 3 == 0 && gridSize.height % 4 == 0) {
        "invalid size of $largeAnimationSheetPng: $gridSize "
    }

    for (x in 0 until gridSize.width / 3) {
        for (y in 0 until gridSize.height / 4) {
            val outputImageFile = outputDir.resolve(largeAnimationSheetPng.name.replace(".png", "-$x-$y.png"))

            if (!imageReader.isFullyTransparent(
                    ImageBlock(
                            largeAnimationSheetPng,
                            PixelBlock(x * DEFAULT_SHEET_WIDTH, y * DEFAULT_SHEET_HEIGHT, DEFAULT_SHEET_WIDTH, DEFAULT_SHEET_HEIGHT)
                        )
                )
            ) {
                val outputImage = BufferedImage(DEFAULT_SHEET_WIDTH, DEFAULT_SHEET_HEIGHT, BufferedImage.TYPE_INT_ARGB)
                outputImage.graphics.drawImage(
                    inputImage,
                    0, 0, DEFAULT_SHEET_WIDTH, DEFAULT_SHEET_HEIGHT,
                    x * DEFAULT_SHEET_WIDTH, y * DEFAULT_SHEET_HEIGHT, (x + 1) * DEFAULT_SHEET_WIDTH, (y + 1) * DEFAULT_SHEET_HEIGHT,
                    null
                )

                ImageIO.write(outputImage, "PNG", outputImageFile)
            }
        }
    }
}
