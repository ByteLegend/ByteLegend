@file:JsModule("react-transition-group")
@file:JsNonModule

package common.ui

import react.RClass
import react.RProps

external val CSSTransition: RClass<CSSTransitionProps>
external val TransitionGroup: RClass<TransitionGroupProps>

external interface TransitionGroupProps : RProps {
    var className: String
}

external interface CSSTransitionProps : RProps {
    var classNames: String
    var timeout: Int
    var key: String
}
