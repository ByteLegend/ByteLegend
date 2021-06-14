plugins {
    id("org.springframework.boot") version "2.4.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm")
    kotlin("plugin.spring") version "1.5.10"
    id("configure-kotlin")
    id("configure-ktlint")
}

evaluationDependsOn(":utils")

repositories {
    mavenCentral()
}

val libs: (String) -> String by rootProject.ext
val libVersions: (String) -> String by rootProject.ext

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    listOf(
        "jackson-databind",
        "jackson-core",
        "jackson-bom",
        "jackson-annotations",
        "jackson-datatype-jdk8",
        "jackson-datatype-jsr310",
        "jackson-module-parameter-names",
        "jackson-dataformat-yaml"
    ).forEach {
        implementation(libs(it).substringBeforeLast(":")) {
            version {
                strictly(libVersions(it).substringAfter(":"))
            }
        }
    }

    implementation(libs("jackson-module-kotlin")) {
        version {
            strictly(libVersions("jackson-module-kotlin").substringAfter(":"))
        }
        exclude(group = "org.jetbrains.kotlin")
    }

    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(project(":shared"))
    implementation(project(":server-shared:common"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(project(":server-shared:test-fixtures"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.named<JavaExec>("bootRun") {
    dependsOn(":utils:buildDevelopmentGameResources")

    jvmArgs("-Dlocal.RRBD=${rootProject.ext["developmentRRBD"]}")
}
tasks.named<Test>("test") {
    dependsOn(":utils:buildProductionGameResources")
    systemProperty("local.RRBD", rootProject.file(rootProject.ext["productionRRBD"].toString()))
    systemProperty("project.dir", rootProject.projectDir.absolutePath)
    systemProperty("build.tmp.dir", temporaryDir)
}
