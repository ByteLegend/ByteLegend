package com.bytelegend.app.shared

import com.bytelegend.app.shared.entities.Player
import com.bytelegend.app.shared.enums.ServerLocation
import kotlinx.serialization.Serializable

/**
 * Some data rendered to HTML page directly so it can be used
 * BEFORE AJAX resources are loaded. More specifically, everything needed for
 * loading page (before the main game page is rendered.).
 */
@Serializable
data class GameInitData(
    val initMapId: String,
    val onlineCount: Int,
    val serverLocation: ServerLocation,
    val rrbd: String,
    val enjoyProgrammingText: String,
    val player: Player,
    val maps: List<GameMapDefinition>
) {
    fun resolve(path: String) = "$rrbd$path"
}

@Serializable
data class GameMapDefinition(
    val id: String,
    val children: List<GameMapDefinition>,
    /**
     * How many frames in background animation, this value is used to pre-render background.
     */
    val frames: Int
)