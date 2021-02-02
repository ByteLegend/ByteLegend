package common.utils

fun limitIn(value: Int) = limitIn(value, value)

fun limitIn(value: Int, rightBorder: Int) = when {
    value < 0 -> 0
    value >= rightBorder -> rightBorder
    else -> value
}
