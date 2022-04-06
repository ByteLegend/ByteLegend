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
@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.app.script.effect

import com.bytelegend.app.client.api.GameCanvasState
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.client.misc.playAudio
import com.bytelegend.app.client.misc.uuid
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.client.app.external.confetti
import com.bytelegend.client.app.ui.Layer
import kotlinext.js.assign
import kotlinx.js.jso
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.delay
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.Node
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.js.Date
import kotlin.math.floor
import kotlin.random.Random

val gsap
    get() = window.asDynamic().gsap

// very high to be on top of modal
const val EFFECT_Z_INDEX = 2000

private fun fire(particleRatio: Double, origin: dynamic, opts: dynamic) {
    val count = 200
    val particleCount = floor(count * particleRatio)
    val z = EFFECT_Z_INDEX
    val options = assign(opts) {
        this.origin = origin
        this.particleCount = particleCount
        this.zIndex = z
    }
    confetti(options)
}

fun showConfetti(canvasState: GameCanvasState, originPointOnMap: GridCoordinate) {
    val originPixelCoordinate = originPointOnMap * canvasState.tileSize - canvasState.getCanvasCoordinateInMap() + canvasState.getCanvasCoordinateInGameContainer()
    val originX = 1.0 * originPixelCoordinate.x / canvasState.gameContainerSize.width
    val originY = 1.0 * originPixelCoordinate.y / canvasState.gameContainerSize.height
    if (originX < 0 || originY < 0) {
        return
    }
    val origin = jso<dynamic> {
        x = originX
        y = originY
    }
    val angle = when {
        originX < 0.5 -> Random.nextInt(60, 90)
        else -> Random.nextInt(90, 120)
    }
    playAudio("tada")

    // https://www.kirilv.com/canvas-confetti/#realistic
    fire(0.25, origin, jso {
        this.angle = angle
        spread = 26
        startVelocity = 55
    })
    fire(0.2, origin, jso {
        this.angle = angle
        spread = 60
    })
    fire(0.35, origin, jso {
        this.angle = angle
        spread = 100
        decay = 0.91
        scalar = 0.8
    })
    fire(0.1, origin, jso {
        this.angle = angle
        spread = 120
        startVelocity = 25
        decay = 0.92
        scalar = 1.2
    })
    fire(0.1, origin, jso {
        this.angle = angle
        spread = 120
        startVelocity = 45
    })
}

suspend fun fadeInEffect(gameContainerSize: PixelSize): Unit = suspendCoroutine { continuation ->
    val id = "fadeIn-${Date().getTime().toLong()}"
    val fadeInLayer = createFullscreenDiv(id, Layer.FadeInFadeOut.zIndex(), gameContainerSize) {
        style.backgroundColor = "black"
        className = "fade-in-layer"
    }
    gsap.fromTo(
        "#$id",
        jso {
            autoAlpha = 1
        },
        jso {
            duration = 1
            autoAlpha = 0
            onComplete = {
                document.body?.removeChild(fadeInLayer)
                continuation.resume(Unit)
            }
        }
    )
}

fun <T : Element> Document.createAndAppend(tagName: String, configure: T.() -> Unit): T {
    val tag: T = createElement(tagName).asDynamic()
    tag.configure()
    body?.appendChild(tag)
    return tag
}

fun <T : Element> Document.create(tagName: String, configure: T.() -> Unit): T {
    val tag: T = createElement(tagName).asDynamic()
    tag.configure()
    return tag
}

@Suppress("UNUSED_PARAMETER")
suspend fun numberIncrementEffect(
    incrementAnimationDiv: Node,
    left: Int,
    top: Int,
    width: Int,
    height: Int
): Unit = suspendCoroutine { continuation ->
    val divId = "star-increment"
    val div = document.createAndAppend<HTMLDivElement>("div") {
        id = divId
        className = "map-title-text"
        style.zIndex = Layer.ScriptWidget.zIndex().toString()
        style.position = "absolute"
        style.height = "${height}px"
        appendChild(incrementAnimationDiv)
    }

    gsap.fromTo(
        "#$divId",
        jso {
            autoAlpha = 1
            x = left
            y = top
        },
        jso {
            duration = 2
            autoAlpha = 0
            x = left
            y = top - 32
            onComplete = {
                document.body?.removeChild(div)
                continuation.resume(Unit)
            }
        }
    )
}

