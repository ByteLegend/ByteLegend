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
package com.bytelegend.utils

import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.objects.GameMapMission
import com.bytelegend.app.shared.objects.GameMapRegion
import com.bytelegend.github.utils.generated.TiledMap
import org.apache.batik.anim.dom.SVGDOMImplementation
import org.apache.batik.dom.GenericDOMImplementation
import org.apache.batik.svggen.SVGGeneratorContext
import org.apache.batik.svggen.SVGGraphics2D
import org.apache.batik.svggen.SVGShape
import org.w3c.dom.Element
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Dimension
import java.awt.Shape
import java.awt.geom.GeneralPath
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.util.concurrent.atomic.AtomicReference

typealias ElementConfigurer = (Element) -> Unit

fun TiledMap.getPixelSize(): PixelSize = PixelSize((width * tilewidth).toInt(), (height * tileheight).toInt())

fun PixelSize.toDimension() = Dimension(width, height)

class ContextAwareSvgConverter(
    context: SVGGeneratorContext,
    private val contextElementConfigurer: AtomicReference<ElementConfigurer>,
) : SVGShape(context) {
    override fun toSVG(shape: Shape): Element {
        return super.toSVG(shape).apply {
            contextElementConfigurer.get()?.invoke(this)
        }
    }
}

// https://www.sarasoueidan.com/blog/svg-coordinate-systems/
class RoadmapSvgGenerator(
    val tiledMap: TiledMap,
    val regions: List<GameMapRegion>,
    val missions: List<GameMapMission>,
    private val outputSvgFile: File
) {
    private val contextElementConfigurer: AtomicReference<ElementConfigurer> = AtomicReference()
    private val domImpl = GenericDOMImplementation.getDOMImplementation()
    private val document = domImpl.createDocument(SVGDOMImplementation.SVG_NAMESPACE_URI, "svg", null)
    private val g = object : SVGGraphics2D(document) {
        override fun setGeneratorContext(generatorCtx: SVGGeneratorContext) {
            super.setGeneratorContext(generatorCtx)
            this.shapeConverter = ContextAwareSvgConverter(generatorCtx, contextElementConfigurer)
        }
    }.apply {
        svgCanvasSize = tiledMap.getPixelSize().toDimension()
    }

    private fun drawRegion(region: GameMapRegion) {
        val curvePoints = region.vertices
        require(curvePoints.size > 2)
        g.color = Color.WHITE
        g.stroke = BasicStroke(
            8.0f, BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER,
            20.0f, arrayOf(20.0f).toFloatArray(), 0.0f
        )
        val path = GeneralPath()

        path.moveTo(curvePoints[0].x.toDouble(), curvePoints[1].y.toDouble())

        for (i in 1 until curvePoints.size - 2) {
            val xc = (curvePoints[i].x + curvePoints[i + 1].x) / 2
            val yc = (curvePoints[i].y + curvePoints[i + 1].y) / 2
            path.quadTo(
                curvePoints[i].x.toDouble(),
                curvePoints[i].y.toDouble(),
                xc.toDouble(),
                yc.toDouble(),
            )
        }
        path.quadTo(
            curvePoints[curvePoints.size - 2].x.toDouble(), curvePoints[curvePoints.size - 2].y.toDouble(),
            curvePoints[curvePoints.size - 1].x.toDouble(), curvePoints[curvePoints.size - 1].y.toDouble()
        )
        path.closePath()
        g.draw(path) {
            it.setAttribute("pointer-events", "visible")
            it.setAttribute("onclick", "console.log('You have clicked the polygon.')")
            it.setAttribute("onmousemove", "console.log('You have mousemove the polygon.')")
            it.setAttribute("onmouseleave", "console.log('You have mouseleave the polygon.')")
        }
    }

    private fun writeToFile() {
        val root = g.root
        root.setAttributeNS(null, "viewBox", "0 0 ${tiledMap.getPixelSize().width} ${tiledMap.getPixelSize().height}")
        PrintWriter(FileWriter(outputSvgFile)).use {
            g.stream(root, it, true, false)
        }
    }

    fun generate() {
        regions.forEach(this::drawRegion)
        writeToFile()
    }

    fun SVGGraphics2D.fill(s: Shape, elementConfigurer: ElementConfigurer) {
        contextElementConfigurer.set(elementConfigurer)
        fill(s)
        contextElementConfigurer.set(null)
    }

    fun SVGGraphics2D.draw(s: Shape, elementConfigurer: ElementConfigurer) {
        contextElementConfigurer.set(elementConfigurer)
        draw(s)
        contextElementConfigurer.set(null)
    }

    fun SVGGraphics2D.drawPolygon(xPoints: IntArray, yPoints: IntArray, nPoints: Int, elementConfigurer: ElementConfigurer) {
        contextElementConfigurer.set(elementConfigurer)
        drawPolygon(xPoints, yPoints, nPoints)
        contextElementConfigurer.set(null)
    }

//    fun show() {
//        val impl = GenericDOMImplementation.getDOMImplementation()
//        val svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI
//        val doc = impl.createDocument(svgNS, "svg", null) //as SVGDocument
//
// //        // Create a converter for this document.
// //        val ctx = SVGGeneratorContext.createDefault(doc)
// //        ctx.domFactory = object : GenericDocument() {
// //            override fun createElementNS(namespaceURI: String?, qualifiedName: String?): Element {
// //                val ret = super.createElementNS(namespaceURI, qualifiedName)
// //                ret.setAttributeNS(null, "onclick", "alert('You have clicked the circle.')")
// //                return ret
// //            }
// //        }
//        val g = object : SVGGraphics2D(doc) {
//            override fun setGeneratorContext(generatorCtx: SVGGeneratorContext) {
//                super.setGeneratorContext(generatorCtx)
//                this.shapeConverter = ContextAwareSvgConverter()
//            }
//        }
//        g.svgCanvasSize = Dimension(1000, 1000)
//
//
// //        root.setAttributeNS(null, "width", "1000")
// //        root.setAttributeNS(null, "height", "1000")
//        g.paint = Color.BLACK
// //        g.drawRect(0, 0, 2000, 2000)
//        g.fill(Rectangle(0, 0, 1000, 1000))
//        g.paint = Color.RED
//        g.fill(Ellipse2D.Double(0.0, 0.0, 500.0, 500.0))
//
//        val root = g.root
//        root.setAttributeNS(null, "viewBox", "0 0 1000 1000")
//
//        val svg = File("/Users/zhb/Projects/ByteLegend/utils/build/test.svg")
// //        val useCSS = true // we want to use CSS style attributes
// //        val out: Writer = OutputStreamWriter(PrintWriter(FileWriter(svg)), "UTF-8")
//        PrintWriter(FileWriter(svg)).use {
//            g.stream(root, it, true, false)
//        }
// //        show(doc, g)
//    }
//
//    fun show(doc: SVGDocument, g: SVGGraphics2D) {
//        val useCSS = true // we want to use CSS style attributes
//        val out: Writer = OutputStreamWriter(System.out, "UTF-8")
//        g.stream(out, useCSS)
// //
// //        // Populate the document root with the generated SVG content.
// //        val root: Element = doc.documentElement
// //        g.getRoot(root)
// //
// //        // Display the document.
// //        val canvas = JSVGCanvas()
// //
// //        canvas.setSize(500, 500)
// //        val f = JFrame()
// ////        f.setSize(1000, 1000)
// //        f.preferredSize = Dimension(500, 500)
// //        f.contentPane.add(canvas)
// //        canvas.svgDocument = doc
// //        f.pack()
// //        f.isVisible = true
//    }

//    fun paint(g2d: Graphics2D) {
//        g2d.paint = Color.red
//        g2d.fill(Rectangle(10, 10, 100, 100))
//    }
}

