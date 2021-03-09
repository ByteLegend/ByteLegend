subprojects {
    apply(plugin = "configure-kotlin")
    apply(plugin = "configure-ktlint")

    repositories {
        jcenter()
        mavenCentral()
        maven { setUrl("https://oss.sonatype.org/content/repositories/snapshots") }
    }

    // Must be here, otherwise it won't be configured because of different classloaders
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}
