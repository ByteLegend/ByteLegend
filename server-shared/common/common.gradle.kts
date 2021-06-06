val libs: (String) -> String by rootProject.ext
val libVersions: (String) -> String by rootProject.ext

dependencies {
    listOf(
        "jackson-databind",
        "jackson-core",
        "jackson-dataformat-yaml"
    ).forEach {
        api(libs(it).substringBeforeLast(":")) {
            version {
                strictly(libVersions(it).substringAfter(":"))
            }
        }
    }
    api(libs("jackson-module-kotlin")) {
        version {
            strictly(libVersions("jackson-module-kotlin").substringAfter(":"))
        }
        exclude(group = "org.jetbrains.kotlin")
    }
    api(project(":shared"))
}
