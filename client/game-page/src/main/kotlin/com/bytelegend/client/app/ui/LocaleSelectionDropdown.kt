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
package com.bytelegend.client.app.ui

import BootstrapDropdownDivider
import BootstrapDropdownItem
import com.bytelegend.app.client.ui.bootstrap.BootstrapDropdownButton
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalHeader
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalTitle
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.i18n.PREFERRED_LOCALE_COOKIE_NAME
import com.bytelegend.client.utils.jsObjectBackedSetOf
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
            attrs.classes = jsObjectBackedSetOf("locale-selection-widget", "map-title-widget")
            BootstrapDropdownButton {
                attrs.id = "locale-selection"
                attrs.title = game.locale.displayName

                Locale.values().filter { it != Locale.ALL }.forEach { locale ->
                    BootstrapDropdownItem {
                        span {
                            attrs.classes = jsObjectBackedSetOf("locale-selection-dropdown-item-span")
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
                        attrs.classes = jsObjectBackedSetOf("locale-selection-dropdown-item-span")
                        +i("AddANewLanguage")
                    }
                    attrs.onClick = {
                        showHelpUsImproveModal()
                    }
                }

                BootstrapDropdownItem {
                    span {
                        attrs.classes = jsObjectBackedSetOf("locale-selection-dropdown-item-span")
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
                    unsafeSpan(i("HelpUsImproveTheTranslationQualityBody"))
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
