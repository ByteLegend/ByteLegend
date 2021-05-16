package com.bytelegend.client.app.ui

import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import kotlinx.css.Position
import kotlinx.css.height
import kotlinx.css.left
import kotlinx.css.position
import kotlinx.css.px
import kotlinx.css.top
import kotlinx.css.width
import kotlinx.html.DIV
import kotlinx.html.IMG
import kotlinx.html.SPAN
import kotlinx.html.classes
import react.Component
import react.RBuilder
import react.RElementBuilder
import react.RHandler
import react.ReactElement
import react.dom.RDOMBuilder
import react.dom.div
import react.dom.img
import react.dom.jsStyle
import react.dom.span
import styled.StyledBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import kotlin.reflect.KClass

fun StyledBuilder<*>.absolutePosition(left: Int, top: Int, width: Int, height: Int) {
    css {
        position = Position.absolute
        this.top = top.px
        this.left = left.px
        this.width = width.px
        this.height = height.px
    }
}

fun RBuilder.absoluteStyledDiv(coordinate: PixelCoordinate, size: PixelSize, block: StyledDOMBuilder<DIV>.() -> Unit) {
    absoluteStyledDiv(coordinate.x, coordinate.y, size.width, size.height, block)
}

fun RBuilder.absoluteStyledDiv(left: Int = 0, top: Int = 0, size: PixelSize, block: StyledDOMBuilder<DIV>.() -> Unit) {
    absoluteStyledDiv(left, top, size.width, size.height, block)
}

fun RBuilder.absoluteStyledDiv(left: Int = 0, top: Int = 0, width: Int, height: Int, block: StyledDOMBuilder<DIV>.() -> Unit) {
    styledDiv {
        absolutePosition(left, top, width, height)
        block()
    }
}

inline fun <T : Any> jsObject(builder1: T.() -> Unit, builder2: T.() -> Unit): T =
    kotlinext.js.jsObject<T>().apply(builder1).apply(builder2)

fun js(builder1: dynamic.() -> Unit, builder2: dynamic.() -> Unit): dynamic = jsObject(builder1, builder2)

@Suppress("UnsafeCastFromDynamic")
fun RBuilder.absoluteDiv(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null,
    width: Int? = null,
    height: Int? = null,
    zIndex: Int = 0,
    opacity: String = "1",
    classes: Set<String> = emptySet(),
    extraStyleBuilder: dynamic.() -> Unit = {},
    block: RDOMBuilder<DIV>.() -> Unit = {}
) {
    div {
        attrs {
            this.classes = classes

            jsStyle {
                position = "absolute"
                if (left != null) {
                    this.left = "${left}px"
                }
                if (right != null) {
                    this.right = "${right}px"
                }
                if (top != null) {
                    this.top = "${top}px"
                }
                if (bottom != null) {
                    this.bottom = "${bottom}px"
                }
                if (width != null) {
                    this.width = "${width}px"
                }
                if (height != null) {
                    this.height = "${height}px"
                }
                this.zIndex = zIndex.toString()
                this.opacity = opacity
            }
            jsStyle(extraStyleBuilder)
            block()
        }
    }
}

@Suppress("UnsafeCastFromDynamic")
fun RBuilder.absoluteSpan(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null,
    width: Int? = null,
    height: Int? = null,
    zIndex: Int = 0,
    opacity: String = "1",
    classes: Set<String> = emptySet(),
    extraStyleBuilder: dynamic.() -> Unit = {},
    content: String = "UNDEFINED",
    block: RDOMBuilder<SPAN>.() -> Unit = {}
) {
    span {
        attrs {
            this.classes = classes

            jsStyle {
                position = "absolute"
                if (left != null) {
                    this.left = "${left}px"
                }
                if (right != null) {
                    this.right = "${right}px"
                }
                if (top != null) {
                    this.top = "${top}px"
                }
                if (bottom != null) {
                    this.bottom = "${bottom}px"
                }
                if (width != null) {
                    this.width = "${width}px"
                }
                if (height != null) {
                    this.height = "${height}px"
                }
                this.zIndex = zIndex.toString()
                this.opacity = opacity
            }
            jsStyle(extraStyleBuilder)
            +content

            block()
        }
    }
}

@Suppress("UnsafeCastFromDynamic")
fun RBuilder.absoluteImg(
    url: String,
    left: Int,
    top: Int,
    width: Int? = null,
    height: Int? = null,
    zIndex: Int = 0,
    opacity: String = "1",
    classes: Set<String> = emptySet(),
    extraStyleBuilder: dynamic.() -> Unit = {},
    block: RDOMBuilder<IMG>.() -> Unit = {}
) {
    img {
        attrs {
            src = url
            this.classes = classes

            jsStyle {
                position = "absolute"
                this.left = "${left}px"
                this.top = "${top}px"
                if (width != null) {
                    this.width = "${width}px"
                }
                if (height != null) {
                    this.height = "${height}px"
                }
                this.zIndex = zIndex.toString()
                this.opacity = opacity
            }
            jsStyle(extraStyleBuilder)
            block()
        }
    }
}

fun StyledDOMBuilder<DIV>.pictureFrameBordered() {
    attrs {
        classes = classes.toMutableSet().apply { add("picture-frame-border") }
    }
}

fun <PARENT : GameProps, CHILD : GameProps> RElementBuilder<PARENT>.gameChild(
    props: PARENT,
    klazz: KClass<out Component<CHILD, *>>,
    handler: RHandler<CHILD> = {}
): ReactElement {
    return child(klazz) {
        attrs.game = props.game
        handler()
    }
}

fun RBuilder.unsafeHtml(html: String, vararg classes: String) {
    span {
        attrs.classes = setOf(*classes)
        consumer.onTagContentUnsafe {
            +html
        }
    }
}
