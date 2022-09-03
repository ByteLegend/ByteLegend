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

package com.bytelegend.app.servershared.utils

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class Shell(private val workingDir: File) {
    fun execSuccessfully(vararg args: String): ExecResult {
        return exec(*args).assertZeroExit()
    }

    fun exec(vararg args: String): ExecResult {
        val stdout = ByteArrayOutputStream()
        val stderr = ByteArrayOutputStream()
        val pb = ProcessBuilder(*args)
        pb.directory(workingDir)
        pb.environment()["LESSCHARSET"] = "UTF-8"

        val process = pb.start()
        val latch = CountDownLatch(2)
        copyStream(process.inputStream, stdout, latch)
        copyStream(process.errorStream, stderr, latch)
        val result = process.waitFor(1, TimeUnit.MINUTES)
        val latchResult = latch.await(1, TimeUnit.MINUTES)
        check(!(!result || !latchResult)) {
            """
                Timeout waiting ${args.contentToString()}
                stdout:
                $stdout
                stderr:
                $stderr
                """.trimIndent()
        }
        return ExecResult(
            listOf(*args),
            process.exitValue(),
            workingDir.absolutePath,
            stdout.toString(),
            stderr.toString()
        )
    }

    private fun copyStream(inputStream: InputStream, os: OutputStream, latch: CountDownLatch) {
        Thread {
            try {
                inputStream.transferTo(os)
            } finally {
                latch.countDown()
            }
        }.start()
    }
}

class ExecResult(val args: List<String?>, val exitValue: Int, val workingDir: String, val stdout: String, val stderr: String) {
    private val output: String
        get() = """
            $stdout
            $stderr
            """.trimIndent()

    fun assertZeroExit(): ExecResult {
        withLog()
        if (exitValue != 0) {
            throw AssertionError()
        }
        return this
    }

    fun withLog(): ExecResult {
        System.out.printf("Exec %s in %s exited with %s:\n%s", args, workingDir, exitValue, output)
        return this
    }
}
