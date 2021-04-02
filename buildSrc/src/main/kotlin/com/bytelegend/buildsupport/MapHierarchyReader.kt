package com.bytelegend.buildsupport

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.io.File

private val yamlMapper = ObjectMapper(YAMLFactory()).apply {
    registerModule(KotlinModule())
}

fun getAllMaps(hierarchyYaml: File): List<String> {
    val ret = mutableListOf<String>()
    val list = yamlMapper.readValue(hierarchyYaml, object : TypeReference<List<GameMapDefinition>>() {})
    readInto(ret, list)
    return ret
}

private fun readInto(ret: MutableList<String>, list: List<GameMapDefinition>) {
    list.forEach {
        ret.add(it.id)
        readInto(ret, it.children)
    }
}

data class GameMapDefinition(
    val id: String,
    val children: List<GameMapDefinition>,
    val frames: Int
)