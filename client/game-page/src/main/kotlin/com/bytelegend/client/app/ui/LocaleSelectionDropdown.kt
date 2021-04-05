package com.bytelegend.client.app.ui

import BootstrapDropdownItem
import com.bytelegend.app.client.ui.bootstrap.BootstrapDropdownButton
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalHeader
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalTitle
import com.bytelegend.app.client.ui.icons.aiOutlineGlobal
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.i18n.PREFERRED_LOCALE_COOKIE_NAME
import com.bytelegend.client.app.page.GAME_INIT_DATA
import kotlinx.browser.document
import kotlinx.browser.localStorage
import kotlinx.browser.window
import kotlinx.html.classes
import org.w3c.dom.events.Event
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

                Locale.values().forEach { locale ->
                    BootstrapDropdownItem {
                        span {
                            attrs.classes = setOf("locale-selection-dropdown-item-span")
                            +locale.displayName
                        }

                        if (locale.byMachine) {
                            aiOutlineGlobal {
                                attrs.className = "locale-selection-dropdown-item-global-svg"
                                attrs.title = "Contribute"
                                attrs.onClick = { event: Event ->
                                    event.stopPropagation()
                                    showHelpUsImproveModal(locale)
                                }
                            }
                        }
                        attrs.onClick = stateUpdatingEventHandler {
                            onSwitchLocale(locale)
                        }
                    }
                }
            }
        }
    }

    private fun showHelpUsImproveModal(selectedLocale: Locale) {
        game.modalController.show {
            BootstrapModalHeader {
                attrs.closeButton = true
                BootstrapModalTitle {
                    +GAME_INIT_DATA.getI18nText("HelpUsImproveTheTranslationQuality", selectedLocale)
                }
            }

            BootstrapModalBody {
                div {
                    consumer.onTagContentUnsafe {
                        +GAME_INIT_DATA.getI18nText("HelpUsImproveTheTranslationQualityBody", selectedLocale)
                    }
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