fun showArrowGif(
    uiCoordinateInGameContainer: PixelCoordinate,
    text: String
): HTMLDivElement {
    val div: HTMLDivElement = document.create("div") {
        className = "map-title-text"
        style.zIndex = Layer.ScriptWidget.zIndex().toString()
        style.position = "absolute"
        style.color = "red"
        style.left = "${uiCoordinateInGameContainer.x + 230}px"
        style.top = "${uiCoordinateInGameContainer.y + 50}px"
    }
    val gif: HTMLImageElement = document.createAndAppend("img") {
        id = "green-arrow"
        src =
            "data:image/gif;base64,R0lGODlhLABMAPcAAI7rr4DopUfefdX34Vbhh3Lmm+P666rww/3//vH89fv+/SHXYpztuR/XYTnccyLYY9355/r++yXYZfv+/Pf++SbYZvz//Szaau/89Pb9+SDXYfL99vP99ivZadb44ifZZiLYYi3aa+f77iPYZDHabinZaN/56UDdePD89Pn++1Lghfj++k/ggjrcdCTYZMj12Nn45EbefPT99zHabfX9+CnZZzXbcDbbceL66tP34DDabOv78T3cdovqrez88eX67GTjkZLssobqqpbstUPeemrklirZaej7717ijXrnodv55eb77sD00zLbbnTmnEnff03fgc/33SjZZ0Xeey7aazvcdZ3uusz227DxyEHdeez88qLvvjTbcPT9+FzijL/00aTvv8f111Xhh4jqq+r78FThhofqqrrzztf447jyzVriipTstILppi/abITpqMX11lfhieH66sL01MH007LxyT/dd8723c723Gzll+7883jnoFDghGjklIrqrMr22n7opDfbcbfyzK/xxrTyymXkk57uul7ijl3ijO388zjccqPvvpjttmvllqbvwLzz0KHuvLXyy3bnnpfstXDmmtr45b3z0On772LjkIzrrkvfgJDrsDjcc0LdejPbb8P01Xzoo4PpqLDxx63wxbPyyV/jjnzooprtuKzwxG3lmGHjj43rr1HghMf12N756HnnoHvnodH332/lmWXkkonqq67xxkjffnfnn3Xnnsb11kTee5XstGPjkUzfgKnwwrjyzE7fgsn12cv22qnww0/gg5ntt5vtudj45GDjj6DuvJHrsVniip/uu7/00jzcdZPss77z0ajvwYXpqT7ddmbkk1PghXHmm1vii33oo6vwxKfvwdL334/rsG3ll+T67GfklLHxyIHppqXvv0rff1jhiYPpp7vzz+X67c3227byy+D66dD33prtt1bhiKHuvdz55tT34MT01bnzzn/opXPmnG7lmXTmnWnklTLabkjefULdeTrcc4fpqh7XYP///wAAAAAAACH/C05FVFNDQVBFMi4wAwEAAAAh/wtYTVAgRGF0YVhNUDw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTM4IDc5LjE1OTgyNCwgMjAxNi8wOS8xNC0wMTowOTowMSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTcgKE1hY2ludG9zaCkiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6REZBODMxRjk2MTlBMTFFOEEwM0ZEM0YwMDFFOUU4NkUiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6REZBODMxRkE2MTlBMTFFOEEwM0ZEM0YwMDFFOUU4NkUiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDpERkE4MzFGNzYxOUExMUU4QTAzRkQzRjAwMUU5RTg2RSIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDpERkE4MzFGODYxOUExMUU4QTAzRkQzRjAwMUU5RTg2RSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PgH//v38+/r5+Pf29fTz8vHw7+7t7Ovq6ejn5uXk4+Lh4N/e3dzb2tnY19bV1NPS0dDPzs3My8rJyMfGxcTDwsHAv769vLu6ubi3trW0s7KxsK+urayrqqmop6alpKOioaCfnp2cm5qZmJeWlZSTkpGQj46NjIuKiYiHhoWEg4KBgH9+fXx7enl4d3Z1dHNycXBvbm1sa2ppaGdmZWRjYmFgX15dXFtaWVhXVlVUU1JRUE9OTUxLSklIR0ZFRENCQUA/Pj08Ozo5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xIubpcuDvEr+lECX+M98fSJqVrtxOdnmdKG/lc5co3Ov1J9bj759evfqGa9nRM2O/bv28NzRe1cPHqN4quTHmy/P/rz79PfX529/8f3T+PDNJ1999GE0m30XHUYWNRxJ4sqDEEYIYWFCVWjhhRhmiFBAACH5BAkDAP0ALAAAAAAsAEwAAAj/APsJHEiwoMGDCBMqXMiwocOHECNKnEixosWLGDNq3Mixo8YAzex5XFiBH79YIxGOM8kvX0qDPViadPKSYD6Z/O7VFGgPp8lxOwH55Kch2UskQ03iS8ngQVKTr0bie2oSkEcnVFki6XgzK78OHLF6NQlk472xLPtkXIlWKUZJGtqypGlxqlyTYCu+uiuzSEWhfE1KkDQRaWCW1CZ2OCwzQES2jE02gxgzsky6De1arupw72aZZRnq+CxzgVGFh0jjdJlwnQTVOKMipAYbJw+EyWr7lF2wq26WVmH+qkG8OPEGcisYL46Pt0MecjdCbys9usbpaKtTv249I/ax2rNzSN/uvTvG717Dgx8vvjz58+Yvos+qPj379e7bw38vP77F+VTVR9999uWH33769cdfXf5VVERbv3C0zysUVmihhTtlqOGGHBYUEAAh+QQJAwD9ACwAAAAALABMAAAI/wD7CRxIsKDBgwgTKlzIsKHDhxAjSpxIsaLFixgzatzIsaPHjwlf4XMCMmEHfvxelTSIBCU/fCsJ7nOJskhMgfho8gN004lOlEBi8vjJTwODkkCIovwFMpkLpShJevwFFSUPj6+qugzKMadWfh04cvvqEslGQGRdBsiYNO1SjI8WuFV7kepclPcs+ry7teJQvigrULQHmOa4iToK0+wRsaVil/kgznxM06ZDu5RRNnO4N3PZhvc803xwVGFb0S5hKqyAWqfUg4da64x8sLPszwbz3dbJ06Ak3bv58VgL8W/ajcbJIp+73G3z4xqTf32uPDpz686xQ88oXSv16dqrczq/Pj57+e0Yu1f97j08+PPi05OXb54++ovqobJf7749/PcYAXefRad9pZpGTiCh4IIMLsjVTRBGWFJAACH5BAkDAP0ALAAAAAAsAEwAAAj/APsJHEiwoMGDCBMqXMiwocOHECNKnEixosWLGDNq3Mixo8ePICu+4gck5MFkGvjxc2KyID6V/Dq0HDgSJj97M/sBsslPgqSWSHiqpNayg1CVAUJSO6pSH8geTGHG+vgyKj9AHmtaJdnR6FZ+C5JtLPIVJtGM6ySUhfkq49K1KnlgJAfXZpGLzerC1GHRiV6bJSnu/Kuywb6JQAjbPAsx2QLFNllCzAfZ5j2IWiur5POQsmaYNRxy+8wTScMZpHkmVZg4tc18Cns0cM1zKsK3tGHKPcgnt1DGBKv61o0wX5HjyI+rhQskOfJfqx/yqLtxOtzq1DVaX4v9uvbsGbeXS+3O/bv38OAxiv9Kfrz58ujPq09/cf3W9uzfu48Pf778+vRZZJ9V+N2nX3787edffwD+ZxEUAVZ0CFy/cLQONxhmqGGGh+XkoUYBAQAh+QQJAwD9ACwAAAAALABMAAAI/wD7CRxIsKDBgwgTKlzIsKHDhxAjSpxIsaLFixgzatzIsaPHjyAt4tsXEiE+fvdKGgTCr+U4lQTvtWzZA2a/QzNb4oMpSUPOlq9UnvzJr0PJAERnFgkJKGlLCes+InE6k9rHDlRnuuk4LuvMZhw1ec3pZOPQsfwAaXyFNiefjDrazlwg6eJUuTPzWWQgAW/OoBSp+c2pd+K+wT9jTeSBOOcMiSwb57QKsankmQ1qOrR3+SdlhpIWdP4JeGG+0T/VLkSK+udShadb56yhMJZsokgSzrhNlKTBu7wnH7QdPGfug/sA2VjO3IZMufiaMy/9kLHcjdbbYse7/brG7Gi7a0v/zp2894zgx4oPb348+vLvz2NM73W9+vbs47ufD5+//Iv0ZWVfffjdp19+/u0HYH8L/mdRgFRthFNbqmkExCEYZqihhr7Z5CFGAQEAIfkEBQMA/QAsAAAAACwATAAACP8A+wkcSLCgwYMIEypcyLChw4cQI0qcSLGixYsYM2rcyLGjx48gLY4LmXAcPx4kDQbgx7JISoKAWPKTsO5lPyQyWVKz2SEnSzcpTfrk14ykpqEynYTEh5QlIJCvmsrk81GHVJYLJHXEeZVlPo4MJHSV+WojtbEyv2bchzZnrIw82sqcgRGI3Jw7Lca8y7JBj4r2+OKlKGmB4JxlJeY7nPNpxJWMc7qEuDiyzBoQY1n2ieThjM0+9zUUClomPoaaGpT2qVTh2dU5USaMCtsnkIR7a8t0kewgH91DRxp8Ddw0wnE6qChfTqWnXH3Ml0ORGFfuxuptr9/Vbl0jdrTcs3tG3z6+O1zy581j/D42PPjy4tPHX4+evvqL7Lu6bw//vXz/9s2HX30D3mdRflftp19//P3XIEbEjSXBRvtU1lUHAdikoUYBAQA7"
    }
    val textNode = document.createTextNode(text)
    div.appendChild(gif)
    div.appendChild(textNode)
    document.body?.appendChild(div)
    return div
}

