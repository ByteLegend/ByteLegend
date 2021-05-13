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
            LogLevel.valueOf(params.get("logLevel")!!.toLowerCase().capitalize())
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
        }
    }
}
