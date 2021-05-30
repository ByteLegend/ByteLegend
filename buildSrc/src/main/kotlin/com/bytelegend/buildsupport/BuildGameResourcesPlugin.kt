package com.bytelegend.buildsupport

import com.bytelegend.buildsrc.json2java.Json2JavaConversion
import com.bytelegend.buildsrc.json2java.Json2JavaTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.ExtraPropertiesExtension
import org.gradle.api.tasks.ClasspathNormalizer
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.TaskProvider
import java.io.File
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.function.Consumer

/**
 * There are 3 variants of game resource building tasks:
 * 1. `buildDevelopmentResources` uses development webpack config, generated resources are in `build/game-resources-development`.
 * 2. `buildProductionResources` uses production webpack config, generated resources are in `build/game-resources-production`.
 * 3. `buildReleaseResources` uses production webpack config, generated resources are in `build/game-resources-{timestamp}`.
 */
class BuildGameResourcesPlugin : Plugin<Project> {
    private lateinit var rootProject: Project
    private val allMaps: List<String> by lazy {
        rootProject.file("game-data/hierarchy.yml").apply {
            if (!isFile) {
                throw IllegalStateException("game-data/hierarchy.yml not found. Did you run `git submodule update game-data`?")
            }
        }.let {
            getAllMaps(it)
        }
    }
    private val gameDataInputDir: File by lazy {
        rootProject.file("game-data")
    }

    private val Project.intermediateRRBD: File
        get() = buildDir.resolve("game-resources-intermediate")
    private val Project.developmentRRBD: File
        get() = buildDir.resolve("game-resources-dev")
    private val Project.productionRRBD: File
        get() = buildDir.resolve("game-resources-production")
    private val Project.releaseRRBD: File
        get() {
            val timestamp = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
                .withZone(ZoneId.systemDefault())
                .format(Instant.now())
            return buildDir.resolve("game-resources-$timestamp")
        }

    override fun apply(project: Project) {
        rootProject = project.rootProject

        val copyTiledMapJsonTask = project.createCopyTiledMapJsonTask()
        val copyTiledTilesetJsonTask = project.createCopyTiledTilesetJsonTask()
        project.configureJson2Java(copyTiledMapJsonTask, copyTiledTilesetJsonTask)

        val processResourcesTasks = mutableListOf<TaskProvider<*>>()
        processResourcesTasks.add(project.createCopyCookedStaticResources())
        processResourcesTasks.add(project.createCopyHierarchyYmlTask())
        processResourcesTasks.add(project.createGenerateOssJsonTask())
        processResourcesTasks.add(project.createGenerateI18nJsonTask())
        processResourcesTasks.add(project.createGeneratePlayerAnimationSetTask())

        allMaps.forEach {
            project.createMapGeneratorTask(it, processResourcesTasks)
        }

        val compressPngTask = project.createCompressPngTask(processResourcesTasks)

        project.createBuildResourcesTasks(Variant.Development, project.developmentRRBD, processResourcesTasks)
        project.createBuildResourcesTasks(Variant.Production, project.productionRRBD, processResourcesTasks)
        project.createBuildResourcesTasks(
            Variant.Release,
            project.releaseRRBD,
            processResourcesTasks + listOf(compressPngTask)
        )
    }

    enum class WebpackMode {
        Development,
        Production
    }

    enum class Variant(val mode: WebpackMode) {
        Development(WebpackMode.Development),
        Production(WebpackMode.Production),
        Release(WebpackMode.Production)
    }

    private fun Project.createCompressPngTask(processResourcesTasks: List<TaskProvider<*>>) =
        tasks.register("compressAllPngs", Exec::class.java) {
            mustRunAfter(processResourcesTasks)
            commandLine(
                "docker", "run", "-v", "${intermediateRRBD.absolutePath}:/var/workdir/", "kolyadin/pngquant",
                "find", ".", "-type", "f", "-name", "*.png", "-exec", "pngquant", "-f", "--ext", ".png", "{}", ";"
            )
        }

