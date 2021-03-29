package com.bytelegend.app.servershared

import com.bytelegend.app.shared.GameMapDefinition
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.i18n.LocalizedText
import com.fasterxml.jackson.core.type.TypeReference
import java.io.File

abstract class AbstractRRBDResourceProvider(
    private val localRRBD: String,
    serializer: JsonMapper
) {
    private val localizedText: Map<String, LocalizedText> by lazy {
        val i18nAllJson = File(localRRBD).resolve("i18n/all.json").readText()
        serializer.fromJson(i18nAllJson, object : TypeReference<Map<String, LocalizedText>>() {})
    }

    val maps: List<GameMapDefinition> by lazy {
        val mapHierarchyYml = File(localRRBD).resolve("map/hierarchy.yml").readText()
        serializer.fromYaml(mapHierarchyYml, object : TypeReference<List<GameMapDefinition>>() {})
    }
    val idToMaps: Map<String, GameMapDefinition> by lazy {
        mutableMapOf<String, GameMapDefinition>().apply {
            putInto(this, maps)
        }.toMap()
    }

    private fun putInto(result: MutableMap<String, GameMapDefinition>, maps: List<GameMapDefinition>) {
        maps.forEach {
            result[it.id] = it
            putInto(result, it.children)
        }
    }

    fun getI18nText(id: String, locale: Locale) = localizedText.getValue(id).getText(locale)
}
