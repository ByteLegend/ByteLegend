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
package com.bytelegend.app.testfixtures

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import java.io.File
import java.time.Instant

fun File.newFile(path: String): File {
    require(this.isDirectory) { "$this must be a directory!" }
    return resolve(path).apply {
        parentFile.mkdirs()
    }
}

fun File.setupGitUser() {
    execSuccessfully(this, "git", "config", "user.name", "ByteLegendBot")
    execSuccessfully(this, "git", "config", "user.email", "bot@bytelegend.com")
}

fun File.setupUpstreamGitRepo() {
    mkdirs()
    execSuccessfully(this, "git", "init", "--initial-branch=main")
    this.setupGitUser()
    this.resolve("init.txt").writeText("Init version")
    execSuccessfully(this, "git", "add", "init.txt")
    execSuccessfully(this, "git", "commit", "-m", "Init commit")
}

fun runBlockingUnit(fn: suspend () -> Unit) {
    runBlocking {
        fn()
    }
}

fun assertUpdatedRecently(instant: Instant) {
    assertUpdatedRecently(instant.toEpochMilli())
}

fun assertUpdatedRecently(epochMs: Long) {
    Assertions.assertTrue(epochMs >= Instant.now().toEpochMilli() - 10_000)
}
