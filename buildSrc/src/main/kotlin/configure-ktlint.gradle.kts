configurations.create("ktlint")
dependencies {
    val ktlintVersion = "0.41.0"
    "ktlint"("com.pinterest:ktlint:$ktlintVersion")
}

val ktlintTask = tasks.register<JavaExec>("ktlint") {
    group = "verification"
    description = "Check Kotlin code style"
    classpath = configurations["ktlint"]
    main = "com.pinterest.ktlint.Main"

    val output = buildDir.resolve("ktlint-report.txt")
    args("src/**/*.kt", "--reporter=plain", "--reporter=plain,output=${output.absolutePath}")

    inputs.files(fileTree("src") {
        include("**/*.kt")
    })
    outputs.file(output)

    mustRunAfter(tasks.withType<AbstractCompile>())
}

tasks.named("check").configure { dependsOn(ktlintTask) }

tasks.register<JavaExec>("ktlintFormat") {
    group = "formatting"
    description = "Fix Kotlin code style deviations"
    classpath = configurations["ktlint"]
    main = "com.pinterest.ktlint.Main"
    args("-F", "src/**/*.kt")
}