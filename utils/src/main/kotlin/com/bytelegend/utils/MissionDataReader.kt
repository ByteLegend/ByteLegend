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

import com.bytelegend.app.shared.entities.mission.ChallengeSpec
import com.bytelegend.app.shared.entities.mission.ChallengeType
import com.bytelegend.app.shared.entities.mission.MissionSpec
import com.bytelegend.app.shared.entities.mission.Tutorial
import java.io.File

/**
 * Read data under `game-data/` directory.
 */
class MissionDataReader(
    private val mapIdToMissionsDir: Map<String, File>
) {
    fun getMissionsOnMap(mapId: String): List<MissionSpec> = maps.getValue(mapId).missionSpecs.values.toList()

    private val maps: Map<String, MapData> = mapIdToMissionsDir.map {
        it.key to MapData(it.value)
    }.toMap()
}

class MapData(ymlDir: File) {
    private val duplicateIdChecker: HashSet<String> = HashSet()

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    val missionSpecs: Map<String, MissionSpec> = ymlDir.let {
        require(it.listFiles() != null) { "$it has no files!" }
        it.listFiles()
    }.filter {
        it.name.endsWith(".yml")
    }.map {
        try {
            val missionSpec = YAML_PARSER.readValue(it, MissionSpec::class.java)
            require(missionSpec.id == it.nameWithoutExtension) {
                "${missionSpec.id} must be put into a file named '${missionSpec.id}.yml'!"
            }
            require(missionSpec.onFinish.coin >= 0) {
                "${missionSpec.id} must have positive coin change!"
            }
            require(missionSpec.challenges.distinctBy { it.id }.size == missionSpec.challenges.size) {
                "${missionSpec.id} has duplicate challenges!"
            }
            missionSpec.onFinish.items.forEach {
                if (it.contains(":")) {
                    require(it.substringAfterLast(":") == missionSpec.id) {
                        "${missionSpec.id} has wrong items!"
                    }
                }
            }
            missionSpec.validateChallenges(ChallengeType.Question) {
                require(readme.isNotBlank()) { "tldr or readme is empty for $id!" }
            }
            missionSpec.validateChallenges(ChallengeType.PullRequest) {
                require(!spec.startsWith("https")) { "$id has invalid spec: $spec" }
            }

            missionSpec
        } catch (e: Throwable) {
            throw IllegalStateException("Failed to parse ${it.absolutePath}", e)
        }
    }.onEach {
        require(duplicateIdChecker.add(it.id)) { "Duplicate id: ${it.id}" }
    }.associateBy {
        it.id
    }

    private fun MissionSpec.validateChallenges(type: ChallengeType? = null, action: ChallengeSpec.() -> Unit) {
        if (type == null) {
            challenges.forEach(action)
        } else {
            challenges.filter { it.type == type }.forEach(action)
        }
    }

    val tutorials: Map<String, Tutorial> = missionSpecs.values.flatMap {
        it.tutorials
    }.onEach {
        require(duplicateIdChecker.add(it.id)) { "Duplicate id: ${it.id}" }
    }.associateBy {
        it.id
    }
}
