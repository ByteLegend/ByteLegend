package com.bytelegend.client.app.ui

import BootstrapDropdownDivider
import BootstrapDropdownItem
import com.bytelegend.app.client.ui.bootstrap.BootstrapDropdownButton
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalHeader
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalTitle
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.i18n.PREFERRED_LOCALE_COOKIE_NAME
import kotlinx.browser.document
import kotlinx.browser.localStorage
import kotlinx.browser.window
import kotlinx.html.classes
import react.RBuilder
import react.RState
import react.dom.div
import react.dom.img
import react.dom.jsStyle
import react.dom.span

interface LocaleSelectionDropdownProps : GameProps

class LocaleSelectionDropdown : GameUIComponent<LocaleSelectionDropdownProps, RState>() {
    private fun onSwitchLocale(target: Locale) {
        localStorage.setItem("locale", target.toString())
        document.cookie = "$PREFERRED_LOCALE_COOKIE_NAME=$target;path=/;"
        window.location.reload()
    }

    @Suppress("UnsafeCastFromDynamic")
    override fun RBuilder.render() {
        div {
            attrs.classes = setOf("locale-selection-widget", "map-title-widget")
            BootstrapDropdownButton {
                attrs.id = "locale-selection"
                attrs.title = game.locale.displayName

                Locale.values().filter { it != Locale.ALL }.forEach { locale ->
                    BootstrapDropdownItem {
                        span {
                            attrs.classes = setOf("locale-selection-dropdown-item-span")
                            +locale.displayName
                        }
                        attrs.onClick = stateUpdatingEventHandler {
                            onSwitchLocale(locale)
                        }
                    }
                }

                BootstrapDropdownDivider {}

                BootstrapDropdownItem {
                    span {
                        attrs.classes = setOf("locale-selection-dropdown-item-span")
                        +i("HelpUsImproveTheTranslationQuality")
                    }
                    attrs.onClick = {
                        showHelpUsImproveModal()
                    }
                }
            }
        }
    }

    private fun showHelpUsImproveModal() {
        game.modalController.show {
            BootstrapModalHeader {
                attrs.closeButton = true
                BootstrapModalTitle {
                    +i("HelpUsImproveTheTranslationQuality")
                }
            }

            BootstrapModalBody {
                div {
                    unsafeHtml(i("HelpUsImproveTheTranslationQualityBody"))
                }

                img {
                    attrs.src = game.resolve("/img/attribution/google-translate.png")
                    attrs.jsStyle {
                        height = "16px"
                    }
                }
            }
        }
    }
}
