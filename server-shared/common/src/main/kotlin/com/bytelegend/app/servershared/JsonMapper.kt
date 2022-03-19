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

import com.bytelegend.app.shared.entities.mission.MapMissionSpec
import com.bytelegend.app.shared.objects.GameMapMission
import com.bytelegend.app.shared.protocol.WebSocketMessage
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.io.InputStream

/**
 * Must be thread-safe.
 */
interface JsonMapper {
    fun toJson(obj: Any): String
    fun toPrettyJson(obj: Any): String
    fun toUglyJson(obj: Any): String

    fun <T> fromJson(string: String, klass: Class<T>): T
    fun <T> fromJson(inputStream: InputStream, klass: Class<T>): T
    fun <T> fromJson(string: String, tr: TypeReference<T>): T
    fun <T> fromYaml(string: String, klass: Class<T>): T
    fun <T> fromYaml(string: String, tr: TypeReference<T>): T
}

/**
 * Serialize game object to frontend. The main purpose is to ignore some properties
 */
open class DefaultJsonMapper constructor(
    private val devMode: Boolean = false,
    configuration: ObjectMapper.() -> Unit = {}
) : JsonMapper {

    private val uglyMapper = ObjectMapper().apply {
        setSerializationInclusion(JsonInclude.Include.NON_NULL)
        registerModule(KotlinModule.Builder().build())
        registerMapMissionSpecMapping()
        install(WebSocketMessage::class.java, WebSocketMessageDeserializer())
        configuration()
    }
    private val prettyMapper = uglyMapper.apply {
        install(WebSocketMessage::class.java, WebSocketMessageDeserializer())
    }.writerWithDefaultPrettyPrinter()

    private val yamlMapper = ObjectMapper(YAMLFactory()).apply {
        registerModule(KotlinModule.Builder().build())
    }

    override fun toJson(obj: Any) = if (devMode) {
        prettyMapper.writeValueAsString(obj)
    } else {
        uglyMapper.writeValueAsString(obj)
    }

    override fun toPrettyJson(obj: Any) = prettyMapper.writeValueAsString(obj)
    override fun toUglyJson(obj: Any) = uglyMapper.writeValueAsString(obj)

    override fun <T> fromJson(string: String, klass: Class<T>) = uglyMapper.readValue(string, klass)
    override fun <T> fromJson(inputStream: InputStream, klass: Class<T>): T = uglyMapper.readValue(inputStream, klass)
    override fun <T> fromJson(string: String, tr: TypeReference<T>) = uglyMapper.readValue(string, tr)
    override fun <T> fromYaml(string: String, klass: Class<T>) = yamlMapper.readValue(string, klass)
    override fun <T> fromYaml(string: String, tr: TypeReference<T>) = yamlMapper.readValue(string, tr)
}

fun <T> ObjectMapper.install(klass: Class<T>, deserializer: JsonDeserializer<T>) {
    val module = SimpleModule()
    module.addDeserializer(klass, deserializer)
    registerModule(module)
}

fun ObjectMapper.registerMapMissionSpecMapping() {
    registerModule(
        SimpleModule().apply {
            setAbstractTypes(
                SimpleAbstractTypeResolver().apply {
                    addMapping(MapMissionSpec::class.java, GameMapMission::class.java)
                }
            )
        }
    )
}
