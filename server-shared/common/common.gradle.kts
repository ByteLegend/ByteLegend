val libs: (String) -> String by rootProject.ext

dependencies {
    api(libs("jackson-core"))
    api(libs("jackson-databind"))
    api(project(":shared"))
}
