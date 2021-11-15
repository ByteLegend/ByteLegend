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

import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.io.PrintStream
import java.util.concurrent.CountDownLatch

fun execSuccessfully(workingDir: File, vararg args: String): ExecResult {
    return exec(workingDir, *args).assertZeroExit()
}

fun exec(workingDir: File, vararg args: String): ExecResult {
    val process = ProcessBuilder().directory(workingDir).command(*args).start()
    val latch = CountDownLatch(2)
    val stdout: ByteArrayOutputStream = connectStream(process.inputStream, latch)
    val stderr: ByteArrayOutputStream = connectStream(process.errorStream, latch)
    val code = process.waitFor()
    latch.await()
    return ExecResult(args.toList(), code, stdout.toString(), stderr.toString())
}

private fun connectStream(forkedProcessOutput: InputStream, latch: CountDownLatch): ByteArrayOutputStream {
    val os = ByteArrayOutputStream()
    val ps = PrintStream(os, true)
    Thread {
        try {
            val reader = BufferedReader(InputStreamReader(forkedProcessOutput))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                ps.println(line)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            latch.countDown()
        }
    }.start()
    return os
}

data class ExecResult(
    val args: List<String>,
    val retcode: Int,
    val stdout: String,
    val stderr: String
) {
    fun assertZeroExit(): ExecResult {
        require(retcode == 0) {
            """
               Running [${args.joinToString(" ")}] returns $retcode
               Stdout: -----
               $stdout
               Stderr: -----
               $stderr
            """.trimIndent()
        }
        return this
    }

    fun getOutput() = stderr + "\n" + stdout
}
