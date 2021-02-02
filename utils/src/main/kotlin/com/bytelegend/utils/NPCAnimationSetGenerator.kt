package com.bytelegend.utils

import com.bytelegend.app.shared.ANIMATION_SET_HORIZONTAL_NUMBER
import com.bytelegend.app.shared.ANIMATION_TILE_HEIGHT
import com.bytelegend.app.shared.ANIMATION_TILE_WIDTH
import com.bytelegend.app.shared.capacityDivide
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.ceil

// Merge NPC animations in every map to a animation set.
// The NPC animation pngs are named as:
//  1-JavaIslandVillageOldMan.png
// Where 1 is location in animation set, JavaIslandVillageOldMan is the id of character on the map
// resources/raw/map/$mapId -> RRBD/map/$mapId/npc.png
class NPCAnimationSetGenerator(
    private val inputDir: File,
    private val outputImage: File
) {
    fun generate() {
        val idToPng = inputDir.resolve("npc").listFiles().filter { it.name.endsWith(".png") }
            .map { it.name.substringBefore("-").toInt() to it }
            .toMap()

        merge(idToPng, ANIMATION_SET_HORIZONTAL_NUMBER, 99999) { outputImage }
    }
}

fun merge(
    idToPngFile: Map<Int, File>,
    horizontalOutputImageCharacterNumber: Int,
    verticalOutputImageCharacterNumber: Int,
    outputImageProvider: (Int) -> File
) {
    val maxId = idToPngFile.keys.maxOrNull()!!

    // maxId = 63 -> 1
    // maxId = 64 -> 2
    val outputFileNumber = ceil((maxId + 1.0) / (horizontalOutputImageCharacterNumber * verticalOutputImageCharacterNumber)).toInt()

    for (i in 0 until outputFileNumber) {
        val outputFile = outputImageProvider(i)

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
