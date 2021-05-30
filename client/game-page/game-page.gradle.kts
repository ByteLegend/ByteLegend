plugins {
    id("configure-kotlin-js")
}

val libs: (String) -> String by rootProject.ext
val libVersions: (String) -> String by rootProject.ext

repositories {
    maven(url = "https://dl.bintray.com/kodein-framework/Kodein-DI/")
}

dependencies {
    implementation(libs("kodein-di"))
    implementation(npm("react-player", libVersions("react-player")))
    implementation(npm("react-select", libVersions("react-select")))
    implementation(npm("react-textarea-autosize", libVersions("react-textarea-autosize")))
    testImplementation(kotlin("test-js"))
}