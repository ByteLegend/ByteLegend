package com.bytelegend.client.app.ui

import com.bytelegend.app.shared.PixelSize
import com.bytelegend.client.app.engine.util.jsObjectBackedSetOf
import kotlinx.html.DIV
import kotlinx.html.classes
import react.Component
import react.RBuilder
import react.RElementBuilder
import react.RHandler
import react.ReactElement
import react.dom.RDOMBuilder
import react.dom.attrs
import react.dom.div
import react.dom.h3
import react.dom.h4
import react.dom.img
import react.dom.jsStyle
import react.dom.span
import kotlin.reflect.KClass

inline fun <T : Any> jsObject(builder1: T.() -> Unit, builder2: T.() -> Unit): T =
    kotlinext.js.jsObject<T>().apply(builder1).apply(builder2)

fun js(builder1: dynamic.() -> Unit, builder2: dynamic.() -> Unit): dynamic = jsObject(builder1, builder2)

fun RBuilder.icon(srcData: String, size: PixelSize = PixelSize(24, 24)) {
    img {
        attrs.jsStyle {
            width = "${size.width}px"
            height = "${size.height}px"
            display = "inline-block"
        }
        attrs.src = srcData
    }
}

@Suppress("UnsafeCastFromDynamic")
fun RBuilder.absoluteDiv(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null,
    width: Int? = null,
    height: Int? = null,
    zIndex: Int? = null,
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
                if (zIndex != null) {
                    this.zIndex = zIndex.toString()
                }
                this.opacity = opacity
            }
            jsStyle(extraStyleBuilder)
            block()
        }
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

fun RBuilder.unsafeSpan(html: String, vararg classes: String) {
    span {
        attrs.classes = jsObjectBackedSetOf(*classes)
        consumer.onTagContentUnsafe {
            +html
        }
    }
}

fun RBuilder.unsafeDiv(html: String) {
    div {
        consumer.onTagContentUnsafe {
            +html
        }
    }
}

fun RBuilder.unsafeH3(html: String) {
    h3 {
        consumer.onTagContentUnsafe {
            +html
        }
    }
}

fun RBuilder.unsafeH4(html: String) {
    h4 {
        consumer.onTagContentUnsafe {
            +html
        }
    }
}
