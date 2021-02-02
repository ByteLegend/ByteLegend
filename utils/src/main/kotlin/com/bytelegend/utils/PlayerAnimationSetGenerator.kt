package com.bytelegend.utils

import com.bytelegend.app.shared.ANIMATION_SET_HORIZONTAL_NUMBER
import com.bytelegend.app.shared.PLAYER_ANIMATION_SET_VERTICAL_NUMBER
import com.bytelegend.app.shared.playerAnimationSetResourceId
import java.io.File

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
        val idToPng = inputDir.listFiles().filter { it.name.endsWith(".png") }
            .map { it.name.replace(".png", "").toInt() to it }
            .toMap()

        merge(idToPng, ANIMATION_SET_HORIZONTAL_NUMBER, PLAYER_ANIMATION_SET_VERTICAL_NUMBER) {
            outputDir.resolve(playerAnimationSetResourceId(it) + ".png")
        }
    }
}
