/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bytelegend.app.servershared

import com.bytelegend.app.shared.GameInitData
import com.bytelegend.app.shared.entities.Player
import com.bytelegend.app.shared.i18n.Locale

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
abstract class AbstractIndexPageRenderer(
    private val rrbdResourceProvider: RRBDResourceProvider,
    private val jsonMapper: JsonMapper
) {
    private val indexHtml = javaClass.getResource("/index.html").readText()
    protected fun renderIndexHtml(
        initMapId: String,
        onlinePlayerCount: Int,
        player: Player,
        locale: Locale,
        RRBD: String,
        joinQQGroupSecret: String
    ): String {
        val enjoyProgrammingText = rrbdResourceProvider.getI18nText("EnjoyProgramming", locale)
        if (player.locale == null) {
            player.locale = locale.toString()
        }
        val model = mutableMapOf(
            "{RRBD}" to RRBD,
            "{LANG}" to locale.language.code,
            "{TITLE}" to "字节传说",
            "{gameInitData}" to jsonMapper.toUglyJson(
                GameInitData(
                    initMapId,
                    onlinePlayerCount,
                    RRBD,
                    enjoyProgrammingText,
                    player,
                    rrbdResourceProvider.mapDefinitions,
                    emptyList(),
                    joinQQGroupSecret
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
