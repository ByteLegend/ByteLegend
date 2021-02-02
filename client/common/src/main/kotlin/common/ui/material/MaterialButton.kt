@file:JsModule("@material-ui/core/Button")
@file:JsNonModule

package common.ui.material

import react.RClass
import react.RProps

@JsName("default")
external val MaterialButton: RClass<MaterialButtonProps>

// https://material-ui.com/api/button/
external interface MaterialButtonProps : RProps {
    var active: Boolean
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
}
