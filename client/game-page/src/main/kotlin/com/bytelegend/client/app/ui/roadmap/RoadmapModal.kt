@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.app.ui.roadmap

import com.bytelegend.app.client.ui.bootstrap.BootstrapAlert
import com.bytelegend.app.client.ui.bootstrap.BootstrapAlertLink
import com.bytelegend.app.client.ui.bootstrap.BootstrapButton
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalHeader
import com.bytelegend.app.client.ui.bootstrap.BootstrapSwitchButton
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.client.app.engine.logger
import com.bytelegend.client.app.obj.downloadURI
import com.bytelegend.client.app.obj.uuid
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.absoluteDiv
import com.bytelegend.client.app.ui.minimap.getMinimapMapFeatures
import com.bytelegend.client.app.ui.minimap.getRoadmapEChartsOptions
import com.bytelegend.client.app.ui.mission.ModalCloseButton
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinext.js.jsObject
import kotlinx.browser.document
import kotlinx.browser.localStorage
import kotlinx.browser.window
import kotlinx.html.classes
import kotlinx.html.id
import kotlinx.html.js.onMouseDownFunction
import kotlinx.html.js.onMouseMoveFunction
import kotlinx.html.js.onMouseOutFunction
import kotlinx.html.js.onMouseUpFunction
import kotlinx.html.js.onWheelFunction
import org.w3c.dom.events.MouseEvent
import org.w3c.dom.events.WheelEvent
import react.RBuilder
import react.RState
import react.dom.div
import react.dom.jsStyle
import react.dom.span
import react.setState

interface RoadmapModalState : RState {
    // modalSize/mapSize ~ 1
    var zoom: Double

    // the roadmap div position, relative to container
    // for example, (-200,-100) means roadmap echarts div is (-200, -100) to parent div element
    // Note that left/top are always negative
    var mapLeftTop: PixelCoordinate
    var modalSize: PixelSize
    var cursor: String
    var showPromptBanner: Boolean
    var showMissionTitles: Boolean
    var lastMouseDownCoordinateOnModal: PixelCoordinate?
    var lastMouseDownCoordinateOnMap: PixelCoordinate?
}

const val DONT_SHOW_ROADMAP_BANNER = "DontShowRoadmapBanner"
const val DEFAULT_DOWNLOAD_ROADMAP_IMAGE_SIZE = 2000

class RoadmapModal(props: GameProps) : GameUIComponent<GameProps, RoadmapModalState>(props) {
    private val echartsContainerElementId = "echarts-container-${uuid()}"

    // https://echarts.apache.org/en/api.html#echarts.init
    private var echarts: dynamic = undefined
    private var options: dynamic = undefined

    private val minZoom: Double
        get() = 1.0 * state.modalSize.width / activeScene.map.pixelSize.width
    private val maxZoom = 1.0
    private val roadmapWidth
        get() = (state.zoom * mapPixelSize.width).toInt()
    private val roadmapHeight
        get() = (state.zoom * mapPixelSize.height).toInt()

    override fun RoadmapModalState.init(props: GameProps) {
        cursor = "grab"
        mapLeftTop = PixelCoordinate(0, 0)
        // TODO change this if map is not square. Currently all map is square
        modalSize = determineRoadmapSize().let { PixelSize(it, it) }
        zoom = 1.0 * modalSize.width / props.game.activeScene.map.pixelSize.width
        showPromptBanner = localStorage.getItem(DONT_SHOW_ROADMAP_BANNER) == null
        showMissionTitles = false
    }

