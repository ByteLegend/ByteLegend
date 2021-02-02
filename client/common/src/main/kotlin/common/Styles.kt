package common

import kotlinx.css.BorderStyle
import kotlinx.css.Color
import kotlinx.css.LinearDimension
import kotlinx.css.backgroundColor
import kotlinx.css.borderColor
import kotlinx.css.borderStyle
import kotlinx.css.borderWidth
import kotlinx.css.opacity
import kotlinx.css.padding
import kotlinx.css.px
import kotlinx.css.zIndex
import styled.StyledBuilder
import styled.css

fun StyledBuilder<*>.dashedBorderCss(width: LinearDimension = 2.px, color: Color = Color.green) {
    css {
        borderStyle = BorderStyle.dashed
        borderColor = color
        borderWidth = width
        zIndex = 6
    }
}

fun StyledBuilder<*>.selectedTileCss() {
    css {
        borderStyle = BorderStyle.dashed
        borderColor = Color.green
        borderWidth = 2.px
        zIndex = 6
    }
}

fun StyledBuilder<*>.hoveredTileCss() {
    css {
        backgroundColor = Color.gray
        opacity = 0.2
        padding = "2 px"
        zIndex = 5
    }
}
