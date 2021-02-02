package com.bytelegend.app.client.api

import com.bytelegend.app.shared.objects.GameObject
import org.w3c.dom.CanvasRenderingContext2D

interface Sprite : GameObject {
    val gameScene: GameScene

    /**
     * Whether the sprite is inside the canvas or not.
     *
     * Return true if the sprite is completely out of canvas (no overlap) so we don't need to paint it at all.
     */
    fun outOfCanvas(): Boolean = true

    fun draw(canvas: CanvasRenderingContext2D)

//    override fun init() {
//        gameScene.sprites.add(this)
//    }
//
//    override fun close() {
//        gameScene.sprites.remove(id)
//    }
}
