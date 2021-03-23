apply(plugin = "java-library")
apply(plugin = "org.jetbrains.kotlin.jvm")

val libs: (String) -> String by (rootProject.extensions.getByName("ext") as ExtraPropertiesExtension)

extensions.configure<JavaPluginExtension>("java") {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    add("implementation", libs("kotlinx-coroutines-jdk8"))
    add("implementation", libs("kotlinx-coroutines-core"))
    add("implementation", libs("kotlin-stdlib-jdk8"))
}