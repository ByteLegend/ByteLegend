val libs: (String) -> String by rootProject.ext

dependencies {
    api(libs("junit-jupiter-api"))
    api(libs("junit-jupiter-engine"))
    api(libs("junit-jupiter-params"))
    api(libs("mockk")) {
        exclude(group = "org.jetbrains.kotlin")
    }
    api(libs("org.testcontainers:testcontainers"))
    api(libs("org.testcontainers:selenium"))
    api(libs("org.testcontainers:junit-jupiter"))
    api(libs("selenium-api"))
    api(libs("selenium-chrome-driver"))
    api(libs("selenium-firefox-driver"))
    api(project(":shared"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}
