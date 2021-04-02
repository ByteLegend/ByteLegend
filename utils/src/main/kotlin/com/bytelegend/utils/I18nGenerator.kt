package com.bytelegend.utils

import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.i18n.LocalizedText
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.houbb.opencc4j.util.ZhConverterUtil
import java.io.File

/**
 * Convert i18n YAMLs to JSONs, with extra work:
 *
 * 1. Automatically translate zh-hans to zh-hant.
 * 2. Merge all data to i18n-all.json for backend.
 *
 * Arguments:
 * 1. Input `game-data` directory
 * 2. Output i18n directory
 * 3. Output i18n-all.json file
 */
fun main(args: Array<String>) {
    generate(File(args[0]), File(args[1]), File(args[2]))
}

fun generate(gameDataDir: File, outputI18nDir: File, outputAllJson: File) {
    val mapIdToData: MutableMap<String, List<LocalizedText>> = gameDataDir.listFiles()
        .filter { it.isDirectory }
        .map { it.name to it.resolve("i18n.yml").toLocalizedTexts() }
        .onEach { generate(it.second, outputI18nDir.resolve(it.first)) }
        .toMap()
        .toMutableMap()
        .apply {
            val common = gameDataDir.resolve("i18n-common.yml").toLocalizedTexts()
            generate(common, outputI18nDir.resolve("common"))
            put("common", common)
        }

    generateAllJson(mapIdToData, outputAllJson)
}

fun generateAllJson(mapIdToData: Map<String, List<LocalizedText>>, outputAllJson: File) {
    val idToTextAllMap: MutableMap<String, LocalizedText> = mutableMapOf()
    mapIdToData.entries.flatMap { it.value }.forEach {
        require(idToTextAllMap[it.id] == null) { "Duplicate entry: ${it.id}" }
        idToTextAllMap[it.id] = it
    }
    outputAllJson.writeText(objectMapper.writeValueAsString(idToTextAllMap))
}

fun generate(data: List<LocalizedText>, outputDir: File) {
    outputDir.mkdirs()
    Locale.values().forEach { locale ->
        val outputJson = outputDir.resolve("${locale.toLowerCase()}.json")
        val outputData = data.map { it.id to it.getText(locale) }.toMap()

        outputJson.writeText(objectMapper.writeValueAsString(outputData))
    }
}

fun File.toLocalizedTexts(): List<LocalizedText> {
    val data = YAML_PARSER.readValue(this, object : TypeReference<List<Map<String, String>>>() {})
    return data.map {
        LocalizedText(
            it.getValue("id"),
            it.entries.filter { it.key != "id" }.map { Locale.of(it.key) to it.value }.toMap().autoConvertHansHant()
        )
    }
}

fun Map<Locale, String>.autoConvertHansHant(): Map<Locale, String> {
    return when {
        containsKey(Locale.ZH_HANS) && !containsKey(Locale.ZH_HANT) -> plus(Locale.ZH_HANT to ZhConverterUtil.toTraditional(get(Locale.ZH_HANS)))
        containsKey(Locale.ZH_HANT) && !containsKey(Locale.ZH_HANS) -> plus(Locale.ZH_HANS to ZhConverterUtil.toSimple(get(Locale.ZH_HANT)))
        else -> this
    }
}

val YAML_FACTORY = YAMLFactory()
val YAML_PARSER = ObjectMapper(YAML_FACTORY).registerModule(KotlinModule())
