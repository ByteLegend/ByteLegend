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

import org.junit.jupiter.api.extension.AfterTestExecutionCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.BrowserWebDriverContainer
import org.testcontainers.lifecycle.TestDescription
import kotlin.reflect.full.memberProperties

/**
 * This is a workaround for
 * https://github.com/testcontainers/testcontainers-java/issues/1341
 *
 * The annotated class must have an instance field
 * BrowserWebDriverContainer browswerWebDriverContainer or
 * List[BrowserWebDriverContainer] browserWebDriverContainers
 *
 */
@Suppress("UNCHECKED_CAST")
class JUnit5VncRecorder : AfterTestExecutionCallback {
    private fun getBrowserContainers(testInstance: Any): List<BrowserWebDriverContainer<Nothing>> {
        val field = testInstance.javaClass.kotlin.memberProperties
            .firstOrNull { it.name == "browserWebDriverContainer" }
        return if (field == null) {
            testInstance.javaClass.kotlin.memberProperties
                .first { it.name == "browserWebDriverContainers" }
                .getter
                .call(testInstance) as List<BrowserWebDriverContainer<Nothing>>
        } else {
            listOf(field.getter.call(testInstance) as BrowserWebDriverContainer<Nothing>)
        }
    }

    private fun toTestDescription(context: ExtensionContext): TestDescription {
        return object : TestDescription {
            override fun getTestId() = context.displayName

            override fun getFilesystemFriendlyName(): String {
                return "${context.testInstance.get().javaClass.simpleName}-${context.testMethod.get().name.replace(' ', '-')}"
            }
        }
    }

    override fun afterTestExecution(context: ExtensionContext) {
        getBrowserContainers(context.testInstance.get()).forEach {
            try {
                it.afterTest(toTestDescription(context), context.executionException)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}
