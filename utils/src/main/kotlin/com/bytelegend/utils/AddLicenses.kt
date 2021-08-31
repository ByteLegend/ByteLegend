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

import java.io.File
import java.io.IOException
import java.nio.file.FileVisitResult
import java.nio.file.FileVisitor
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.attribute.BasicFileAttributes

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
                require(license.isValidLicense() || license.isBlank()) {
                    "${file.toAbsolutePath()} license: $license"
                }
                if (license.isBlank()) {
                    file.toFile().prependLicense()
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
