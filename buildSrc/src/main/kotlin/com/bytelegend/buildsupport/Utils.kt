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
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.gradle.api.Project
import java.io.File
import java.util.*

fun Project.isCI() = System.getenv("CI") != null

fun Project.getEnvironment() = findProperty("environment") ?: "dev"

fun Project.isDebug() = findProperty("debug") !== null || findProperty("debug")?.toString() == "true"

fun Project.readBuildVersions(): List<BuildVersion> {
    return readBuildVersions(rootProject.file("versions.json").readText())
}

fun readBuildVersions(json: String): List<BuildVersion> {
    return fromJson(json, object : TypeReference<List<BuildVersion>>() {})
}

fun Project.writeBuildVersions(buildVersions: List<BuildVersion>) {
    rootProject.file("versions.json").writeText(toPrettyJson(buildVersions))
}

private val objectMapper = ObjectMapper().apply {
    registerModule(KotlinModule())
    registerModule(JavaTimeModule())
}
private val prettyMapper = objectMapper.writerWithDefaultPrettyPrinter()

fun toPrettyJson(obj: Any): String = prettyMapper.writeValueAsString(obj)
fun toUglyJson(obj: Any): String = objectMapper.writeValueAsString(obj)
fun <T> fromJson(json: String, tr: TypeReference<T>): T = objectMapper.readValue(json, tr)
fun fromJson(json: String) = objectMapper.readTree(json)
fun <T> fromJson(file: File, tr: TypeReference<T>): T = objectMapper.readValue(file, tr)
fun base64Encode(input: String): String = Base64.getEncoder().encodeToString(input.toByteArray())
fun base64Decode(input: String): String = String(Base64.getDecoder().decode(input))
