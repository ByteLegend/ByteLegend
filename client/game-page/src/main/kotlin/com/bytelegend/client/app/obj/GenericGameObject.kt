package com.bytelegend.client.app.obj

import com.bytelegend.app.shared.INVISIBLE_OBJECT_LAYER
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole

class GenericGameObject(
    override val id:String,
    override val roles: Set<GameObjectRole> = emptySet()
): GameObject {
    override val layer: Int = INVISIBLE_OBJECT_LAYER
}