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
import csstype.ClassName
import kotlinx.browser.document
import kotlinx.browser.localStorage
import kotlinx.browser.window
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.span

interface LocaleSelectionDropdownProps : GameProps

class LocaleSelectionDropdown : GameUIComponent<LocaleSelectionDropdownProps, State>() {
    private fun onSwitchLocale(target: Locale) {
        localStorage.setItem("locale", target.toString())
        document.cookie = "$PREFERRED_LOCALE_COOKIE_NAME=$target;path=/;"
        window.location.reload()
    }

    @Suppress("UnsafeCastFromDynamic")
    override fun render() = Fragment.create {
        div {
            className = ClassName("locale-selection-widget map-title-widget")
            BootstrapDropdownButton {
                id = "locale-selection"
                title = game.locale.displayName

                Locale.values().filter { it != Locale.ALL }.forEach { locale ->
                    BootstrapDropdownItem {
                        span {
                            className = ClassName("locale-selection-dropdown-item-span")
                            +locale.displayName
                        }
                        onClick = stateUpdatingEventHandler {
                            onSwitchLocale(locale)
                        }
                    }
                }

                BootstrapDropdownDivider {}

                BootstrapDropdownItem {
                    span {
                        className = ClassName("locale-selection-dropdown-item-span")
                        +i("AddANewLanguage")
                    }
                    onClick = {
                        showHelpUsImproveModal()
                    }
                }

                BootstrapDropdownItem {
                    span {
                        className = ClassName("locale-selection-dropdown-item-span")
                        +i("HelpUsImproveTheTranslationQuality")
                    }
                    onClick = {
                        showHelpUsImproveModal()
                    }
                }
            }
        }
    }

    private fun showHelpUsImproveModal() {
        game.modalController.show {
            BootstrapModalHeader {
                closeButton = true
                BootstrapModalTitle {
                    +i("HelpUsImproveTheTranslationQuality")
                }
            }

            BootstrapModalBody {
                div {
                    unsafeSpan(i("HelpUsImproveTheTranslationQualityBody"))
                }

                img {
                    src = game.resolve("/img/attribution/google-translate.png")
                    jsStyle {
                        this.height = "16px"
                    }
                }
            }
        }
    }
}
