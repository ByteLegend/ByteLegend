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

import com.bytelegend.app.shared.ANIMATION_SET_HORIZONTAL_NUMBER
import com.bytelegend.app.shared.ANIMATION_TILE_HEIGHT
import com.bytelegend.app.shared.ANIMATION_TILE_WIDTH
import com.bytelegend.app.shared.PLAYER_ANIMATION_SET_VERTICAL_NUMBER
import com.bytelegend.app.shared.math.capacityDivide
import com.bytelegend.app.shared.playerAnimationSetId
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.ceil

// 1. Merge (32x32) animation set to a large file (32x8 x 32x8)
//    See ANIMATION_SET_HORIZONTAL_NUMBER and ANIMATION_SET_VERTICAL_NUMBER
// 2. Merge NPC animations in every map to a animation set.
// resources/raw/player-animations -> RRBD/img/player
//                                                  /animation-set-X.png
fun main(args: Array<String>) {
    PlayerAnimationSetGenerator(File(args[0]), File(args[1])).generate()
}

class PlayerAnimationSetGenerator(
    val inputDir: File,
    val outputDir: File
) {
    init {
        outputDir.mkdirs()
    }

    fun generate() {
        val idToPng =
            inputDir.listFiles().filter { it.name.endsWith(".png") }.associateBy { it.name.replace(".png", "").toInt() }

        merge(idToPng, ANIMATION_SET_HORIZONTAL_NUMBER, PLAYER_ANIMATION_SET_VERTICAL_NUMBER)
    }

    private fun merge(
        idToPngFile: Map<Int, File>,
        horizontalOutputImageCharacterNumber: Int,
        verticalOutputImageCharacterNumber: Int,
    ) {
        val maxId = idToPngFile.keys.maxOrNull()!!

        // maxId = 63 -> 1
        // maxId = 64 -> 2
        val outputFileNumber = ceil((maxId + 1.0) / (horizontalOutputImageCharacterNumber * verticalOutputImageCharacterNumber)).toInt()

        for (i in 0 until outputFileNumber) {
            val outputFile = outputDir.resolve(playerAnimationSetId(i) + ".png")

            val verticalNumber = if (i == outputFileNumber - 1)
                capacityDivide((maxId + 1) % (horizontalOutputImageCharacterNumber * verticalOutputImageCharacterNumber), horizontalOutputImageCharacterNumber)
            else
                verticalOutputImageCharacterNumber

            // 0 -> 8
            // 1 -> 1
            // 2 -> 2
            // 7 -> 7
            val horizontalNumber = if (i == outputFileNumber - 1 && verticalNumber == 1 && ((maxId + 1) % horizontalOutputImageCharacterNumber != 0))
                (maxId + 1) % horizontalOutputImageCharacterNumber
            else
                horizontalOutputImageCharacterNumber

            val outputImage = BufferedImage(
                horizontalNumber * ANIMATION_TILE_WIDTH,
                verticalNumber * ANIMATION_TILE_HEIGHT,
                BufferedImage.TYPE_INT_ARGB
            )
            val graphics = outputImage.graphics

            for (x in 0 until horizontalOutputImageCharacterNumber) {
                for (y in 0 until verticalOutputImageCharacterNumber) {
                    val imageId = (i * horizontalOutputImageCharacterNumber * verticalOutputImageCharacterNumber) +
                        y * horizontalOutputImageCharacterNumber + x
                    if (idToPngFile.containsKey(imageId)) {
                        val inputImage = ImageIO.read(idToPngFile.getValue(imageId))
                        graphics.drawImage(
                            inputImage,
                            x * ANIMATION_TILE_WIDTH,
                            y * ANIMATION_TILE_HEIGHT,
                            ANIMATION_TILE_WIDTH,
                            ANIMATION_TILE_HEIGHT,
                            null
                        )
                    }
                }
            }

            graphics.dispose()
            ImageIO.write(outputImage, "PNG", outputFile)
        }
    }
}
