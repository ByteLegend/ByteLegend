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
package com.bytelegend.app.client.ui.bootstrap

import react.Props

// https://react-bootstrap.github.io/components/buttons/
interface BootstrapButtonProps : Props {
    var active: Boolean
    var className: String
    var disabled: Boolean
    var href: String

    // 'sm' | 'lg'
    var size: String

    // 'button' | 'reset' | 'submit' | null
    var type: String

    /*
    One or more button variant combinations

buttons may be one of a variety of visual variants such as:

'primary', 'secondary', 'success', 'danger', 'warning', 'info', 'dark', 'light', 'link'

as well as "outline" versions (prefixed by 'outline-*')

'outline-primary', 'outline-secondary', 'outline-success', 'outline-danger', 'outline-warning', 'outline-info', 'outline-dark', 'outline-light'
     */
    var variant: String
    var onClick: Any
    var onMouseOver: Any
    var onMouseOut: Any
    var style: Any
    var title: String
}
