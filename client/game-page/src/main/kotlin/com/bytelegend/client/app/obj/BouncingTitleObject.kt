package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.ui.mission.BouncingTitle
import com.bytelegend.client.utils.jsObjectBackedSetOf
import react.RBuilder

class BouncingTitleObject(
    override val id: String,
    private val titleText: String,
    private val color: String,
    private val backgroundColor: String,
    private val pixelCoordinate: PixelCoordinate,
    private val onClickFunction: UnitFunction?,
    private val gameScene: GameScene
) : GameObject, HasBouncingTitle {
    override val layer: Int = 0
    override val roles: Set<String> = jsObjectBackedSetOf(GameObjectRole.HasBouncingTitle.toString())
    override fun renderBouncingTitle(builder: RBuilder) {
        builder.child(BouncingTitle::class) {
            attrs.pixelCoordinate = pixelCoordinate
            attrs.onClickFunction = onClickFunction
            attrs.title = titleText
            attrs.gameScene = gameScene
            attrs.backgroundColor = backgroundColor
            attrs.color = color
        }
    }
}
