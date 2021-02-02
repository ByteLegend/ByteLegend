import common.ui.bootstrap.BootstrapForm
import common.ui.bootstrap.ExtraClassAwareProps
import react.RClass
import react.RProps

val BootstrapFormRow: RClass<BootstrapFormRowProps> = BootstrapForm.asDynamic().Row

external interface BootstrapFormRowProps : RProps {
    var bsPrefix: String
}

val BootstrapFormGroup: RClass<BootstrapFormGroupProps> = BootstrapForm.asDynamic().Group

external interface BootstrapFormGroupProps : RProps {
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
