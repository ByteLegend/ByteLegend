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
 */
fun main(args: Array<String>) {
    generate(File(args[0]), File(args[1]), File(args[2]))
}

fun generate(inputI18nDir: File, outputI18nDir: File, outputAllJson: File) {
    val mapIdToData: Map<String, List<LocalizedText>> = inputI18nDir.walk()
        .filter { it.isFile && it.name.endsWith(".yml") }
        .map { it.name.replace(".yml", "") to it.toLocalizedTexts() }
        .onEach { generate(it.second, outputI18nDir.resolve(it.first)) }
        .toMap()

    generateAllJson(mapIdToData, outputAllJson)
}

fun generateAllJson(mapIdToData: Map<String, List<LocalizedText>>, outputAllJson: File) {
    val idToTextAllMap: MutableMap<String, LocalizedText> = mutableMapOf()
    mapIdToData.entries.flatMap { it.value }.forEach {
        require(idToTextAllMap[it.id] == null) { "Duplicate entry: ${it.id}" }
        idToTextAllMap[it.id] = it
    }
    outputAllJson.writeText(jsonReader.writeValueAsString(idToTextAllMap))
}

fun generate(data: List<LocalizedText>, outputDir: File) {
    outputDir.mkdirs()
    Locale.values().forEach { locale ->
        val outputJson = outputDir.resolve("${locale.toLowerCase()}.json")
        val outputData = data.map { it.id to it.getText(locale) }.toMap()

        outputJson.writeText(jsonReader.writeValueAsString(outputData))
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

val YAML_PARSER = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())
