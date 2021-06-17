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
