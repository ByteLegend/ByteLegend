import com.bytelegend.buildsupport.getEnvironment
import com.bytelegend.buildsupport.isDebug

plugins {
    id("org.springframework.boot") version "2.4.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm")
    kotlin("plugin.spring") version "1.4.21"
    id("configure-kotlin")
    id("configure-ktlint")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(project(":shared"))
    implementation(project(":server-api"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.named<JavaExec>("bootRun") {
    dependsOn(":utils:processGameResources")

    jvmArgs("-Dgame.resources=${rootProject.file("utils/build/game-resources").absolutePath}")
}