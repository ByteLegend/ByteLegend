package com.bytelegend.client.app.ui

import BootstrapDropdownItem
import com.bytelegend.app.shared.i18n.Locale
import common.ui.bootstrap.BootstrapDropdownButton
import kotlinx.browser.localStorage
import kotlinx.browser.window
import react.RBuilder
import react.RState

interface LocaleSelectionDropdownProps : GameProps

class LocaleSelectionDropdown : GameUIComponent<LocaleSelectionDropdownProps, RState>() {
    private fun onSwitchLocale(target: Locale) {
        localStorage.setItem("locale", target.toString())
        window.location.reload()
    }

    override fun RBuilder.render() {
        absoluteDiv(
            uiContainerCoordinateInGameContainer.x + uiContainerSize.width - 175,
            uiContainerCoordinateInGameContainer.y,
            AVATAR_WIDTH,
            AVATAR_HEIGHT / 2,
            Layer.LocaleSelectionDropdown.zIndex(),
            classes = setOf("locale-selection-widget")
        ) {
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
