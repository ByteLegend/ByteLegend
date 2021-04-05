@file:JsModule("react-bootstrap/Form")
@file:JsNonModule

package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapForm: RClass<BootstrapFormProps>

// https://react-bootstrap.github.io/components/forms/#form-props
external interface BootstrapFormProps : RProps {
    var inline: Boolean
    var validated: Boolean
    var bsPrefix: String
}
