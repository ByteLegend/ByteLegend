package com.bytelegend.buildsupport

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.gradle.api.Project
import java.io.File

fun Project.getEnvironment() = findProperty("environment") ?: "dev"

fun Project.isDebug() = findProperty("debug") !== null || findProperty("debug")?.toString() == "true"

fun Project.readBuildVersions(): List<BuildVersion> {
    return fromJson(rootProject.file("versions.json"), object : TypeReference<List<BuildVersion>>() {})
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
fun <T> fromJson(file: File, tr: TypeReference<T>): T = objectMapper.readValue(file, tr)
