/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
configurations.create("ktlint")
dependencies {
    val ktlintVersion = "0.43.2"
    "ktlint"("com.pinterest:ktlint:$ktlintVersion")
}

val ktlintTask = tasks.register<JavaExec>("ktlint") {
    group = "verification"
    description = "Check Kotlin code style"
    classpath = configurations["ktlint"]
    mainClass.set("com.pinterest.ktlint.Main")

    val output = buildDir.resolve("ktlint-report.txt")
    args("src/**/*.kt", "--reporter=plain", "--reporter=plain,output=${output.absolutePath}")

    inputs.files(fileTree("src") {
        include("**/*.kt")
    })
    outputs.file(output)

    mustRunAfter(tasks.withType<AbstractCompile>())
}

tasks.withType<Test>().configureEach {
    mustRunAfter(ktlintTask)
}

tasks.register<JavaExec>("ktlintFormat") {
    group = "formatting"
    description = "Fix Kotlin code style deviations"
    classpath = configurations["ktlint"]
    mainClass.set("com.pinterest.ktlint.Main")
    args("-F", "src/**/*.kt")
}