// fun main(args: Array<String>) {
//    MinimapSvgGenerator().show()
// //    // Create an SVG document.
// //    val impl = SVGDOMImplementation.getDOMImplementation()
// //    val svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI
// //    val doc = impl.createDocument(svgNS, "svg", null) as SVGDocument
// //
// //    // Create a converter for this document.
// //    val g = SVGGraphics2D(doc)
// //
// //    // Do some drawing.
// //    val circle: Shape = Ellipse2D.Double(0.0, 0.0, 50.0, 50.0)
// //    g.paint = Color.red
// //    g.fill(circle)
// //    g.translate(60, 0)
// //    g.paint = Color.green
// //    g.fill(circle)
// //    g.translate(60, 0)
// //    g.paint = Color.blue
// //    g.fill(circle)
// //    g.svgCanvasSize = Dimension(180, 50)
// //
//
// }

// fun main(args: Array<String>) {
//    // Get a DOMImplementation.
//    val domImpl: DOMImplementation = SVGDOMImplementation.getDOMImplementation()
//
//    // Create an instance of org.w3c.dom.Document.
//    val svgNS = "http://www.w3.org/2000/svg"
//    val document: SVGDocument = domImpl.createDocument(svgNS, "svg", null) as SVGDocument
//
//    // Create an instance of the SVG Generator.
//    val svgGenerator = SVGGraphics2D(document)
//
//    // Ask the test to render into the SVG Graphics2D implementation.
//    val test = MinimapSvgGenerator()
//    test.paint(svgGenerator)
//
//    val canvas = JSVGCanvas()
//    val frame = JFrame()
//    frame.contentPane.add(canvas)
//    canvas.svgDocument = svgGenerator.getRoot(document.documentElement)
//    frame.pack()
//    frame.isVisible = true
//
//    // Finally, stream out SVG to the standard output using
//    // UTF-8 encoding.
//    val useCSS = true // we want to use CSS style attributes
//    val out: Writer = OutputStreamWriter(System.out, "UTF-8")
//    svgGenerator.stream(out, useCSS)
// }
