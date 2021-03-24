package com.bytelegend.client.app.ui

import BootstrapDropdownItem
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.i18n.PREFERRED_LOCALE_COOKIE_NAME
import common.ui.bootstrap.BootstrapDropdownButton
import kotlinx.browser.document
import kotlinx.browser.localStorage
import kotlinx.browser.window
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
        document.cookie = "$PREFERRED_LOCALE_COOKIE_NAME=$target;path=/;"
        window.location.reload()
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
