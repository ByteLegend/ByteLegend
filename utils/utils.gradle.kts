import com.bytelegend.buildsrc.json2java.Json2JavaConversion
import com.bytelegend.buildsrc.json2java.Json2JavaTask
import com.bytelegend.buildsupport.OpenSourceLibrary
import com.bytelegend.buildsupport.getEnvironment
import com.bytelegend.buildsupport.toJsonString
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

apply(plugin = "json2Java")

val libs: (String) -> String by rootProject.ext
val oss: List<OpenSourceLibrary> by rootProject.ext

dependencies {
    implementation(project(":shared"))
    implementation(libs("core-kotlin"))
    implementation(libs("java-jwt"))
    implementation(libs("bcprov-jdk15on"))
    implementation(libs("jackson-dataformat-yaml"))
    implementation(libs("opencc4j"))
    implementation(libs("kotlinx-serialization-json"))
}

repositories {
    mavenCentral()
    maven {
        setUrl("http://oss.sonatype.org/content/repositories/snapshots")
    }
}

fun registerExecTask(name: String, mainClassName: String, vararg args: String, configuration: JavaExec.() -> Unit = {}) =
    tasks.register<JavaExec>(name) {
        classpath = sourceSets["main"].runtimeClasspath
        workingDir = rootProject.rootDir
        mainClass.set(mainClassName)
        args(*args)
        jvmArgs("-Denvironment=${project.getEnvironment()}")

        inputs.files(sourceSets["main"].runtimeClasspath).withNormalizer(ClasspathNormalizer::class)

        this.configuration()
    }

//val buildTimestamp = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").withZone(ZoneId.systemDefault()).format(Instant.now())
val RRBD = project.buildDir.resolve("game-resources")

// Rename the Tiled JSONs because Json2Java plugin generation is name-based
tasks.register<Copy>("copyMapJson") {
    from(rootProject.file("resources/raw/maps/JavaIsland/JavaIsland.json"))
    into(project.buildDir)
    rename { "TiledMap.json" }
}

tasks.register<Copy>("copyTilesetJson") {
    from(rootProject.rootDir.resolve("resources/raw/tileset-jsons/java-flag.json"))
    into(project.buildDir)
    rename { "TiledTileset.json" }
}

tasks.withType<Json2JavaTask>() {
    dependsOn("copyMapJson", "copyTilesetJson")
}

extra["json2Java"] = listOf(
    Json2JavaConversion()
        .setSrcFile(project.buildDir.resolve("TiledMap.json"))
        .setDestDir(projectDir.resolve("src/main/java"))
        .setTargetPackage("com.bytelegend.github.utils.generated"),
    Json2JavaConversion()
        .setSrcFile(project.buildDir.resolve("TiledTileset.json"))
        .setDestDir(projectDir.resolve("src/main/java"))
        .setTargetPackage("com.bytelegend.github.utils.generated")
)


/**
 * Resource processing, see <project-root>/resources/README.md
 */
val processResourcesTasks = mutableListOf<Any>()

processResourcesTasks.add(tasks.register<Copy>("copyStaticResources") {
    from(rootProject.file("resources/cooked"))
    include("**")
    exclude("README.md")

    into(RRBD)
})

val i18nInputDir = rootProject.file("i18n")
val i18nOutputDir = RRBD.resolve("i18n")
val i18nAllJson = project(":server-api").file("src/main/resources/i18n-all.json")
processResourcesTasks.add(registerExecTask(
    "generateI18nJsons",
    "com.bytelegend.utils.I18nGeneratorKt",
    i18nInputDir.absolutePath,
    i18nOutputDir.absolutePath,
    i18nAllJson.absolutePath
) {
    inputs.dir(i18nInputDir)
    outputs.dir(i18nOutputDir)
    outputs.file(i18nAllJson)
})

val inputMapDir = rootProject.file("resources/raw/maps")
val outputMapDataDir = RRBD.resolve("map")

processResourcesTasks.add(registerExecTask(
    "generateMapData", "com.bytelegend.utils.MapGeneratorKt",
    inputMapDir.absolutePath,
    outputMapDataDir.absolutePath
) {
    inputs.dir(inputMapDir)
    inputs.dir(rootProject.file("resources/raw/tilesets"))
    inputs.dir(rootProject.file("resources/raw/tileset-jsons"))
    outputs.dir(outputMapDataDir)
})

val inputAnimationsDir = rootProject.file("resources/raw/player-animations")
val outputAnimationSetDir = RRBD.resolve("img/player")
processResourcesTasks.add(registerExecTask(
    "generatePlayerAnimationSet", "com.bytelegend.utils.PlayerAnimationSetGeneratorKt",
    inputAnimationsDir.absolutePath,
    outputAnimationSetDir.absolutePath
) {
    inputs.dir(inputAnimationsDir)
    outputs.dir(outputAnimationSetDir)
})

val allMaps: List<String> = rootProject
    .file("resources/raw/maps").walk()
    .filter { it.isFile && it.name.endsWith(".json") && it.name != "hierarchy.json" }
    .map { it.name.replace(".json", "") }
    .toList()
processResourcesTasks.add(tasks.register<Copy>("copyGameJs") {
    if (project.getEnvironment() == "dev") {
        dependsOn(":client:game-page:browserDevelopmentWebpack")
        allMaps.forEach {
            dependsOn(":client:game-$it:browserDevelopmentWebpack")
        }
    } else {
        dependsOn(":client:game-page:browserProductionWebpack")
        allMaps.forEach {
            dependsOn(":client:game-$it:browserProductionWebpack")
        }
    }
    from(project(":client:game-page").file("build/distributions/game-page.js"))
    allMaps.forEach {
        from(project(":client:game-$it").file("build/distributions/game-$it.js"))
    }
    into(RRBD.resolve("js"))
})

val ossJson = RRBD.resolve("misc/oss.json")
processResourcesTasks.add(tasks.register("generateOSSJson") {
    inputs.property("ossLibraries", oss)
    outputs.file(ossJson)

    doLast {
        ossJson.parentFile.mkdirs()
        ossJson.writeText(toJsonString(oss))
    }
})

tasks.register("processGameResources") {
    dependsOn(processResourcesTasks)
}




