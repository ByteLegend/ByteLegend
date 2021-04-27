package com.bytelegend.app.client.api

import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.GameObject

interface Character : GameObject, CoordinateMutable {
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

    fun moveTo(destination: GridCoordinate, callback: UnitFunction? = null)
    fun moveAlong(movePath: List<GridCoordinate>, callback: UnitFunction? = null)
    fun searchPath(destination: GridCoordinate): List<GridCoordinate>
}

interface CoordinateMutable : CoordinateAware {
    override var gridCoordinate: GridCoordinate
    override var pixelCoordinate: PixelCoordinate
}

interface CoordinateAware {
    val gridCoordinate: GridCoordinate
    val pixelCoordinate: PixelCoordinate
}