    override fun RBuilder.render() {
        val roadmapSize = determineRoadmapSize()

        child(ModalCloseButton::class) {
            attrs.onClickFunction = {
                game.modalController.hide()
            }
        }

        div {
            attrs.classes = jsObjectBackedSetOf("show-mission-titles-switch")

            span {
                +i("ShowMissionTitles")
            }

            BootstrapSwitchButton {
                attrs.size = "xs"
                attrs.checked = state.showMissionTitles
                attrs.onChange = {
                    setState {
                        showMissionTitles = it
                    }
                }
            }
        }

        BootstrapModalHeader {
            span {
                +i("LearningRoadmap")
                BootstrapButton {
                    attrs.className = "download-my-roadmap-btn"
                    attrs.variant = "outline-primary"
                    attrs.size = "sm"
                    +i("DownloadMyRoadmap")
                    attrs.onClick = {
                        if (echarts != undefined) {
                            val oldZoom = state.zoom
                            setState(jsObject<RoadmapModalState> { zoom = 1.0 * DEFAULT_DOWNLOAD_ROADMAP_IMAGE_SIZE / mapPixelSize.width }) {
                                downloadURI(echarts.getDataURL(), "my-roadmap.svg")
                                setState { zoom = oldZoom }
                            }
                        }
                    }
                }
            }
        }

        BootstrapModalBody {
            div {
                attrs.jsStyle {
                    position = "relative"
                    overflow = "hidden"
                    width = "${roadmapSize}px"
                    height = "${roadmapSize}px"
                }
                absoluteDiv(top = 0, left = 0, width = roadmapSize, height = roadmapSize, zIndex = 2) {
                    attrs.jsStyle {
                        cursor = state.cursor
                    }
                    attrs.onMouseOutFunction = {
                        onMouseUpEvent(it.asDynamic().nativeEvent)
                    }
                    attrs.onWheelFunction = {
                        onMouseWheelEvent(it.asDynamic().nativeEvent)
                    }
                    attrs.onMouseUpFunction = {
                        onMouseUpEvent(it.asDynamic().nativeEvent)
                    }
                    attrs.onMouseDownFunction = {
                        onMouseDownEvent(it.asDynamic().nativeEvent)
                    }
                    attrs.onMouseMoveFunction = {
                        onMouseMoveEvent(it.asDynamic().nativeEvent)
                    }
                }

                absoluteDiv(
                    left = state.mapLeftTop.x,
                    top = state.mapLeftTop.y,
                    width = roadmapWidth,
                    height = roadmapHeight,
                    zIndex = 1
                ) {
                    attrs.id = echartsContainerElementId
                }
            }

            if (state.showPromptBanner) {
                BootstrapAlert {
                    attrs.variant = "info"
                    attrs.dismissible = false
                    attrs.onClose = {
                        setState {
                            showPromptBanner = false
                        }
                    }
                    attrs.className = "roadmap-modal-prompt"
                    +game.i("TryScrollAndDrag")
                    BootstrapAlertLink {
                        attrs.href = "#"
                        attrs.className = "roadmap-modal-prompt-link"
                        attrs.onClick = {
                            setState {
                                showPromptBanner = false
                            }
                            localStorage.setItem(DONT_SHOW_ROADMAP_BANNER, "1")
                        }
                        +game.i("DontShowThisAgain")
                    }
                }
                window.setTimeout({
                    setState {
                        showPromptBanner = false
                    }
                }, 5000)
            }
        }
    }

    private fun initIfNot(refresh: Boolean) {
        if (echarts == undefined) {
            document.getElementById(echartsContainerElementId)?.apply {
                echarts = window.asDynamic().echarts.init(this, "light", jsObject {
                    renderer = "svg"
                })
                window.asDynamic().echarts.registerMap("minimap", activeScene.getMinimapMapFeatures())
            }
        }
        if (options == undefined || refresh) {
            options = activeScene.getRoadmapEChartsOptions(state.showMissionTitles)
            if (logger.debugEnabled) {
                logger.debug("Set echarts options for minimap: ${JSON.stringify(options)}")
            }
            echarts.setOption(options)
        }
    }

    override fun componentDidMount() {
        initIfNot(false)
    }

    override fun componentDidUpdate(prevProps: GameProps, prevState: RoadmapModalState, snapshot: Any) {
        initIfNot(state.showMissionTitles != prevState.showMissionTitles)
        if (state.zoom != prevState.zoom) {
            echarts.resize(roadmapWidth, roadmapHeight)
        }
    }

