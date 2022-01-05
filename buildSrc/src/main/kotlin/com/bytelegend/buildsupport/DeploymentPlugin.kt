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

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.TaskProvider
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

/**
 * Configure release/deploy related tasks.
 */
class DeploymentPlugin : Plugin<Project> {
    lateinit var buildTimestamp: String
    override fun apply(project: Project) {
        buildTimestamp = project.rootProject.extensions.extraProperties["buildTimestamp"]!!.toString()

        project.createDeployBeijingTasks("Beijing")
        project.createDeploySeoulTasks("Seoul")

        val updateVersionsTask = project.createUpdateVersionsTask()

        project.tasks.register("release") {
            dependsOn(updateVersionsTask)
            doLast {
                println("::warning ::Release successfully $buildTimestamp")
            }
        }
        project.tasks.register("deploy") {
            dependsOn("deployBeijing", "deploySeoul")
        }
    }

    private fun Project.createDeployBeijingTasks(region: String) {
        val uploadToS3Task = project.createUploadToS3Task(region)

        val releaseTasks = listOf(uploadToS3Task)

        project.tasks.register("release$region") {
            dependsOn(releaseTasks)
        }

        project.tasks.register("deploy$region") {
            mustRunAfter(releaseTasks)
        }
    }

    private fun Project.createDeploySeoulTasks(region: String) {
        val dockerPushBytelegendTask = project.createDockerPushTask(region, "bytelegend", ":server:app:dockerBuild")
        val uploadToS3Task = project.createUploadToS3Task(region)

        val releaseTasks = listOf(dockerPushBytelegendTask, uploadToS3Task)

        val mergeK8sYamls = project.createMergeK8sYamlTask(region)
        val deployToK8sTask = project.createDeployToK8sTask(region, mergeK8sYamls)

        project.tasks.register("release$region") {
            dependsOn(releaseTasks)
        }

        project.tasks.register("deploy$region") {
            mustRunAfter(releaseTasks)
            dependsOn(deployToK8sTask)
        }
    }

    private fun Project.createUpdateVersionsTask() = tasks.register("updateVersionsJsonOnMaster", UpdateVersionsJsonTask::class.java) {
        dependsOn("releaseBeijing", "releaseSeoul")
    }

    private fun Project.createDockerPushTask(region: String, image: String, dockerBuildTask: String) = registerReleaseExecTask(
        "dockerPush${image.capitalize()}To$region",
        "com.bytelegend.deployment.DockerPushToEcrKt",
        region,
        "$image:$buildTimestamp"
    ) {
        enabled = region != "Beijing"
        dependsOn(dockerBuildTask)
    }

    private fun Project.createUploadToS3Task(region: String) = registerReleaseExecTask(
        "uploadToS3$region",
        "com.bytelegend.deployment.UploadToS3Kt",
        rootProject.file("utils/build/game-resources-$buildTimestamp").absolutePath,
        region,
        "bytelegend-cdn",
        buildTimestamp
    )

    private fun Project.createMergeK8sYamlTask(region: String) = tasks.register("MergeK8sYamls$region", Copy::class.java) {
        from(file("production"))
        into(buildDir.resolve("deploy-k8s-${region.toLowerCase()}-$buildTimestamp"))
        include("*.yaml", "*.yaml.template")
    }

    private fun Project.createDeployToK8sTask(region: String, mergeK8sYamlsTask: TaskProvider<*>) = registerExecTask(
        "deployToK8s$region",
        "com.bytelegend.deployment.DeployToK8sKt",
        region,
        project.buildDir.resolve("deploy-k8s-${region.toLowerCase()}-$buildTimestamp").absolutePath,
        buildTimestamp
    ) {
        dependsOn(mergeK8sYamlsTask)
        doFirst {
            val marker = "https://cdn.bytelegend.com/${buildTimestamp}/map/hierarchy.yml"
            val request = HttpRequest.newBuilder().uri(URI.create(marker)).GET().build()
            val response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString())
            require(response.statusCode() in 200..399) {
                "$marker not found, was it released?\n${response.statusCode()}\n${response.body()}"
            }
        }
    }
}

internal fun Project.registerReleaseExecTask(
    name: String,
    mainClass: String,
    vararg args: String,
    configuration: JavaExec.() -> Unit = {}
) = registerExecTask(name, mainClass, *args) {
    dependsOn(":utils:buildReleaseGameResources")
    configuration()
}
