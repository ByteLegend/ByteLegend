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

import com.bytelegend.app.client.ui.bootstrap.BootstrapSpinner
import csstype.ClassName
import csstype.px
import kotlinext.js.assign
import kotlinx.js.jso
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import react.ChildrenBuilder
import react.Component
import react.ComponentClass
import react.Fragment
import react.State
import react.create
import react.dom.html.HTMLAttributes
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.h4
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.span

inline fun <T : Any> jso(builder1: T.() -> Unit, builder2: T.() -> Unit): T =
    jso<T>().apply(builder1).apply(builder2)

fun js(builder1: dynamic.() -> Unit, builder2: dynamic.() -> Unit): dynamic = jso(builder1, builder2)

fun ChildrenBuilder.icon(
    size: Int = 16,
    className: String,
    config: HTMLAttributes<HTMLDivElement>.() -> Unit = {}
) {
    div {
        this.className = ClassName("inline-icon $className")
        style = jso {
            width = size.px
            height = size.px
        }
        config()
    }
}

fun <S : State> Component<*, S>.setState(buildState: S.() -> Unit) {
    setState({ assign(it, buildState) })
}

fun <T : HTMLElement> HTMLAttributes<T>.jsStyle(block: dynamic.() -> Unit) {
    if (style == null) {
        style = jso(block)
    } else {
        style.apply(block)
    }
}

fun <T : HTMLElement> HTMLAttributes<T>.setJsStyle(obj: dynamic) {
    style = obj
}

@Suppress("UnsafeCastFromDynamic")
fun ChildrenBuilder.absoluteDiv(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null,
    width: Int? = null,
    height: Int? = null,
    zIndex: Int? = null,
    opacity: Double = 1.0,
    className: String = "",
    extraStyleBuilder: dynamic.() -> Unit = {},
    block: ChildrenBuilder.(HTMLAttributes<HTMLDivElement>) -> Unit = {}
) {
    div {
        this.className = ClassName(className)
        this.style = jso<dynamic> {
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
                this.zIndex = zIndex
            }
            this.opacity = opacity
        }

        style.apply(extraStyleBuilder)
        block(this)
    }
}

fun <PARENT : GameProps, CHILD : GameProps> gameChild(
    props: PARENT,
    klazz: ComponentClass<CHILD>,
    block: CHILD.() -> Unit = {}
) {
    val newChild = Fragment.create {
        child(klazz, jso {
            this.game = props.game
            block()
        })
    }
    if (props.children == null) {
        props.children = arrayOf(newChild).asDynamic()
    } else {
        props.children.asDynamic().push(newChild)
    }
}

fun ChildrenBuilder.unsafeP(html: String, className: String = "") {
    p {
        this.className = ClassName(className)
        dangerouslySetInnerHTML = jso {
            __html = html
        }
    }
}

fun ChildrenBuilder.unsafeSpan(html: String, className: String = "") {
    span {
        this.className = ClassName(className)
        dangerouslySetInnerHTML = jso {
            __html = html
        }
    }
}

fun ChildrenBuilder.unsafeDiv(html: String, config: HTMLAttributes<HTMLDivElement>.() -> Unit = {}) {
    div {
        config()
        dangerouslySetInnerHTML = jso {
            __html = html
        }
    }
}

fun ChildrenBuilder.unsafeH3(html: String) {
    h3 {
        dangerouslySetInnerHTML = jso {
            __html = html
        }
    }
}

fun ChildrenBuilder.unsafeH4(html: String) {
    h4 {
        dangerouslySetInnerHTML = jso {
            __html = html
        }
    }
}

fun ChildrenBuilder.loadingSpinner() {
    BootstrapSpinner {
        animation = "border"
    }
}