    private fun Project.createBuildResourcesTasks(
        variant: Variant,
        outputRRBD: File,
        processResourcesTasks: List<TaskProvider<*>>
    ) {
        val copyIntermediateToFinalRRBD = tasks.create("copyIntermediateTo${variant}RRBD", Copy::class.java) {
            dependsOn(processResourcesTasks)
            from(intermediateRRBD)
            into(outputRRBD)
        }

        val processGameJs = tasks.register("processGame${variant}Js", Copy::class.java) {
            dependsOn(":client:game-page:browser${variant.mode}Webpack")
            forEachMapWithProject {
                dependsOn(":client:game-$it:browser${variant.mode}Webpack")
            }
            from(project(":client:game-page").file("build/distribution-${variant.mode.name.toLowerCase()}/game-page.js"))
            forEachMapWithProject {
                from(project(":client:game-$it").file("build/distribution-${variant.mode.name.toLowerCase()}/game-$it.js"))
            }
            into(outputRRBD.resolve("js"))
        }

        tasks.register("build${variant}GameResources") {
            dependsOn(copyIntermediateToFinalRRBD, processGameJs)
            doLast {
                println("Game resources has been generated in ${outputRRBD.absolutePath}")
            }
        }

        fileTree(outputRRBD)
            .filter { it.name.endsWith(".png") }
            .forEach {
                val size = it.length()
                if (size > 1024 * 1024 * 1024) {
                    logger.warn("${it.absolutePath} size is ${size / 1024 / 1024}M!")
                }
            }
    }

    private fun Project.createMapGeneratorTask(
        map: String,
        processResourcesTasks: MutableList<TaskProvider<*>>
    ) {
        val tiledMapsInputDir = project.rootProject.file("resources/raw/maps")
        val inputTiledJson = tiledMapsInputDir.resolve("$map/${map}.json")
        if (!inputTiledJson.isFile) {
            return
        }
        val inputGameDataMapDir = gameDataInputDir.resolve(map)
        val outputMapJsonsDir = intermediateRRBD.resolve("map/$map")
        processResourcesTasks.add(registerExecTask(
            "generate${map}Map", "com.bytelegend.utils.MapGeneratorKt",
            map,
            inputTiledJson.absolutePath,
            gameDataInputDir.absolutePath,
            outputMapJsonsDir.absolutePath
        ) {
            inputs.file(inputTiledJson).withPathSensitivity(PathSensitivity.RELATIVE)
            inputs.dir(inputGameDataMapDir).withPathSensitivity(PathSensitivity.RELATIVE)
            inputs.dir(rootProject.file("resources/raw/tilesets")).withPathSensitivity(PathSensitivity.RELATIVE)
            inputs.dir(rootProject.file("resources/raw/tileset-jsons"))
                .withPathSensitivity(PathSensitivity.RELATIVE)
            outputs.dir(outputMapJsonsDir)
        })
    }

    private fun Project.configureJson2Java(vararg copyJsonTasks: TaskProvider<*>) {
        tasks.withType(Json2JavaTask::class.java) {
            dependsOn(*copyJsonTasks)
        }

        extensions.extraProperties["json2Java"] = listOf(
            Json2JavaConversion()
                .setSrcFile(project.buildDir.resolve("TiledMap.json"))
                .setDestDir(project.projectDir.resolve("src/main/java"))
                .setTargetPackage("com.bytelegend.github.utils.generated"),
            Json2JavaConversion()
                .setSrcFile(project.buildDir.resolve("TiledTileset.json"))
                .setDestDir(project.projectDir.resolve("src/main/java"))
                .setTargetPackage("com.bytelegend.github.utils.generated")
        )
    }

    private fun Project.createCopyTiledMapJsonTask() = project.tasks.register("copyMapJson", Copy::class.java) {
        from(rootProject.file("resources/raw/maps/JavaIsland/JavaIsland.json"))
        into(project.buildDir)
        rename { "TiledMap.json" }
    }

