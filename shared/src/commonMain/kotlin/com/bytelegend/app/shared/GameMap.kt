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
@file:Suppress("UNCHECKED_CAST", "EXPERIMENTAL_API_USAGE")

package com.bytelegend.app.shared

import com.bytelegend.app.shared.ConstantPoolType.AnimationFrame
import com.bytelegend.app.shared.ConstantPoolType.AnimationLayer
import com.bytelegend.app.shared.ConstantPoolType.BlockerGameTile
import com.bytelegend.app.shared.ConstantPoolType.NonBlockerGameTile
import com.bytelegend.app.shared.ConstantPoolType.StaticImageLayer
import com.bytelegend.app.shared.annotations.JsonIgnore
import com.bytelegend.app.shared.objects.CompressedGameMapAnimation
import com.bytelegend.app.shared.objects.CompressedGameMapCurve
import com.bytelegend.app.shared.objects.CompressedGameMapDynamicObject
import com.bytelegend.app.shared.objects.CompressedGameMapEntrancePoint
import com.bytelegend.app.shared.objects.CompressedGameMapMission
import com.bytelegend.app.shared.objects.CompressedGameMapObject
import com.bytelegend.app.shared.objects.CompressedGameMapPoint
import com.bytelegend.app.shared.objects.CompressedGameMapRegion
import com.bytelegend.app.shared.objects.CompressedGameMapText
import com.bytelegend.app.shared.objects.GameMapObject
import com.bytelegend.app.shared.objects.GameMapObjectType
import com.bytelegend.app.shared.objects.GameMapObjectType.*
import com.bytelegend.app.shared.util.HashBiMap
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonTransformingSerializer
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

const val BLOCKER = 1
const val NON_BLOCKER = 0

interface GameMap {
    val id: String

    /**
     * How many tiles in this map?
     */
    val size: GridSize

    /**
     * How large (in pixel) is every tile in this map?
     */
    val tileSize: PixelSize

    /**
     * The map size in pixel
     */
    val pixelSize: PixelSize
        @JsonIgnore get() = tileSize * size

    val rawTiles: List<List<RawGameMapTile>>

    val objects: List<GameMapObject>
}

interface CompressableElement {
    /**
     * First round: scan all raw entries
     */
    fun addToRawConstantPool(rawConstantPool: MutableMap<Any, Int>)

    /**
     * Final round: compress and add to final constant pool
     */
    fun addToFinalConstantPool(rawConstantPool: Map<Any, Int>, finalConstantPool: LinkedHashSet<ConstantPoolEntry>)
}

@Serializable
data class RawGameMap(
    override val id: String,
    /**
     * How many tiles in this map?
     */
    override val size: GridSize,

    /**
     * How large (in pixel) is every tile in this map?
     */
    override val tileSize: PixelSize,
    override val rawTiles: List<List<RawGameMapTile>>,
    override val objects: List<GameMapObject>
) : GameMap {
    fun compress(): CompressedGameMap {
        val rawConstantPool: MutableMap<Any, Int> = HashBiMap()
        val constantPool: LinkedHashSet<ConstantPoolEntry> = LinkedHashSet()
        val flattenedTiles = rawTiles.flatten()

        flattenedTiles.forEach {
            it.addToRawConstantPool(rawConstantPool)
        }
        flattenedTiles.forEach {
            it.addToFinalConstantPool(rawConstantPool, constantPool)
        }
        return CompressedGameMap(
            id,
            size,
            tileSize,
            constantPool.toList(),
            flattenedTiles.mapToConstantPoolIndex(rawConstantPool),
            objects.map { it.compress() }
        )
    }
}

fun MutableMap<Any, Int>.recordRawConstantPoolEntries(vararg entries: Any) = entries.forEach {
    get(it) ?: put(it, this.size + 1)
}

fun <T> List<T>.mapToConstantPoolIndex(rawConstantPool: Map<Any, Int>): List<Int> = map { rawConstantPool.getValue(it as Any) }

