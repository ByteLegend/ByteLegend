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

import org.junit.jupiter.api.Assertions
import java.io.File

interface ResourceLoaderFixture {
    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun getResourceDir(path: String): File =
        File(javaClass.classLoader.getResource(path).toURI()).also {
            Assertions.assertTrue(it.isDirectory) { "$it must be directory!" }
        }

    fun getResource(path: String): File =
        File(javaClass.classLoader.getResource(path).toURI())
}