    private fun Project.createCopyTiledTilesetJsonTask() = tasks.register<Copy>("copyTilesetJson", Copy::class.java) {
        from(rootProject.rootDir.resolve("resources/raw/tileset-jsons/java-flag.json"))
        into(project.buildDir)
        rename { "TiledTileset.json" }
    }

    private fun Project.createCopyCookedStaticResources() =
        tasks.register("copyCookedStaticResourcesTo", Copy::class.java) {
            from(rootProject.file("resources/cooked"))
            include("**")
            exclude("README.md")

            into(intermediateRRBD)
        }

    private fun Project.createGenerateOssJsonTask() = tasks.register("generateOSSJson") {
        val ossJson = intermediateRRBD.resolve("misc/oss.json")
        val oss = (rootProject.extensions.getByName("ext") as ExtraPropertiesExtension)["oss"]!!
        inputs.property("ossLibraries", oss)
        outputs.file(ossJson)

        doLast {
            ossJson.parentFile.mkdirs()
            ossJson.writeText(toJsonString(oss))
        }
    }

    private fun Project.createGenerateI18nJsonTask(): TaskProvider<JavaExec> {
        val i18nOutputDir = intermediateRRBD.resolve("i18n")
        val i18nAllOutputJson = intermediateRRBD.resolve("i18n/all.json")
        return registerExecTask(
            "generateI18nJsons",
            "com.bytelegend.utils.I18nGeneratorKt",
            gameDataInputDir.absolutePath,
            i18nOutputDir.absolutePath,
            i18nAllOutputJson.absolutePath
        ) {
            // TODO filter out i18n.yml & i18n-common.yml
            inputs.files(fileTree(gameDataInputDir) {
                include("**/i18n*.yml")
            }).withPathSensitivity(PathSensitivity.RELATIVE)
            outputs.files(fileTree(gameDataInputDir) {
                include("**/i18n*.json")
            })
            outputs.dir(i18nOutputDir)
            outputs.file(i18nAllOutputJson)
        }
    }

    private fun Project.createCopyHierarchyYmlTask() =
        tasks.register("copyHierarchyYml", Copy::class.java) {
            from(rootProject.file("game-data"))
            include("hierarchy.yml")

            into(intermediateRRBD.resolve("map"))
        }

    private fun Project.createGeneratePlayerAnimationSetTask(): TaskProvider<JavaExec> {
        val inputAnimationsDir = rootProject.file("resources/raw/player-animations")
        val outputAnimationSetDir = intermediateRRBD.resolve("img/player")

        return registerExecTask(
            "generatePlayerAnimationSet", "com.bytelegend.utils.PlayerAnimationSetGeneratorKt",
            inputAnimationsDir.absolutePath,
            outputAnimationSetDir.absolutePath
        ) {
            inputs.dir(inputAnimationsDir)
            outputs.dir(outputAnimationSetDir)
        }
    }

    private fun Project.sourceSet(name: String): SourceSet {
        return ((this as ExtensionAware).extensions.getByName("sourceSets") as SourceSetContainer).getByName(name)
    }

    private fun Project.registerExecTask(
        name: String,
        mainClassName: String,
        vararg args: String,
        configuration: JavaExec.() -> Unit = {}
    ) = tasks.register(name, JavaExec::class.java) {
        classpath = project.sourceSet("main").runtimeClasspath
        workingDir = rootProject.rootDir
        mainClass.set(mainClassName)
        args(*args)
        jvmArgs("-Denvironment=${project.getEnvironment()}")

        inputs.files(sourceSet("main").runtimeClasspath).withNormalizer(ClasspathNormalizer::class.java)
        this.configuration()
    }

    fun forEachMapWithProject(consumer: Consumer<String>) {
        allMaps.filter { rootProject.findProject(":client:game-$it") != null }.forEach(consumer)
    }
}