@Serializable
data class RawGameMapTile(
    val layers: List<RawGameMapTileLayer>,
    val blocker: Int
) : CompressableElement {
    override fun addToRawConstantPool(rawConstantPool: MutableMap<Any, Int>) {
        rawConstantPool.recordRawConstantPoolEntries(this)
        layers.forEach { it.addToRawConstantPool(rawConstantPool) }
    }

    override fun addToFinalConstantPool(rawConstantPool: Map<Any, Int>, finalConstantPool: LinkedHashSet<ConstantPoolEntry>) {
        finalConstantPool.add(GameTileConstantPoolEntry(layers.mapToConstantPoolIndex(rawConstantPool), blocker))
        layers.forEach { it.addToFinalConstantPool(rawConstantPool, finalConstantPool) }
    }
}

val rawGameMapTileLayerModule = SerializersModule {
    polymorphic(RawGameMapTileLayer::class) {
        subclass(RawStaticImageLayer::class)
        subclass(RawAnimationLayer::class)
    }
}

@Polymorphic
interface RawGameMapTileLayer : CompressableElement {
    val layer: Int
}

@Serializable
data class RawStaticImageLayer(
    val coordinate: GridCoordinate,
    override val layer: Int
) : RawGameMapTileLayer {
    override fun addToRawConstantPool(rawConstantPool: MutableMap<Any, Int>) {
        rawConstantPool.recordRawConstantPoolEntries(this)
        rawConstantPool.recordRawConstantPoolEntries(coordinate)
    }

    override fun addToFinalConstantPool(rawConstantPool: Map<Any, Int>, finalConstantPool: LinkedHashSet<ConstantPoolEntry>) {
        finalConstantPool.add(StaticImageLayerEntry(rawConstantPool.getValue(coordinate), layer))
        finalConstantPool.add(CoordinateConstantPoolEntry(coordinate))
    }
}

@Serializable
data class RawAnimationLayer(
    val frames: List<RawTileAnimationFrame>,
    override val layer: Int
) : RawGameMapTileLayer {
    override fun addToRawConstantPool(rawConstantPool: MutableMap<Any, Int>) {
        rawConstantPool.recordRawConstantPoolEntries(this)
        frames.forEach { rawConstantPool.recordRawConstantPoolEntries(it) }
    }

    override fun addToFinalConstantPool(rawConstantPool: Map<Any, Int>, finalConstantPool: LinkedHashSet<ConstantPoolEntry>) {
        finalConstantPool.add(AnimationLayerEntry(frames.mapToConstantPoolIndex(rawConstantPool), layer))
        frames.forEach { finalConstantPool.add(AnimationFrameEntry(it)) }
    }
}

@Serializable
data class RawTileAnimationFrame(
    val coordinate: GridCoordinate,
    // milliseconds
    val duration: Int
)

@Serializable
data class CompressedGameMap(
    override val id: String,
    override val size: GridSize,
    override val tileSize: PixelSize,
    @Serializable(with = ConstantPoolEntryListSerializer::class)
    val constantPool: List<ConstantPoolEntry>,
    val tiles: List<Int>,
    @Serializable(with = CompressedGameMapObjectListSerializer::class)
    val compressedObjects: List<CompressedGameMapObject> = emptyList(),
) : GameMap {
    private val rawGameMap: RawGameMap = decompress()
    override val rawTiles: List<List<RawGameMapTile>>
        @JsonIgnore get() = rawGameMap.rawTiles
    override val objects: List<GameMapObject>
        @JsonIgnore get() = rawGameMap.objects

    fun decompress(): RawGameMap {
        val constantPoolTable: Map<Int, ConstantPoolEntry> = constantPool.withIndex().associate {
            it.index + 1 to IndexedConstantPoolEntryWrapper(it.index + 1, it.value)
        }

        val decompressedTiles = tiles.chunked(size.width)
            .map { constantPoolTable.getValue(it).decompress(constantPoolTable) as RawGameMapTile }
        return RawGameMap(id, size, tileSize, decompressedTiles, compressedObjects.map { it.decompress() })
    }
}

