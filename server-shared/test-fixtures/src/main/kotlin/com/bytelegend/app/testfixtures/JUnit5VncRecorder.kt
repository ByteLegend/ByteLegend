package com.bytelegend.app.browsertest

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestWatcher
import org.testcontainers.containers.BrowserWebDriverContainer
import org.testcontainers.lifecycle.TestDescription
import java.util.Optional
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.memberProperties

/**
 * This is a workaround for
 * https://github.com/testcontainers/testcontainers-java/issues/1341
 *
 * The annotated class must have a public static field
 * List[BrowserWebDriverContainer] browserWebDriverContainers
 */
@Suppress("UNCHECKED_CAST")
class JUnit5VncRecorder : TestWatcher {
    private fun getBrowserContainers(testClass: Class<*>): List<BrowserWebDriverContainer<Nothing>> {
        try {
            return testClass.getField("browserWebDriverContainers").get(null) as List<BrowserWebDriverContainer<Nothing>>
        } catch (e: NoSuchFieldException) {
            // Try Kotlin reflection on companion object
            val companionObjectClass = testClass.kotlin.companionObject!!
            val companionObjectInstance = testClass.kotlin.companionObjectInstance
            return companionObjectClass.memberProperties
                .first { it.name == "browserWebDriverContainers" }
                .getter
                .call(companionObjectInstance) as List<BrowserWebDriverContainer<Nothing>>
        }
    }

    override fun testSuccessful(context: ExtensionContext) {
        try {
            getBrowserContainers(context.testClass.get()).forEach {
                it.afterTest(toTestDescription(context), Optional.empty())
                it.close()
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    override fun testFailed(context: ExtensionContext, cause: Throwable) {
        try {
            getBrowserContainers(context.testClass.get()).forEach {
                it.afterTest(toTestDescription(context), Optional.of(cause))
                it.close()
            }
        } catch (e: Throwable) {
            e.printStackTrace()
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
}
