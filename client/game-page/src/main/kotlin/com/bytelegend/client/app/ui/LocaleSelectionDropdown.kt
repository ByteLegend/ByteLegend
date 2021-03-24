package com.bytelegend.client.app.ui

import BootstrapDropdownItem
import com.bytelegend.app.shared.i18n.Locale
import common.ui.bootstrap.BootstrapDropdownButton
import kotlinx.browser.localStorage
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.html.classes
import react.RBuilder
import react.RState
import react.dom.div
import react.dom.jsStyle

interface LocaleSelectionDropdownProps : GameProps

// wait at most 500 ms then refresh the page
const val LOCALE_SWITCH_WAIT_MS = 500

class LocaleSelectionDropdown : GameUIComponent<LocaleSelectionDropdownProps, RState>() {
    private fun onSwitchLocale(target: Locale) {
        localStorage.setItem("locale", target.toString())
        val switchLocaleResult = GlobalScope.async { game.webSocketClient.switchLocale(target) }
        GlobalScope.launch {
            var counter = 0
            try {
                while (counter < LOCALE_SWITCH_WAIT_MS && !switchLocaleResult.isCompleted) {
                    counter += 100
                }
            } finally {
                window.location.reload()
            }
        }
    }

    override fun RBuilder.render() {
        div {
            attrs.classes = setOf("locale-selection-widget", "map-title-widget")
            attrs.jsStyle {
                display = "inline-block"
            }
            BootstrapDropdownButton {
                attrs.id = "locale-selection"
                attrs.title = game.locale.displayName

                Locale.values().forEach { locale ->
                    BootstrapDropdownItem {
                        +locale.displayName
                        attrs.onClick = stateUpdatingEventHandler {
                            onSwitchLocale(locale)
                        }
                    }
                }
            }
        }
    }
}
