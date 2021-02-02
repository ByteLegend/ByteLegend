package com.bytelegend.app.client.api

import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.GameObject


interface Character : GameObject, GridCoordinateAware, MutableLocation {
    /**
     * 1. player-{playerId}, e.g. player-gh#ByteLegendBot
     * 2. npc-{npcId}, e.g. npc-JavaIslandNewbieVillageOldMan
     */
    override val id: String

    /**
     * The direction of the character, only UP/DOWN/LEFT/RIGHT.
     * Note this means "how we paint the character", not moving direction.
     */
    var direction: Direction

    var movePath: List<GridCoordinate>
}

interface GridCoordinateAware {
    val gridCoordinate: GridCoordinate
}

/**
 * The coordinate of location in map.
 */
interface MutableLocation {
    var pixelCoordinate: PixelCoordinate
}

