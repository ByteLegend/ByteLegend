package com.bytelegend.app.servershared

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule

/**
 * Must be thread-safe.
 */
interface JsonMapper {
    fun toJson(obj: Any): String
    fun toPrettyJson(obj: Any): String
    fun toUglyJson(obj: Any): String

    fun <T> fromJson(string: String, klass: Class<T>): T
    fun <T> fromJson(string: String, tr: TypeReference<T>): T
    fun <T> fromYaml(string: String, klass: Class<T>): T
    fun <T> fromYaml(string: String, tr: TypeReference<T>): T
}

fun <T> ObjectMapper.install(klass: Class<T>, deserializer: JsonDeserializer<T>) {
    val module = SimpleModule()
    module.addDeserializer(klass, deserializer)
    registerModule(module)
}
