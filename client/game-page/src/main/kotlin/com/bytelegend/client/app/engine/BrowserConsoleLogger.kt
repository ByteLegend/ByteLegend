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
package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.LogLevel
import com.bytelegend.app.client.api.Logger
import kotlinx.browser.window
import org.w3c.dom.url.URLSearchParams

val DEFAULT_LOG_LEVEL = LogLevel.Warn

object BrowserConsoleLogger : Logger {
    override val logLevel: LogLevel by lazy {
        try {
            val params = URLSearchParams(window.location.search)
            LogLevel.valueOf(params.get("logLevel")!!.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() })
        } catch (e: Exception) {
            DEFAULT_LOG_LEVEL
        }
    }
    override val debugEnabled: Boolean = logLevel <= LogLevel.Debug
    override val infoEnabled: Boolean = logLevel <= LogLevel.Info
    override val warnEnabled: Boolean = logLevel <= LogLevel.Warn
    override val errorEnabled: Boolean = logLevel <= LogLevel.Error

    override fun debug(message: String) {
        if (debugEnabled) {
            console.log(message)
        }
    }

    override fun info(message: String) {
        if (infoEnabled) {
            console.info(message)
        }
    }

    override fun warn(message: String) {
        if (warnEnabled) {
            console.warn(message)
        }
    }

    override fun error(message: String) {
        if (errorEnabled) {
            console.error(message)
            console.asDynamic().trace()
        }
    }
}
