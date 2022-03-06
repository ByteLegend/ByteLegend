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
package com.bytelegend.app.servershared

import com.bytelegend.app.shared.CompressedGameMap
import com.bytelegend.app.shared.GameMap
import com.bytelegend.app.shared.GameMapDefinition
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.entities.mission.ChallengeSpec
import com.bytelegend.app.shared.entities.mission.MissionSpec
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.i18n.LocalizedText
import com.bytelegend.app.shared.i18n.render
import com.bytelegend.app.shared.objects.GameMapEntrancePoint
import com.bytelegend.app.shared.objects.GameMapObject
import com.bytelegend.app.shared.objects.mapEntranceDestinationId
import com.bytelegend.app.shared.objects.mapEntranceId
import com.fasterxml.jackson.core.type.TypeReference
import kotlinx.serialization.json.Json
import java.io.File

interface RRBDResourceProvider {
    val mapDefinitions: List<GameMapDefinition>
    val idToMapDefinitions: Map<String, GameMapDefinition>
    val allMaps: List<String>

    fun getMissionSpecByMissionId(missionId: String): MissionSpec?
    fun getMissionChallengeByChallengeId(challengeId: String): Pair<MissionSpec, ChallengeSpec>?
    fun getMissionChallengeByRepo(repo: String): Pair<MissionSpec, ChallengeSpec>?
    fun getI18nText(id: String, locale: Locale, vararg args: String): String
    fun getEntranceDestinationPoint(srcMapId: String, destMapId: String): GridCoordinate
}

abstract class AbstractRRBDResourceProvider(
    private val localRRBD: String,
    serializer: JsonMapper
) : RRBDResourceProvider {
    val localizedText: Map<String, LocalizedText> by lazy {
        val i18nAllJson = File(localRRBD).resolve("i18n/all.json").readText()
        serializer.fromJson(i18nAllJson, object : TypeReference<Map<String, LocalizedText>>() {})
    }

    override val mapDefinitions: List<GameMapDefinition> by lazy {
        val mapHierarchyYml = File(localRRBD).resolve("map/hierarchy.yml").readText()
        serializer.fromYaml(mapHierarchyYml, object : TypeReference<List<GameMapDefinition>>() {})
    }
    final override val idToMapDefinitions: Map<String, GameMapDefinition> by lazy {
        mutableMapOf<String, GameMapDefinition>().apply {
            putInto(this, mapDefinitions)
        }.toMap()
    }

    override val allMaps: List<String> by lazy { idToMapDefinitions.keys.toList() }

    val mapToMissionSpecs: Map<String, List<MissionSpec>> =
        idToMapDefinitions.keys.associateWith { serializer.fromJson(File(localRRBD, "map/$it/missions.json").readText(), object : TypeReference<List<MissionSpec>>() {}) }

    private val missionIdToMissionSpec: Map<String, MissionSpec> = mapToMissionSpecs.values
        .flatten()
        .associateBy { it.id }

    private val challengeIdToMissionChallenge: Map<String, Pair<MissionSpec, ChallengeSpec>> = missionIdToMissionSpec.let {
        val result = mutableMapOf<String, Pair<MissionSpec, ChallengeSpec>>()
        it.values.forEach { missionSpec ->
            missionSpec.challenges.forEach { challenge ->
                result[challenge.id] = (missionSpec to challenge)
            }
        }
        result
    }

    private val repoToMissionChallenge: Map<String, Pair<MissionSpec, ChallengeSpec>> = missionIdToMissionSpec.let {
        val result = mutableMapOf<String, Pair<MissionSpec, ChallengeSpec>>()
        it.values.forEach { missionSpec ->
            missionSpec.challenges.filter { it.type.withGitHubRepo }.forEach { challenge ->
                result[challenge.spec] = (missionSpec to challenge)
            }
        }
        result
    }

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

    override fun getMissionSpecByMissionId(missionId: String): MissionSpec? = missionIdToMissionSpec[missionId]
    override fun getMissionChallengeByChallengeId(challengeId: String): Pair<MissionSpec, ChallengeSpec>? = challengeIdToMissionChallenge[challengeId]
    override fun getMissionChallengeByRepo(repo: String) = repoToMissionChallenge[repo]
    override fun getI18nText(id: String, locale: Locale, vararg args: String) = localizedText.getValue(id).getTextOrDefaultLocale(locale).render(*args)
    override fun getEntranceDestinationPoint(srcMapId: String, destMapId: String): GridCoordinate {
        return idToMaps.getValue(destMapId).getObjectById<GameMapEntrancePoint>(
            mapEntranceDestinationId(mapEntranceId(srcMapId, destMapId))
        ).gridCoordinate
    }
}

class FastAccessGameMap(private val delegate: GameMap) : GameMap by delegate {
    private val idToObject = objects.associateBy { it.id }

    @Suppress("UNCHECKED_CAST")
    fun <T : GameMapObject> getObjectById(id: String): T {
        return idToObject.getValue(id) as T
    }
}

private fun File.readAsGameMap(): GameMap {
    return Json.decodeFromString(CompressedGameMap.serializer(), readText()).decompress()
}
