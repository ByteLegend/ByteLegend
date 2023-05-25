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

package com.bytelegend.utils

import org.apache.commons.io.FilenameUtils
import org.springframework.util.AntPathMatcher
import java.io.File
import java.io.IOException
import java.nio.file.FileVisitResult
import java.nio.file.FileVisitor
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.attribute.BasicFileAttributes

internal const val LICENSE = """Copyright 2021 ByteLegend Technologies and the original author or authors.

Licensed under the GNU Affero General Public License v3.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License."""
internal val IGNORED_PATHS = listOf(
    "**/build/**",
    "**/.*/**",
    "**/generated/**",
    "**/githubdiffparser", // It's a copy which uses Apache-2 license
    "github1s/**"
)
internal val INCLUDED_PATHS = listOf(
    "**/*.kt",
    "**/*.yml",
    "**/*.kts",
    "**/*.java",
)
internal val MATCHER = AntPathMatcher()

internal fun Path.unixStyle(): String = FilenameUtils.separatorsToUnix(this.toString())

internal fun Path.isIgnoredPath(): Boolean = IGNORED_PATHS.any {
    // Remove leading "./"
    require(!this.isAbsolute) { "$this must be relative!" }
    if (this.unixStyle() == ".") {
        return false
    }
    MATCHER.match(it, this.unixStyle().substring(2))
}

internal fun Path.isIncludedPath() = INCLUDED_PATHS.any {
    // Remove leading "./"
    MATCHER.match(it, this.unixStyle().substring(2))
}

internal fun File.parseLicense() = when {
    name.endsWith(".yml") -> readLines().parseLicenseFromYaml()
    else -> readLines().parseLicenseFromCLike()
}

internal fun File.prependLicense() = when {
    name.endsWith(".yml") -> writeText(licenseForYaml() + readText())
    else -> writeText(licenseForCLike() + readText())
}

fun licenseForYaml(): String = LICENSE.lines().joinToString("\n") { "# $it" } + "\n\n"
fun licenseForCLike(): String = "/*\n" + LICENSE.lines().joinToString("\n") { " * $it" } + "\n */\n\n"

private fun List<String>.parseLicenseFromCLike(): String {
    val licenseLines = mutableListOf<String>()
    for (line in this) {
        if ((line.isBlank() || line.startsWith("/*")) && licenseLines.isEmpty()) {
            continue
        } else if (line.contains("*/")) {
            break
        } else if (line.startsWith(" *")) {
            licenseLines.add(line.substring(2).trimStart())
        } else {
            break
        }
    }
    return licenseLines.joinToString("\n")
}

private fun List<String>.parseLicenseFromYaml(): String {
    val licenseLines = mutableListOf<String>()
    for (line in this) {
        if (line.isBlank() && licenseLines.isEmpty()) {
            continue
        } else if (line.startsWith("#")) {
            licenseLines.add(line.substring(1).trimStart())
        } else {
            break
        }
    }
    return licenseLines.joinToString("\n")
}

/**
 * Make sure all specific files have licences at the beginning.
 */
fun main() {
    val rootProjectDir = File(".").toPath()
    Files.walkFileTree(rootProjectDir, object : FileVisitor<Path> {
        override fun preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult {
            return if (dir.isIgnoredPath()) {
                FileVisitResult.SKIP_SUBTREE
            } else {
                FileVisitResult.CONTINUE
            }
        }

        override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
            if (file.isIncludedPath()) {
                val license = file.toFile().parseLicense()
                require(license.isValidLicense()) {
                    """${file.toAbsolutePath()} doesn't contain a valid license: $license
                        |
                        |Please run `./gradlew utils:addLicenses` or add the file into `CheckLicenses.IGNORED_PATHS`.
                    """.trimMargin()
                }
            }
            return FileVisitResult.CONTINUE
        }

        override fun visitFileFailed(file: Path, e: IOException): FileVisitResult {
            throw e
        }

        override fun postVisitDirectory(dir: Path, e: IOException?): FileVisitResult {
            if (e != null) {
                throw e
            }
            return FileVisitResult.CONTINUE
        }
    })
}

internal fun String.isValidLicense(): Boolean {
    return trim().startsWith("Copyright") &&
        trim().endsWith("the License.") &&
        contains("https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE")
}
