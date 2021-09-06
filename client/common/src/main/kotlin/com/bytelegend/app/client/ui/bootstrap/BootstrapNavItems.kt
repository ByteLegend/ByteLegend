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
import com.bytelegend.app.client.ui.bootstrap.BootstrapNav
import react.ElementType
import react.Props

val BootstrapNavItem: ElementType<BootstrapNavItemProps> = BootstrapNav.asDynamic().Item
val BootstrapNavLink: ElementType<BootstrapNavLinkProps> = BootstrapNav.asDynamic().Link

external interface BootstrapNavItemProps : Props {
    var `as`: Any
    var role: String
    var bsPrefix: String
}

external interface BootstrapNavLinkProps : Props {
    var active: Boolean
    var href: String
    var `as`: Any
    var eventKey: String
    var onSelect: Any
}
