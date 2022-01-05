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
@file:JsModule("react-select")
@file:JsNonModule

package com.bytelegend.client.app.external

import react.ElementType
import react.Props

@JsName("default")
external val ReactSelect: ElementType<ReactSelectProps>

external interface ReactSelectProps : Props {
    var id: String
    var className: String
    var options: Any
    var value: Any
    var onChange: Any
    var isMulti: Boolean
    var isSearchable: Boolean
    var isDisabled: Boolean
    var closeMenuOnSelect: Boolean
    var defaultMenuIsOpen: Boolean

    var onBlur: Any
    // https://react-select.com/styles
    var styles: Any
    var onMenuOpen: Any
    var onMenuClose: Any
}
