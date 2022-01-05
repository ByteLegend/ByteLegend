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
    val frames: Int,
    val children: List<GameMapDefinition> = emptyList(),
    val roadmap: Boolean = true
)
