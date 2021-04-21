package com.bytelegend.app.servershared

import com.bytelegend.app.shared.GameInitData
import com.bytelegend.app.shared.entities.Player
import com.bytelegend.app.shared.enums.ServerLocation
import com.bytelegend.app.shared.i18n.Locale

abstract class AbstractIndexPageRenderer(
    private val rrbdResourceProvider: AbstractRRBDResourceProvider,
    private val jsonMapper: JsonMapper
) {
    private val indexHtml = javaClass.getResource("/index.html").readText()
    protected fun renderIndexHtml(
        initMapId: String,
        onlinePlayerCount: Int,
        player: Player,
        locale: Locale,
        RRBD: String,
        serverLocation: ServerLocation
    ): String {
        val enjoyProgrammingText = rrbdResourceProvider.getI18nText("EnjoyProgramming", locale)
        if (player.locale == null) {
            player.locale = locale.toString()
        }
        val model = mutableMapOf(
            "{RRBD}" to RRBD,
            "{LANG}" to locale.language.code,
            "{TITLE}" to enjoyProgrammingText,
            "{gameInitData}" to jsonMapper.toUglyJson(
                GameInitData(
                    initMapId,
                    onlinePlayerCount,
                    serverLocation,
                    RRBD,
                    enjoyProgrammingText,
                    player,
                    rrbdResourceProvider.maps,
                    emptyList()
                )
            )
        )

        return model.render(indexHtml)
    }

    private fun Map<String, String>.render(template: String): String {
        val sb = StringBuilder(template)
        forEach { (key, value) ->
            var index = sb.indexOf(key)
            while (index != -1) {
                sb.replace(index, index + key.length, value)
                index = sb.indexOf(key)
            }
        }
        return sb.toString()
    }
}