suspend fun showAchievementEffect(
    canvasState: GameCanvasState,
    achievementIconUrl: String,
    endCoordinateInGameContainer: PixelCoordinate
) {
    val imgId = "achievement-img-${uuid()}"
    val opaqueBackground = document.createAndAppend<HTMLDivElement>("div") {
        className = "achievement-background no-pointer-events"
        style.zIndex = EFFECT_Z_INDEX.toString()
        style.width = "${canvasState.gameContainerSize.width}px"
        style.height = "${canvasState.gameContainerSize.height}px"
    }

    val rotationDiv = document.createAndAppend<HTMLDivElement>("div") {
        className = "achievement-radial-animation no-pointer-events"
        style.zIndex = (EFFECT_Z_INDEX + 1).toString()
        style.width = "${canvasState.gameContainerSize.width}px"
        style.height = "${canvasState.gameContainerSize.height}px"
    }
    val img = document.createAndAppend<HTMLImageElement>("img") {
        id = imgId
        src = achievementIconUrl
        width = 32
        height = 32
        className = "achievement-img no-pointer-events"
        style.zIndex = (EFFECT_Z_INDEX + 2).toString()
    }
    playAudio("achievement")

    val center = PixelCoordinate(canvasState.gameContainerSize.width / 2, canvasState.gameContainerSize.height / 2)

    val timeline = gsap.timeline()
    timeline.to("#$imgId", jso {
        x = center.x
        y = center.y
        duration = 0
    })

    timeline.to("#$imgId", jso {
        x = center.x
        y = center.y
        duration = 1
        scale = 5
    })
    timeline.to("#$imgId", jso {
        x = center.x
        y = center.y
        duration = 1
        scale = 3
    })

    delay(3000)
    document.body?.removeChild(opaqueBackground)
    document.body?.removeChild(rotationDiv)

    itemFlyTo(imgId, PixelCoordinate(canvasState.gameContainerSize.width / 2, canvasState.gameContainerSize.height / 2), endCoordinateInGameContainer, 1.0) {
        document.body?.removeChild(img)
    }
}

