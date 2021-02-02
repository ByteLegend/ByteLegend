@file:JsModule("react-bootstrap/ProgressBar")
@file:JsNonModule

package common.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapProgressBar: RClass<BootstrapProgressBarProps>

// https://react-bootstrap.github.io/components/progress/
external interface BootstrapProgressBarProps : RProps {
    var now: Int
    var animated: Boolean
    var variant: String
}
