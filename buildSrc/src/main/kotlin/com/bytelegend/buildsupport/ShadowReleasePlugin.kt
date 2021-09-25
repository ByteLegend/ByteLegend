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

import com.github.jengelman.gradle.plugins.shadow.ShadowPlugin
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.named

class ShadowReleasePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply(ShadowPlugin::class.java)
        val shadowJar = target.tasks.named<ShadowJar>("shadowJar")
        shadowJar.configure {
            from(target.project(":utils").buildDir.resolve("game-resources-intermediate")) {
                include("i18n/all.json")
            }

            // kotlin js and js.map files are quite large - 2~4MiB !
            exclude("**/*.js", "**/*.js.map")
            dependsOn(":utils:generateI18nJsons")
            archiveClassifier.set("")
            archiveVersion.set("")
        }

        target.project(":server:github-release").registerExecTask(
            "Upload${target.name.kebabCaseToCamelCase()}ToGitHubRelease",
            "com.bytelegend.githubrelease.UploadToGitHubReleaseKt",
            target.buildDir.resolve("libs/${target.name}.jar").absolutePath
        ) {
            jvmArgs("-DgitHubToken=${System.getProperty("gitHubToken", "")}")
            dependsOn(shadowJar, target.tasks.named("check"))
        }
    }

    private fun String.kebabCaseToCamelCase() = split('-').joinToString("") { it.capitalize() }
}
