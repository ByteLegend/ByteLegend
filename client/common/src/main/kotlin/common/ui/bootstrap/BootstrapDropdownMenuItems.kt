import common.ui.bootstrap.BootstrapDropdown
import org.w3c.dom.events.Event
import react.RClass
import react.RProps

val BootstrapDropdownMenu: RClass<BootstrapDropdownMenuProps> = BootstrapDropdown.asDynamic().Menu
val BootstrapDropdownItem: RClass<BootstrapDropdownItemProps> = BootstrapDropdown.asDynamic().Item
val BootstrapDropdownToggle: RClass<BootstrapDropdownToggleProps> = BootstrapDropdown.asDynamic().Toggle
val BootstrapDropdownDivider: RClass<BootstrapDropdownMenuProps> = BootstrapDropdown.asDynamic().Divider

external interface BootstrapDropdownMenuProps : RProps

external interface BootstrapDropdownDividerProps : RProps

external interface BootstrapDropdownItemProps : RProps {
    var className: String
    var href: String
    var onClick: (Event) -> Unit
    var onSelect: (Event) -> Unit
}

external interface BootstrapDropdownToggleProps : RProps {
    var id: String
    var variant: String
}
