import com.bytelegend.buildsupport.OpenSourceLibrary

plugins {
    kotlin("jvm")
    id("configure-ktlint")
    id("json2Java")
    id("buildGameResources")
}

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
    implementation(libs("jackson-module-kotlin")) {
        exclude(group = "org.jetbrains.kotlin")
    }
    implementation(libs("opencc4j"))
    implementation(libs("kotlin-reflect"))
    implementation(libs("kotlinx-serialization-json"))
    implementation(libs("batik-svggen"))
    implementation(libs("batik-dom"))
    implementation(libs("batik-swing"))
    implementation(libs("commonmark"))

    testImplementation(libs("junit-jupiter-api"))
    testImplementation(libs("junit-jupiter-engine"))
    testImplementation(libs("junit-jupiter-params"))
}