    private fun determineRoadmapSize(): Int {
        return if (gameContainerWidth > gameContainerHeight) {
            // modal header
            gameContainerHeight - 120
        } else {
            gameContainerWidth
        }
    }

    private fun onMouseWheelEvent(event: WheelEvent) {
        val newZoom = determineNewZoom(event)
        val newLeftTop = determineNewLeftTop(event, newZoom)
        setState {
            zoom = newZoom
            mapLeftTop = newLeftTop
        }
    }

    private fun determineNewZoom(event: WheelEvent): Double {
        var newZoom = state.zoom
        val delta = 0.05
        if (event.deltaY > 0) {
            newZoom -= delta
        } else {
            newZoom += delta
        }
        if (newZoom < minZoom) {
            newZoom = minZoom
        }
        if (newZoom > maxZoom) {
            newZoom = maxZoom
        }
        return newZoom
    }

    // when mouse scrolls, keep the point under mouse cursor unchanged.
    private fun determineNewLeftTop(event: WheelEvent, newZoom: Double): PixelCoordinate {
        val modalX = event.offsetX
        val modalY = event.offsetY

        val mapCoordinateUnderCursor = calculateMapCoordinateUnderCursor(state.zoom, modalX.toInt(), modalY.toInt())
        return calculateLeftTopToMakeSureModalPointSameAsMapPoint(
            newZoom,
            modalX.toInt(), modalY.toInt(),
            mapCoordinateUnderCursor.x, mapCoordinateUnderCursor.y
        )
    }

    private fun calculateMapCoordinateUnderCursor(zoom: Double, modalX: Int, modalY: Int): PixelCoordinate {
        // distanceOnModal / distanceOnMap = zoom
        // modalX/(mapX-topLeft.x) = zoom

        return PixelCoordinate(
            (modalX - state.mapLeftTop.x) / zoom,
            (modalY - state.mapLeftTop.y) / zoom
        )
    }

    private fun calculateLeftTopToMakeSureModalPointSameAsMapPoint(
        zoom: Double,
        modalX: Int,
        modalY: Int,
        mapX: Int,
        mapY: Int
    ): PixelCoordinate {
        // Keep coordinate under cursor unchanged, let's calculate the new left top
        var newLeft = (modalX - mapX * zoom).toInt()
        var newTop = (modalY - mapY * zoom).toInt()

        if (newLeft > 0) {
            newLeft = 0
        }
        if (newTop > 0) {
            newTop = 0
        }
        if (state.modalSize.width - newLeft > zoom * mapPixelSize.width) {
            newLeft = (state.modalSize.width - zoom * mapPixelSize.width).toInt()
        }
        if (state.modalSize.height - newTop > zoom * mapPixelSize.height) {
            newTop = (state.modalSize.height - zoom * mapPixelSize.height).toInt()
        }
        return PixelCoordinate(newLeft, newTop)
    }

    private fun onMouseMoveEvent(event: MouseEvent) {
        if (state.lastMouseDownCoordinateOnMap != null) {
            setState {
                mapLeftTop = calculateLeftTopToMakeSureModalPointSameAsMapPoint(
                    state.zoom,
                    event.offsetX.toInt(), event.offsetY.toInt(),
                    state.lastMouseDownCoordinateOnMap!!.x, state.lastMouseDownCoordinateOnMap!!.y
                )
            }
        } else {
            document.getElementById(echartsContainerElementId)?.firstChild?.dispatchEvent(MouseEvent("mousemove", event.asDynamic()))
        }
    }

    private fun onMouseDownEvent(event: MouseEvent) {
        setState {
            cursor = "grabbing"
            lastMouseDownCoordinateOnModal = PixelCoordinate(event.offsetX, event.offsetY)
            lastMouseDownCoordinateOnMap = calculateMapCoordinateUnderCursor(state.zoom, event.offsetX.toInt(), event.offsetY.toInt())
        }
    }

    private fun onMouseUpEvent(event: MouseEvent) {
        setState {
            cursor = "grab"
            lastMouseDownCoordinateOnModal = null
            lastMouseDownCoordinateOnMap = null
        }
    }
}
