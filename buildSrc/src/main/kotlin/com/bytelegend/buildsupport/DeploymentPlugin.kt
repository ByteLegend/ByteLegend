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

        listOf("Beijing", "Seoul").forEach { region ->
            val dockerPushBytelegendTask = project.createDockerPushTask(region, "bytelegend", ":server:app:dockerBuild")
            val dockerPushSyncserverTask = project.createDockerPushTask(region, "sync-server", ":server:sync-server:dockerBuild")
            val uploadToS3Task = project.createUploadToS3Task(region)

            val releaseTasks = listOf(dockerPushBytelegendTask, dockerPushSyncserverTask, uploadToS3Task)

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

        val updateVersionsTask = project.createUpdateVersionsTask()

        project.tasks.register("release") {
            dependsOn(updateVersionsTask)
            dependsOn("releaseBeijing") //, "releaseSeoul")
            doLast {
                println("::warning ::Release successfully $buildTimestamp")
            }
        }
        project.tasks.register("deploy") {
            dependsOn("deployBeijing") //, "deploySeoul")
        }
    }

    private fun Project.createUpdateVersionsTask() = tasks.register("updateVersionsJsonOnMaster", UpdateVersionsJsonTask::class.java)

    private fun Project.createDockerPushTask(region: String, image: String, dockerBuildTask: String) = registerReleaseExecTask(
        "dockerPush${image.capitalize()}To$region",
        "com.bytelegend.deployment.DockerPushToEcrKt",
        region,
        "$image:$buildTimestamp"
    ) {
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
        from(file("bytelegend-production-shared"))
        from(file("bytelegend-production-${region.toLowerCase()}"))
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
            val js = "https://cdn.bytelegend.com/${buildTimestamp}/map/hierarchy.yml"
            val httpClient = HttpClient.newHttpClient()
            val request = HttpRequest.newBuilder().uri(URI.create(js)).GET().build()
            val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
            require(response.statusCode() in 200..399) {
                "$js not found. Was it released?"
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
