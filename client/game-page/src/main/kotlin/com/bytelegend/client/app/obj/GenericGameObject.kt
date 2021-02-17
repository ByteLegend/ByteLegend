package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.CoordinateAware
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.INVISIBLE_OBJECT_LAYER
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole

open class GenericGameObject(
    override val id: String,
    private val onClickFunction: () -> Unit = {},
    override val roles: Set<GameObjectRole> = emptySet()
) : GameObject {
    override val layer: Int = INVISIBLE_OBJECT_LAYER

    override fun onClick() {
        onClickFunction()
    }
}

class GenericCoordinateAwareGameObject(
    id: String,
    override val gridCoordinate: GridCoordinate,
    override val pixelCoordinate: PixelCoordinate,
    onClickFunction: () -> Unit = {},
    override val roles: Set<GameObjectRole> = setOf(GameObjectRole.CoordinateAware)
) : GenericGameObject(
    id,
    onClickFunction,
    roles
),
    CoordinateAware
