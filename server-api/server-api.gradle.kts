plugins {
    kotlin("jvm")
    id("configure-kotlin")
    id("configure-ktlint")
}

val libs: (String) -> String by rootProject.ext

repositories {
    jcenter()
    mavenCentral()
    maven { setUrl("https://oss.sonatype.org/content/repositories/snapshots") }
}

// Must be here, otherwise it won't be configured because of different classloaders
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(libs("dynamodb-enhanced"))
    implementation(libs("dynamodb"))
    implementation(project(":shared"))
}
