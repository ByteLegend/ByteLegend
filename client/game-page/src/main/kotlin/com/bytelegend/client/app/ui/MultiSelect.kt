@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.app.ui

import com.bytelegend.client.app.external.ReactSelect
import com.bytelegend.client.app.external.ReactSelectProps
import kotlinext.js.clone
import kotlinext.js.jsObject
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.setState

data class Option(
    /**
     * The value for machine
     */
    val value: String,
    /**
     * The value name displayed for human beings
     */
    val label: String
) {
    constructor(jsObj: dynamic) : this(jsObj.value, jsObj.label)

    fun toJsObject(): dynamic = jsObject {
        this.value = this@Option.value
        this.label = this@Option.label
    }
}

interface MultiSelectProps : RProps {
    var className: String
    var initOptions: List<Option>
    var allOptions: List<Option>

    /**
     * User select multiple values and close the dropdown
     */
    var onSelectComplete: (List<Option>) -> Unit
    var configuration: ReactSelectProps.() -> Unit
}

interface MultiSelectState : RState {
    var selectedOptions: List<Option>
}

class MultiSelect(props: MultiSelectProps) : RComponent<MultiSelectProps, MultiSelectState>(props) {
    override fun MultiSelectState.init(props: MultiSelectProps) {
        selectedOptions = props.initOptions
    }

    override fun RBuilder.render() {
        ReactSelect {
            if (props.configuration != undefined) {
                props.configuration.invoke(attrs)
            }
            attrs.className = "${props.className ?: ""} mission-modal-tutorial-filter"
            attrs.options = props.allOptions.map { it.toJsObject() }.toTypedArray()
            attrs.value = state.selectedOptions.map { it.toJsObject() }.toTypedArray()

            attrs.onChange = { it: Array<dynamic> ->
                setState { selectedOptions = it.map { Option(it) }.distinct() }
            }
            attrs.isMulti = true
            attrs.closeMenuOnSelect = false
            attrs.onMenuClose = {
                props.onSelectComplete(state.selectedOptions.map { Option(it) })
            }
            attrs.styles = jsObject<dynamic> {
                container = { provided: dynamic, _: dynamic ->
                    val ret: dynamic = clone(provided)
                    ret.width = "${(this@MultiSelect.state.selectedOptions.size + 1) * 100}px"
                    ret
                }
            }
        }
    }
}
