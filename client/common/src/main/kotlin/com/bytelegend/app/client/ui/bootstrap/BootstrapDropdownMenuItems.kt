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
import com.bytelegend.app.client.ui.bootstrap.BootstrapDropdown
import org.w3c.dom.events.Event
import react.RClass
import react.RProps

val BootstrapDropdownMenu: RClass<BootstrapDropdownMenuProps> = BootstrapDropdown.asDynamic().Menu
val BootstrapDropdownItem: RClass<BootstrapDropdownItemProps> = BootstrapDropdown.asDynamic().Item
val BootstrapDropdownToggle: RClass<BootstrapDropdownToggleProps> = BootstrapDropdown.asDynamic().Toggle
val BootstrapDropdownDivider: RClass<BootstrapDropdownMenuProps> = BootstrapDropdown.asDynamic().Divider

external interface BootstrapDropdownMenuProps : RProps {
    var show: Boolean
    var style: Any
    var rootCloseEvent: String
}

external interface BootstrapDropdownDividerProps : RProps

external interface BootstrapDropdownItemProps : RProps {
    var className: String
    var style: dynamic
    var href: String
    var onClick: (Event) -> Unit
    var onSelect: (Event) -> Unit
}

external interface BootstrapDropdownToggleProps : RProps {
    var id: String
    var variant: String
    var split: Boolean
    var size: String
}
