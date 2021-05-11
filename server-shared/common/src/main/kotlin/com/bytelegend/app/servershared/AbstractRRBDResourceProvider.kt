package com.bytelegend.app.servershared

import com.bytelegend.app.shared.CompressedGameMap
import com.bytelegend.app.shared.GameMap
import com.bytelegend.app.shared.GameMapDefinition
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.entities.mission.ChallengeType
import com.bytelegend.app.shared.entities.mission.MissionSpec
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.i18n.LocalizedText
import com.bytelegend.app.shared.objects.GameMapObject
import com.bytelegend.app.shared.objects.GameMapPoint
import com.bytelegend.app.shared.objects.defaultMapEntranceDestinationId
import com.bytelegend.app.shared.objects.defaultMapEntranceId
import com.fasterxml.jackson.core.type.TypeReference
import kotlinx.serialization.json.Json
import java.io.File

abstract class AbstractRRBDResourceProvider(
    private val localRRBD: String,
    serializer: JsonMapper
) {
    val localizedText: Map<String, LocalizedText> by lazy {
        val i18nAllJson = File(localRRBD).resolve("i18n/all.json").readText()
        serializer.fromJson(i18nAllJson, object : TypeReference<Map<String, LocalizedText>>() {})
    }

    val mapDefinitions: List<GameMapDefinition> by lazy {
        val mapHierarchyYml = File(localRRBD).resolve("map/hierarchy.yml").readText()
        serializer.fromYaml(mapHierarchyYml, object : TypeReference<List<GameMapDefinition>>() {})
    }
    val idToMapDefinitions: Map<String, GameMapDefinition> by lazy {
        mutableMapOf<String, GameMapDefinition>().apply {
            putInto(this, mapDefinitions)
        }.toMap()
    }

    private val mapToMissionSpecs: Map<String, List<MissionSpec>> =
        idToMapDefinitions.keys.associateWith { serializer.fromJson(File(localRRBD, "map/$it/missions.json").readText(), object : TypeReference<List<MissionSpec>>() {}) }

    private val idToMissionSpec: Map<String, MissionSpec> = mapToMissionSpecs.values
        .flatten()
        .associateBy { it.id }

    private val repoToMissionSpec: Map<String, MissionSpec> = idToMissionSpec.values
        .filter { it.challenge != null && (it.challenge?.type == ChallengeType.PullRequest || it.challenge?.type == ChallengeType.Star) }
        .associateBy { it.challenge!!.spec }

    private val idToMaps: Map<String, FastAccessGameMap> = idToMapDefinitions.keys
        .filter { File(localRRBD).resolve("map/$it/map.json").isFile }
        .associateWith {
            FastAccessGameMap(File(localRRBD).resolve("map/$it/map.json").readAsGameMap())
        }

    private fun putInto(result: MutableMap<String, GameMapDefinition>, maps: List<GameMapDefinition>) {
        maps.forEach {
            result[it.id] = it
            putInto(result, it.children)
        }
    }

    fun getMissionSpecById(id: String) = idToMissionSpec.getValue(id)
    fun getMissionSpecByRepo(repo: String) = repoToMissionSpec.getValue(repo)

    fun getI18nText(id: String, locale: Locale) = localizedText.getValue(id).getTextOrDefaultLocale(locale)

    fun getEntranceDestinationPoint(srcMapId: String, destMapId: String): GridCoordinate {
        return idToMaps.getValue(destMapId).getObjectById<GameMapPoint>(
            defaultMapEntranceDestinationId(defaultMapEntranceId(srcMapId, destMapId))
        ).point
    }
}

class FastAccessGameMap(private val delegate: GameMap) : GameMap by delegate {
    private val idToObject = objects.associateBy { it.id }

    fun <T : GameMapObject> getObjectById(id: String): T {
        return idToObject.getValue(id) as T
    }
}

private fun File.readAsGameMap(): GameMap {
    return Json.decodeFromString(CompressedGameMap.serializer(), readText()).decompress()
}
