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
