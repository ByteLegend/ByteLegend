import com.bytelegend.buildsrc.json2java.Json2JavaConversion
import com.bytelegend.buildsrc.json2java.Json2JavaTask
import com.bytelegend.buildsupport.OpenSourceLibrary
import com.bytelegend.buildsupport.getAllMaps
import com.bytelegend.buildsupport.getEnvironment
import com.bytelegend.buildsupport.toJsonString
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("configure-ktlint")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

apply(plugin = "json2Java")

val libs: (String) -> String by rootProject.ext
val oss: List<OpenSourceLibrary> by rootProject.ext

dependencies {
    implementation(project(":shared"))
    implementation(platform(libs("libraries-bom")))
    implementation("com.google.cloud:google-cloud-translate")
    implementation(libs("core-kotlin"))
    implementation(libs("java-jwt"))
    implementation(libs("bcprov-jdk15on"))
    implementation(libs("jackson-dataformat-yaml"))
    implementation(libs("jackson-module-kotlin"))
    implementation(libs("opencc4j"))
    implementation(libs("kotlinx-serialization-json"))
    implementation(libs("batik-svggen"))
    implementation(libs("batik-dom"))
    implementation(libs("batik-swing"))

    testImplementation(libs("junit-jupiter-api"))
    testImplementation(libs("junit-jupiter-engine"))
    testImplementation(libs("junit-jupiter-params"))
}

repositories {
    mavenCentral()
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
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

val tiledMapsDir = rootProject.file("resources/raw/maps")
val gameDataDir = rootProject.file("game-data")
val rrbdMapDataDir = RRBD.resolve("map")
val i18nOutputDir = RRBD.resolve("i18n")
val i18nAllJson = RRBD.resolve("i18n/all.json")

processResourcesTasks.add(registerExecTask(
    "generateI18nJsons",
    "com.bytelegend.utils.I18nGeneratorKt",
    gameDataDir.absolutePath,
    i18nOutputDir.absolutePath,
    i18nAllJson.absolutePath
) {
    // TODO filter out i18n.yml & i18n-common.yml
    inputs.files(fileTree(gameDataDir) {
        include("**/i18n*.yml")
    }).withPathSensitivity(PathSensitivity.RELATIVE)
    outputs.files(fileTree(gameDataDir) {
        include("**/i18n*.json")
    })
    outputs.dir(i18nOutputDir)
    outputs.file(i18nAllJson)
})

processResourcesTasks.add(tasks.register<Copy>("copyHierarchyYml") {
    from(gameDataDir)
    include("hierarchy.yml")

    into(rrbdMapDataDir)
})

val mapHierarchyYaml = rootProject.file("game-data/hierarchy.yml").apply {
    if (!isFile) {
        throw IllegalStateException("game-data/hierarchy.yml not found. Did you run `git submodule update game-data`?")
    }
}
val allMaps: List<String> = getAllMaps(mapHierarchyYaml)
allMaps.forEach { map ->
    val inputTiledJson = tiledMapsDir.resolve("$map/${map}.json")
    if (!inputTiledJson.isFile) {
        return@forEach
    }
    val inputGameDataMapDir = gameDataDir.resolve(map)
    val outputMapJsonsDir = rrbdMapDataDir.resolve(map)
    processResourcesTasks.add(registerExecTask(
        "generate${map}Map", "com.bytelegend.utils.MapGeneratorKt",
        map,
        inputTiledJson.absolutePath,
        gameDataDir.absolutePath,
        outputMapJsonsDir.absolutePath
    ) {
        inputs.file(inputTiledJson).withPathSensitivity(PathSensitivity.RELATIVE)
        inputs.dir(inputGameDataMapDir).withPathSensitivity(PathSensitivity.RELATIVE)
        inputs.dir(rootProject.file("resources/raw/tilesets")).withPathSensitivity(PathSensitivity.RELATIVE)
        inputs.dir(rootProject.file("resources/raw/tileset-jsons")).withPathSensitivity(PathSensitivity.RELATIVE)
        outputs.dir(outputMapJsonsDir)
    })
}

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

fun forEachMapWithProject(consumer: (String) -> Unit) {
    allMaps.filter { rootProject.findProject(":client:game-$it") != null }.forEach(consumer)
}

tasks.register<Copy>("processGameDevJs") {
    dependsOn(":client:game-page:browserDevelopmentWebpack")
    forEachMapWithProject {
        dependsOn(":client:game-$it:browserDevelopmentWebpack")
    }
    from(project(":client:game-page").file("build/distributions/game-page.js"))
    forEachMapWithProject {
        from(project(":client:game-$it").file("build/distributions/game-$it.js"))
    }
    into(RRBD.resolve("js"))
}

tasks.register<Copy>("processGameProductionJs") {
    dependsOn(":client:game-page:browserProductionWebpack")
    forEachMapWithProject {
        dependsOn(":client:game-$it:browserProductionWebpack")
    }
    from(project(":client:game-page").file("build/distributions/game-page.js"))
    forEachMapWithProject {
        from(project(":client:game-$it").file("build/distributions/game-$it.js"))
    }
    into(RRBD.resolve("js"))
}

val ossJson = RRBD.resolve("misc/oss.json")
processResourcesTasks.add(tasks.register("generateOSSJson") {
    inputs.property("ossLibraries", oss)
    outputs.file(ossJson)

    doLast {
        ossJson.parentFile.mkdirs()
        ossJson.writeText(toJsonString(oss))
    }
})

tasks.register("processGameDevResources") {
    shouldRunAfter("compressPng")
    dependsOn("processGameDevJs")
    dependsOn(processResourcesTasks)
}

tasks.register("processGameProductionResources") {
    shouldRunAfter("compressPng")
    dependsOn("processGameProductionJs")
    dependsOn(processResourcesTasks)
}

tasks.register<Exec>("compressPng") {
    dependsOn(processResourcesTasks)
    val pngFiles = rrbdMapDataDir.walk()
        .filter { it.name.endsWith(".png") }
        .map { rrbdMapDataDir.toPath().relativize(it.absoluteFile.toPath()) }
        .toList()

    commandLine("docker", "run", "-v", "${rrbdMapDataDir.absolutePath}:/var/workdir/", "kolyadin/pngquant", "-f")
    args(pngFiles)
    args("--ext", ".png")
}





