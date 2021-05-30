plugins {
    id("org.springframework.boot") version "2.4.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm")
    kotlin("plugin.spring") version "1.5.10"
    id("configure-kotlin")
    id("configure-ktlint")
}

repositories {
    mavenCentral()
}

val libs: (String) -> String by rootProject.ext

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(libs("jackson-dataformat-yaml"))
    implementation(project(":shared"))
    implementation(project(":server-shared:common"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(project(":server-shared:test-fixtures"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.named<JavaExec>("bootRun") {
    dependsOn(":utils:processGameDevResources")

    jvmArgs("-Dlocal.RRBD=${rootProject.file("utils/build/game-resources").absolutePath}")
}

val localProductionRRBD = rootProject.file("utils/build/game-resources-production").absolutePath
tasks.named<Test>("test") {
    dependsOn(":utils:buildProductionGameResources")
    useJUnitPlatform()
    systemProperty("local.RRBD", localProductionRRBD)
    systemProperty("project.dir", rootProject.projectDir.absolutePath)
    systemProperty("build.tmp.dir", temporaryDir)
}
