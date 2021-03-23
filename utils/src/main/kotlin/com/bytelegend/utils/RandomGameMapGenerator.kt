//package com.bytelegend.utils
//
//import com.bytelegend.app.shared.RawGameMap
//import com.bytelegend.app.shared.GridSize
//import com.bytelegend.app.shared.PixelBlock
//import com.bytelegend.app.shared.PixelCoordinate
//import com.bytelegend.app.shared.PixelSize
//import com.bytelegend.app.shared.StaticImageTileData
//import com.fasterxml.jackson.annotation.com.bytelegend.app.shared.annotations.JsonIgnore
//import com.fasterxml.jackson.databind.ObjectMapper
//import java.io.File
//import javax.imageio.ImageIO
//import kotlin.random.Random
//
//
//fun main() {
//    generateGameMapJson(
//        "1",
//        GridSize(100, 100),
//        PixelSize(32, 32),
//        File("server/src/main/resources/static/img/tilesheet.png"),
//        PixelSize(48, 48)
//    )
//}
//
//fun generateGameMapJson(
//    id: String,
//    size: GridSize,
//    tileSize: PixelSize,
//    imgPath: File,
//    imgTileSize: PixelSize
//) {
//    val img = ImageIO.read(imgPath)
//    val gridSize = GridSize(img.width / imgTileSize.width, img.height / imgTileSize.height)
//
//    val data = mutableListOf<Array<StaticImageTileData>>()
//
//    for (y in 0 until size.height) {
//        val row = mutableListOf<StaticImageTileData>()
//        for (x in 0 until size.width) {
//            val randomX = Random.nextInt(gridSize.width)
//            val randomY = Random.nextInt(gridSize.height)
//            val pixelBlock = PixelBlock(randomX * tileSize.width, randomY * tileSize.height, imgTileSize.width, imgTileSize.height)
//            row.add(StaticImageTileData("tilesheet", pixelBlock))
//        }
//        data.add(row.toTypedArray())
//    }
//
//    val gameMap = RawGameMap().apply {
//        this.id = id
//        this.size = size
//        this.tileSize = tileSize
////        this.data = data.toTypedArray()
//    }
//
//    val objectWriter = ObjectMapper()
//        .addMixIn(PixelBlock::class.java, PixelBlockMixIn::class.java)
//        .addMixIn(RawGameMap::class.java, GameMapMixIn::class.java)
//        .addMixIn(StaticImageTileData::class.java, TileDataMixIn::class.java)
//        .writerWithDefaultPrettyPrinter()
//
//    File("server/src/main/resources/maps/${id}.json").writeText(objectWriter.writeValueAsString(gameMap))
//}
//
//abstract class PixelBlockMixIn {
//    @com.bytelegend.app.shared.annotations.JsonIgnore
//    abstract fun getX(): Int
//
//    @com.bytelegend.app.shared.annotations.JsonIgnore
//    abstract fun getY(): Int
//
//    @com.bytelegend.app.shared.annotations.JsonIgnore
//    abstract fun getWidth(): Int
//
//    @com.bytelegend.app.shared.annotations.JsonIgnore
//    abstract fun getHeight(): Int
//}
//
//abstract class GameMapMixIn {
//    @com.bytelegend.app.shared.annotations.JsonIgnore
//    abstract fun getPixelSize(): PixelSize
//}
//
//abstract class TileDataMixIn {
//    @com.bytelegend.app.shared.annotations.JsonIgnore
//    abstract fun getFrames(): List<PixelBlock>
//
//    @com.bytelegend.app.shared.annotations.JsonIgnore
//    abstract fun getFps(): Int
//}