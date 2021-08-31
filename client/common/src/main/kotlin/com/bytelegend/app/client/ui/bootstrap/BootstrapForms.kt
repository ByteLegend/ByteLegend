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
import com.bytelegend.app.client.ui.bootstrap.BootstrapForm
import com.bytelegend.app.client.ui.bootstrap.ExtraClassAwareProps
import react.RClass
import react.RProps

val BootstrapFormRow: RClass<BootstrapFormRowProps> = BootstrapForm.asDynamic().Row

external interface BootstrapFormRowProps : RProps {
    var bsPrefix: String
}

val BootstrapFormGroup: RClass<BootstrapFormGroupProps> = BootstrapForm.asDynamic().Group

external interface BootstrapFormGroupProps : RProps {
    var `as`: Any
    var controlId: String
    var bsPrefix: String
}

val BootstrapFormLabel: RClass<BootstrapFormLabelProps> = BootstrapForm.asDynamic().Label

external interface BootstrapFormLabelProps : RProps {
    // boolean | 'sm' | 'lg'
    var column: Any
    var htmlFor: String
    var scOnly: Boolean
    var bsPrefix: String
}

val BootstrapFormText: RClass<BootstrapFormTextProps> = BootstrapForm.asDynamic().Text

external interface BootstrapFormTextProps : RProps {
    var muted: Boolean
    var bsPrefix: String
}

val BootstrapFormCheck: RClass<BootstrapFormCheckProps> = BootstrapForm.asDynamic().Check

external interface BootstrapFormCheckProps : ExtraClassAwareProps {
    var disabled: Boolean
    var inline: Boolean
    var type: String
    var bsPrefix: String
    var label: Any
    var onChange: Any
    var checked: Boolean
}

val BootstrapFormDotControl: RClass<BootstrapFormDotControlProps> = BootstrapForm.asDynamic().Control

external interface BootstrapFormDotControlProps : RProps
