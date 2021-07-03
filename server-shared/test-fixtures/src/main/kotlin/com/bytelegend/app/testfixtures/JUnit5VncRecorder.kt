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
