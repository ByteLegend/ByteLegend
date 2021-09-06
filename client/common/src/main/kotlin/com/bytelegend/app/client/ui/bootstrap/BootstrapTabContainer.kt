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
@file:JsModule("react-bootstrap/TabContainer")
@file:JsNonModule

// https://react-bootstrap.github.io/components/tabs/?#tab-container-props

package com.bytelegend.app.client.ui.bootstrap

import react.ElementType
import react.Props

@JsName("default")
external val BootstrapTabContainer: ElementType<BootstrapTabContainerProps>

external interface BootstrapTabContainerProps : Props {
    var defaultActiveKey: Any
    var activeKey: Any
    var generateChildId: Any
    var id: String
    var mountOnEnter: Boolean
    var onSelect: Any
    var transition: Any
    var unmountOnExit: Boolean
}