/**
 * Fly to the start point, scale,then fly to the end point
 */
suspend fun itemPopupEffect(
    imgUrl: String,
    gameContainerSize: PixelSize,
    startCoordinateInGameContainer: PixelCoordinate,
    endCoordinateInGameContainer: PixelCoordinate,
    durationSecond: Double
) {
    // 1/3 time to fly to center of screen
    // 1/3 time to scale
    // 1/3 time to fly to item box
    playAudio("popup")
    val itemId = "item-popup-${uuid()}"
    document.createAndAppend<HTMLImageElement>("img") {
        id = itemId
        src = imgUrl
        style.zIndex = EFFECT_Z_INDEX.toString()
        style.position = "absolute"
        className = "inline-icon-32 outer-glow"
    }

    itemFlyTo(
        itemId,
        startCoordinateInGameContainer,
        PixelCoordinate(gameContainerSize.width / 2, gameContainerSize.height / 2),
        durationSecond / 3
    ) {
        itemScale(itemId, gameContainerSize, startCoordinateInGameContainer, endCoordinateInGameContainer, durationSecond)
    }
    gsap.timeline().to(
        "#$itemId",
        jso {
            scale = 5
        }
    )
    delay((durationSecond * 1000).toLong())
}

private fun itemFlyTo(
    itemId: String,
    start: PixelCoordinate,
    end: PixelCoordinate,
    durationSecond: Double,
    onCompleteFunction: UnitFunction = {}
) {
    gsap.fromTo(
        "#$itemId",
        jso {
            x = start.x
            y = start.y
        },
        jso {
            x = end.x
            y = end.y
            duration = durationSecond
            onComplete = onCompleteFunction
        }
    )
}

