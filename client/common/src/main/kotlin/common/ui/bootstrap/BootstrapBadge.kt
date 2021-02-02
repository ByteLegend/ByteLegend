@file:JsModule("react-bootstrap/Badge")
@file:JsNonModule

package common.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapBadge: RClass<BootstrapBadgeProps>

// https://react-bootstrap.github.io/components/badge/
external interface BootstrapBadgeProps : RProps {
    var pill: Boolean
    // 'primary' | 'secondary' | 'success' | 'danger' | 'warning' | 'info' | 'light' | 'dark'
    var variant: String
}