object ConstantPoolEntryListSerializer : JsonTransformingSerializer<List<ConstantPoolEntry>>(ListSerializer(ConstantPoolEntrySerializer))
object CompressedGameMapObjectListSerializer : JsonTransformingSerializer<List<CompressedGameMapObject>>(ListSerializer(CompressedGameMapObjectSerializer))

@Suppress("UNCHECKED_CAST")
enum class ConstantPoolType(
    /**
     * The index in constant pool, starting from 1.
     */
    val index: Int,
    val serializer: KSerializer<Any>
) {
    Coordinate(1, GridCoordinate.serializer() as KSerializer<Any>) {
        override fun of(value: Any) = CoordinateConstantPoolEntry(value as GridCoordinate)
    },
    BlockerGameTile(2, ListSerializer(Int.serializer()) as KSerializer<Any>) {
        override fun of(value: Any) = GameTileConstantPoolEntry(value as List<Int>, BLOCKER)
    },
    NonBlockerGameTile(3, ListSerializer(Int.serializer()) as KSerializer<Any>) {
        override fun of(value: Any) = GameTileConstantPoolEntry(value as List<Int>, NON_BLOCKER)
    },
    StaticImageLayer(4, ListSerializer(Int.serializer()) as KSerializer<Any>) {
        override fun of(value: Any): StaticImageLayerEntry {
            val list = value as List<Int>
            return StaticImageLayerEntry(list[0], list[1])
        }
    },
    AnimationLayer(5, ListSerializer(Int.serializer()) as KSerializer<Any>) {
        override fun of(value: Any): AnimationLayerEntry {
            val list = value as List<Int>
            return AnimationLayerEntry(list.subList(0, list.size - 1), list.last())
        }
    },
    AnimationFrame(6, RawTileAnimationFrame.serializer() as KSerializer<Any>) {
        override fun of(value: Any): ConstantPoolEntry = AnimationFrameEntry(value as RawTileAnimationFrame)
    };

    abstract fun of(value: Any): ConstantPoolEntry

    companion object {
        fun ofIndex(index: Int) = values()[index - 1]
    }
}

// https://kotlin.github.io/kotlinx.serialization/kotlinx-serialization-json/kotlinx-serialization-json/kotlinx.serialization.json/-json-content-polymorphic-serializer/index.html
object CompressedGameMapObjectSerializer : JsonContentPolymorphicSerializer<CompressedGameMapObject>(CompressedGameMapObject::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out CompressedGameMapObject> {
        return when (GameMapObjectType.fromIndex(element.jsonObject["type"].toString().toInt())) {
            GameMapText -> CompressedGameMapText.serializer()
            GameMapRegion -> CompressedGameMapRegion.serializer()
            GameMapPoint -> CompressedGameMapPoint.serializer()
            GameMapCurve -> CompressedGameMapCurve.serializer()
            GameMapDynamicSprite -> CompressedGameMapDynamicObject.serializer()
            GameMapMission -> CompressedGameMapMission.serializer()
            GameMapEntrancePoint -> CompressedGameMapEntrancePoint.serializer()
            GameMapEntranceDestinationPoint -> CompressedGameMapEntrancePoint.serializer()
            GameMapAnimation -> CompressedGameMapAnimation.serializer()
        }
    }
}

// https://github.com/Kotlin/kotlinx.serialization/blob/master/guide/example/example-serializer-11.kt
@Deprecated("Awful deserialization performance, https://github.com/Kotlin/kotlinx.serialization/issues/1258")
object ConstantPoolEntrySerializer : KSerializer<ConstantPoolEntry> {
    @OptIn(InternalSerializationApi::class)
    override val descriptor: SerialDescriptor = buildSerialDescriptor("ConstantPoolEntry", StructureKind.OBJECT) {
        // type
        element<Int>("t")
        // value
        element("v", buildSerialDescriptor("ConstantPoolEntryValue", PolymorphicKind.OPEN))
    }

