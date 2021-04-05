package com.bytelegend.app.client.ui.icons

import react.RProps

interface IconProps : RProps {
    var onClick: Any
    var style: dynamic
    var size: Int
    var className: String
    var title: String
}
