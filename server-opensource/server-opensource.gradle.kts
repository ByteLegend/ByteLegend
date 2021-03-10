plugins {
    id("org.springframework.boot") version "2.4.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm")
    kotlin("plugin.spring") version "1.4.31"
    id("configure-kotlin")
    id("configure-ktlint")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
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
    dependsOn(":utils:processGameDevResources")

    jvmArgs("-Dlocal.RRBD=${rootProject.file("utils/build/game-resources").absolutePath}")
}

val localRRBD = rootProject.file("utils/build/game-resources").absolutePath
tasks.named<Test>("test") {
    dependsOn(":utils:processGameProductionResources")
    useJUnitPlatform()
    systemProperty("local.RRBD", localRRBD)
    systemProperty("project.dir", rootProject.projectDir.absolutePath)
    systemProperty("build.tmp.dir", temporaryDir)
}
