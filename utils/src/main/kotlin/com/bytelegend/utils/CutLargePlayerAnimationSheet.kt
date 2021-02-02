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
