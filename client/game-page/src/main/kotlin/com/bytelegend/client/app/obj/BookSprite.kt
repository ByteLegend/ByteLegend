package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.AnimationFrame
import com.bytelegend.app.client.api.FramePlayingAnimation
import com.bytelegend.app.client.api.GameScene

class BookSprite(
    override val id: String,
    override val gameScene: GameScene
) : DynamicSprite(
    id,
    gameScene,
    gameScene.objects.getById("Book")
) {
    override fun onClick() {
        play(
            FramePlayingAnimation(
                // Book open animation
                listOf(AnimationFrame(1, 500)),
                false
            )
        )
    }
}