@Suppress("UNUSED_PARAMETER")
private fun itemScale(
    itemId: String,
    gameContainerSize: PixelSize,
    startCoordinateInGameContainer: PixelCoordinate,
    endCoordinateInGameContainer: PixelCoordinate,
    totalDurationSecond: Double
) {
    val onCompleteFunction = {
        itemFlyTo(
            itemId,
            PixelCoordinate(gameContainerSize.width / 2, gameContainerSize.height / 2),
            endCoordinateInGameContainer,
            totalDurationSecond / 3
        ) {
            document.body?.removeChild(document.getElementById(itemId)!!)
        }
    }
    gsap.fromTo(
        "#$itemId",
        jso {
            scale = 5
        },
        jso {
            scale = 2
            duration = totalDurationSecond / 3
            onComplete = onCompleteFunction
        },
    )
}

// TODO multiple effects at the same time.
suspend fun starFlyEffect(
    gameContainerSize: PixelSize,
    from: PixelCoordinate,
    to: PixelCoordinate,
    durationSecond: Int
): Unit = suspendCoroutine { continuation ->
    val canvas = document.createAndAppend<HTMLCanvasElement>("canvas") {
        id = "starfly-canvas"
        className = "no-pointer-events"
        style.zIndex = EFFECT_Z_INDEX.toString()
        style.position = "absolute"
        width = gameContainerSize.width
        height = gameContainerSize.height
    }
    val starDiv = document.createAndAppend<HTMLDivElement>("div") {
        id = "starfly-star"
        style.zIndex = (EFFECT_Z_INDEX + 1).toString()
        style.top = "0px"
        style.left = "0px"
        style.backgroundColor = "transparent"
        style.position = "absolute"

        appendChild(
            document.createElement("div").apply {
                className = "star-icon inline-icon-16"
            }
        )
    }

    window.asDynamic().starFly(
        canvas,
        starDiv,
        from.x,
        from.y,
        to.x,
        to.y,
        durationSecond,
        // continue animation after star flying is completed
        { continuation.resume(Unit) }
    ) {
        // but wait for all particles to finish then clean up
        document.body?.removeChild(starDiv)
        document.body?.removeChild(canvas)
    }
}

fun disconnectionEffect(gameContainerSize: PixelSize) {
    val id = "disconnect-${Date().getTime().toLong()}"
    createFullscreenDiv(id, Layer.FadeInFadeOut.zIndex(), gameContainerSize) {
        style.backgroundColor = "black"
    }
    window.asDynamic().gsap.fromTo(
        "#$id",
        jso {
            x = gameContainerSize.width / 2
            y = gameContainerSize.height / 2
            width = 0
            height = 0
        },
        jso {
            x = 0
            y = 0
            width = gameContainerSize.width
            height = gameContainerSize.height
        }
    )
}

private fun createFullscreenDiv(id: String, zIndex: Int, gameContainerSize: PixelSize, configure: HTMLDivElement.() -> Unit): HTMLDivElement {
    val div = document.createElement("div").unsafeCast<HTMLDivElement>()
    div.style.zIndex = zIndex.toString()
    div.style.width = "${gameContainerSize.width}px"
    div.style.height = "${gameContainerSize.height}px"
    div.style.position = "absolute"
    div.style.top = "0"
    div.style.left = "0"
    div.id = id
    div.configure()

    document.body?.appendChild(div)
    return div
}
