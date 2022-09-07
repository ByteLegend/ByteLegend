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

import com.bytelegend.app.shared.GridSize
import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.github.utils.generated.TiledTileset
import com.madgag.gif.fmsware.GifDecoder
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileInputStream
import java.util.regex.Pattern
import javax.imageio.ImageIO

/**
 * Process a single gif or a list of animation frames, and generates
 * tileset PNG and tileset JSON for Tiled.
 *
 * Usage: ./gradlew utils:mergeAnimationSpriteSheet '-DinputFiles=/path/to/single.gif or *.png' -DtilesetName=TeslaCoil -DoutputFrameWidth=32 -DbackgroundColor=0000ff -DgroupType=Animations/DynamicSprites
 *
 * groupType: Animations/DynamicSprites
 * inputFiles: a single GIF file or a list of animation frame PNGs.
 * tilesetName: the name of the tileset.
 * outputFrameWidth: optional. Specify the output width to resize the images.
 * backgroundColor: optional. Some GIFs have non-transparent background. Specify this value to transparentify the GIF.
 */
fun main() {
    val tilesetName = System.getProperty("tilesetName")!!
    val groupType = SpecialMapGroup.valueOf(System.getProperty("groupType")!!)

    val inputImages = determineInputImages().onEach { resetBackgroundColor(it) }
    val opaqueRegion = inputImages.findOpaqueRegion()
    println("Opaque region: $opaqueRegion")

    val outputFrameWidth = System.getProperty("outputFrameWidth")?.toInt() ?: opaqueRegion.width
    val outputFrameHeight = (1.0 * opaqueRegion.height / opaqueRegion.width * outputFrameWidth).toInt()

    val frameGridSize = if (groupType == SpecialMapGroup.Animations)
        GridSize(1, 1)
    else
        GridSize(outputFrameWidth / 32, if (outputFrameHeight % 32 == 0) outputFrameHeight / 32 else (outputFrameHeight / 32 + 1))

    val tilesetSize = if (groupType == SpecialMapGroup.Animations)
        PixelSize(outputFrameWidth * inputImages.size, outputFrameHeight)
    else
        PixelSize(frameGridSize.width * 32 * inputImages.size, frameGridSize.height * 32)

    val outputImage = BufferedImage(
        tilesetSize.width,
        tilesetSize.height,
        BufferedImage.TYPE_INT_ARGB
    )

    println("Output frame size: ${frameGridSize}x$outputFrameHeight $frameGridSize")
    println("Output tileset size: $tilesetSize")
    inputImages.forEachIndexed { index, frame ->
        outputImage.graphics.drawImage(
            frame,
            outputFrameWidth * index, 0, outputFrameWidth * index + outputFrameWidth, outputFrameHeight,
            opaqueRegion.x, opaqueRegion.y, opaqueRegion.x + opaqueRegion.width, opaqueRegion.y + opaqueRegion.height,
            null
        )
    }

    val tilesetDir = File("resources/raw/${groupType.tilesetImgDir}").apply { require(isDirectory) }
    val tilesetJsonDir = File("resources/raw/${groupType.tilesetJsonDir}").apply { require(isDirectory) }

    val animationTiles = mutableListOf<TiledTileset.Tile>()
    for (x in 0 until frameGridSize.width) {
        for (y in 0 until frameGridSize.height) {
            animationTiles.add(TiledTileset.Tile().apply {
                id = frameGridSize.width * inputImages.size * y + x.toLong()
                animation = inputImages.indices.map { TiledTileset.Animation(500, id + frameGridSize.width * it) }
            })
        }
    }

    tilesetJsonDir.resolve("$tilesetName.json").writeText(
        prettyObjectMapper.writeValueAsString(
            TiledTileset().apply {
                columns = frameGridSize.width * inputImages.size.toLong()
                image = "../${groupType.tilesetImgDir}/$tilesetName.png"
                imageheight = tilesetSize.height.toLong()
                imagewidth = tilesetSize.width.toLong()
                margin = 0
                name = tilesetName
                margin = 0
                tilecount = inputImages.size.toLong() * frameGridSize.width * frameGridSize.height
                tiledversion = "1.7.2"
                tilewidth = imagewidth / inputImages.size / frameGridSize.width
                tileheight = imageheight / frameGridSize.height
                objectalignment = "topleft"
                tiles = animationTiles
                type = "tileset"
                version = "1.6"
            }
        )
    )

    ImageIO.write(outputImage, "PNG", tilesetDir.resolve("$tilesetName.png"))
}