    override fun deserialize(decoder: Decoder): ConstantPoolEntry {
        val composite = decoder.beginStructure(descriptor)
        var type: ConstantPoolType? = null
        var value: Any? = null
        while (true) {
            when (val index = composite.decodeElementIndex(descriptor)) {
                0 -> type = ConstantPoolType.ofIndex(composite.decodeIntElement(descriptor, 0))
                1 -> value = composite.decodeSerializableElement(descriptor, index, type!!.serializer)
                DECODE_DONE -> break
                else -> error("Unexpected index: $index")
            }
        }
        composite.endStructure(descriptor)
        return type!!.of(value!!)
    }

    override fun serialize(encoder: Encoder, value: ConstantPoolEntry) {
        val composite = encoder.beginStructure(descriptor)
        composite.encodeIntElement(descriptor, 0, value.type.index)
        composite.encodeSerializableElement(descriptor, 1, value.type.serializer, value.value)
        composite.endStructure(descriptor)
    }
}

@Serializable(with = ConstantPoolEntrySerializer::class)
interface ConstantPoolEntry {
    fun getIndex(): Int = -1
    val type: ConstantPoolType
    val value: Any

    fun <T> decompress(constantPoolTable: Map<Int, ConstantPoolEntry>): T
}

data class GameTileConstantPoolEntry(
    val layers: List<Int>,
    val blocker: Int
) : ConstantPoolEntry {
    override val type: ConstantPoolType
        get() = if (blocker == BLOCKER) BlockerGameTile else NonBlockerGameTile
    override val value: Any
        get() = layers

    override fun <T> decompress(constantPoolTable: Map<Int, ConstantPoolEntry>): T {
        val layers = layers.map { constantPoolTable.getValue(it).decompress(constantPoolTable) as RawGameMapTileLayer }
        return RawGameMapTile(layers, blocker) as T
    }
}

class IndexedConstantPoolEntryWrapper(private val index: Int, private val delegate: ConstantPoolEntry) : ConstantPoolEntry by delegate {
    override fun getIndex() = index
}

data class CoordinateConstantPoolEntry(
    val coordinate: GridCoordinate
) : ConstantPoolEntry {
    override val type: ConstantPoolType
        get() = ConstantPoolType.Coordinate
    override val value: Any
        get() = coordinate

    override fun <T> decompress(constantPoolTable: Map<Int, ConstantPoolEntry>): T = coordinate as T
}

data class StaticImageLayerEntry(
    val coordinate: Int,
    val layer: Int
) : ConstantPoolEntry {
    override val type: ConstantPoolType
        get() = StaticImageLayer
    override val value: Any
        get() = listOf(coordinate, layer)

    override fun <T> decompress(constantPoolTable: Map<Int, ConstantPoolEntry>): T {
        return RawStaticImageLayer(constantPoolTable.getValue(coordinate).decompress(constantPoolTable) as GridCoordinate, layer) as T
    }
}

data class AnimationLayerEntry(
    val frames: List<Int>,
    val layer: Int
) : ConstantPoolEntry {
    override val type: ConstantPoolType
        get() = AnimationLayer
    override val value: Any
        get() = frames + layer

    override fun <T> decompress(constantPoolTable: Map<Int, ConstantPoolEntry>): T {
        val frames = frames.map { constantPoolTable.getValue(it).decompress(constantPoolTable) as RawTileAnimationFrame }
        return RawAnimationLayer(frames, layer) as T
    }
}

data class AnimationFrameEntry(
    val frame: RawTileAnimationFrame
) : ConstantPoolEntry {
    override val type: ConstantPoolType
        get() = AnimationFrame
    override val value: Any
        get() = frame

    override fun <T> decompress(constantPoolTable: Map<Int, ConstantPoolEntry>): T = frame as T
}
