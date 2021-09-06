/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bytelegend.client.app.ui

import com.bytelegend.app.shared.PixelSize
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.html.DIV
import kotlinx.html.IMG
import kotlinx.html.classes
import react.Component
import react.RBuilder
import react.RElementBuilder
import react.RHandler
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

fun RBuilder.icon(
    srcData: String,
    size: PixelSize = PixelSize(24, 24),
    config: RDOMBuilder<IMG>.() -> Unit = {}
) {
    img {
        attrs.jsStyle {
            width = "${size.width}px"
            height = "${size.height}px"
            display = "inline-block"
        }
        attrs.src = srcData
        config()
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
) {
    child(klazz) {
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