private fun resetBackgroundColor(frame: BufferedImage) {
    val backgroundColor = System.getProperty("backgroundColor") ?: return
    require(backgroundColor.matches("[0-9a-fA-F]{6}".toRegex()))
    for (x in 0 until frame.width) {
        for (y in 0 until frame.height) {
            val rgba = frame.getRGBA(x, y)
            val currentColor = String.format("%02x%02x%02x", rgba.r, rgba.g, rgba.b)
            if (currentColor.equals(backgroundColor, true)) {
                frame.setRGB(x, y, (rgba.r shl 16) or (rgba.g shl 8) or rgba.b)
            }
        }
    }
}

private fun determineInputImages(): List<BufferedImage> {
    val inputFiles = System.getProperty("inputFiles")!!
    return if (inputFiles.endsWith(".gif")) {
        val decoder = GifDecoder().apply { read(FileInputStream(inputFiles)) }
        (0 until decoder.frameCount).map { decoder.getFrame(it) }
    } else if (inputFiles.contains("*")) {
        val dir = if (inputFiles.contains("/")) inputFiles.substringBeforeLast("/") else "."
        val name = if (inputFiles.contains("/")) inputFiles.substringAfterLast("/") else inputFiles
        val namePattern = name.split("*").joinToString(".*") { Pattern.quote(it) }.toRegex()
        File(dir).listFiles()
            .filter { it.isFile && it.name.matches(namePattern) }
            .sortedBy { it.name }
            .apply { println("Files to merge: $this") }
            .map { ImageIO.read(it) }
    } else {
        listOf(ImageIO.read(File(inputFiles)))
    }
}

fun List<BufferedImage>.findOpaqueRegion(): PixelBlock {
    map { it.width }.toSet().apply { require(size == 1) }.first()
    map { it.height }.toSet().apply { require(size == 1) }.first()
    val leftTopCorners = map { findLeftTopCorner(it) }
    val rightBottomCorners = map { findRightBottomCorner(it) }
    val leftmostColumn = leftTopCorners.minOf { it.x }
    val topmostRow = leftTopCorners.minOf { it.y }
    val rightmostColumn = rightBottomCorners.maxOf { it.x }
    val bottommostRow = rightBottomCorners.maxOf { it.y }
    return PixelBlock(leftmostColumn, topmostRow, (rightmostColumn - leftmostColumn), (bottommostRow - topmostRow))
}

private fun findLeftTopCorner(frame: BufferedImage): PixelCoordinate {
    val firstOpaqueRow = (0 until frame.height).firstOrNull { y ->
        (0 until frame.width).any { frame.getRGBA(it, y).isOpaque() }
    } ?: throw IllegalStateException("All pixels are transparent?")
    val firstOpaqueColumn = (0 until frame.width).firstOrNull { x ->
        (0 until frame.height).any { frame.getRGBA(x, it).isOpaque() }
    } ?: throw IllegalStateException("All pixels are transparent?")
    return PixelCoordinate(firstOpaqueColumn, firstOpaqueRow)
}

private fun findRightBottomCorner(frame: BufferedImage): PixelCoordinate {
    val lastOpaqueRow = (0 until frame.height).lastOrNull { y ->
        (0 until frame.width).any { frame.getRGBA(it, y).isOpaque() }
    } ?: throw IllegalStateException("All pixels are transparent?")
    val lastOpaqueColumn = (0 until frame.width).lastOrNull { x ->
        (0 until frame.height).any { frame.getRGBA(x, it).isOpaque() }
    } ?: throw IllegalStateException("All pixels are transparent?")
    return PixelCoordinate(lastOpaqueColumn, lastOpaqueRow)
}
