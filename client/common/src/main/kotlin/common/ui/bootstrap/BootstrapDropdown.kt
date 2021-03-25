@file:JsModule("react-bootstrap/Dropdown")
@file:JsNonModule

package common.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapDropdown: RClass<BootstrapDropdownRProps>

external interface BootstrapDropdownRProps : RProps {
    var id: String
    var size: String
    var show: Boolean
    var rootCloseEvent: String
}

// https://react-bootstrap.github.io/components/dropdowns/
/*
<Dropdown>
  <Dropdown.Toggle variant="success" id="dropdown-basic">
    Dropdown Button
  </Dropdown.Toggle>

  <Dropdown.Menu>
    <Dropdown.Item href="#/action-1">Action</Dropdown.Item>
    <Dropdown.Item href="#/action-2">Another action</Dropdown.Item>
    <Dropdown.Item href="#/action-3">Something else</Dropdown.Item>
  </Dropdown.Menu>
</Dropdown>

<DropdownButton id="dropdown-basic-button" title="Dropdown button">
  <Dropdown.Item href="#/action-1">Action</Dropdown.Item>
  <Dropdown.Item href="#/action-2">Another action</Dropdown.Item>
  <Dropdown.Item href="#/action-3">Something else</Dropdown.Item>
</DropdownButton>
 */
