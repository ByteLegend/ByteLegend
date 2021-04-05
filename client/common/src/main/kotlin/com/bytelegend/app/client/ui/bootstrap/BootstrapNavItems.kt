import com.bytelegend.app.client.ui.bootstrap.BootstrapNav
import react.RClass
import react.RProps

val BootstrapNavItem: RClass<BootstrapNavItemProps> = BootstrapNav.asDynamic().Item
val BootstrapNavLink: RClass<BootstrapNavLinkProps> = BootstrapNav.asDynamic().Link

external interface BootstrapNavItemProps : RProps {
    var `as`: Any
    var role: String
    var bsPrefix: String
}

external interface BootstrapNavLinkProps : RProps {
    var active: Boolean
    var href: String
    var `as`: Any
    var eventKey: String
    var onSelect: Any
}
