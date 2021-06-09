package com.bytelegend.buildsupport

import com.fasterxml.jackson.annotation.JsonFormat
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Provide a unified build timestamp for consumers, like:
 * - buildReleaseGameResources use the timestamp as destination directory name.
 * - dockerBuild use the timestamp as image tag.
 * - dockerTest use the timestamp-tagged docker image for test.
 * - Release
 * - Deploy
 */
class SetupBuildVersionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        require(project.parent == null) {
            "setupBuildVersion must be applied to rootProject!"
        }

        val buildTimestamp = when (val timestamp = project.findProperty("buildTimestamp")) {
            null -> DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
                .withZone(ZoneId.systemDefault())
                .format(Instant.now())
            "latest" -> project.readBuildVersions().last().buildTimestamp
            else -> timestamp
        }

        project.extensions.extraProperties.set("buildTimestamp", buildTimestamp)
        println("Use buildTimestamp $buildTimestamp")
    }
}

data class BuildVersion(
    val commit: String,
    val buildTimestamp: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    val buildTime: Instant
)
